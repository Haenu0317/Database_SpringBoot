package com.dlnu.aspect;

import com.dlnu.common.R;
import com.dlnu.util.MD5Utils;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Objects;

@Aspect
@Component
public class SignatureValidation {
    /**
     * 时间戳请求最小限制(30s)
     * 设置的越小，安全系数越高，但是要注意一定的容错性
     */
    private static final long MAX_REQUEST = 30 * 1000L;
    /**
     * 秘钥
     */
    private static final long SECRET= 1524756L;

    /**
     * 验签切点(完整的找到设置的文件地址)
     */
    @Pointcut("execution(@com.dlnu.aop.SignatureValidation * *(..))")
    private void verifyUserKey() {
    }

    /**
     * 开始验签
     */
    @Before("verifyUserKey()")
    public void doBasicProfiling() throws Exception {
        HttpServletRequest request = ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
        String token = request.getHeader("token");
        System.out.println(token);
        String timestamp = request.getHeader("timestamp");
        System.out.println(timestamp);
        try {
            Boolean check = checkToken(token, timestamp);
            System.out.println(check);
            if (!check) {
                System.out.println("111");
                throw new Exception("签名验证错误");
            }
        } catch (Throwable throwable) {
            System.out.println("222");
            throw new Exception("签名验证错误");
        }
    }

    /**
     * 校验token
     *
     * @param token     签名
     * @param timestamp 时间戳
     * @return 校验结果
     */
    private Boolean checkToken(String token, String timestamp) {
        if (StringUtils.isAnyBlank(token, timestamp)) {
            System.out.println("333");
            return false;
        }
        long now = System.currentTimeMillis();
        long time = Long.parseLong(timestamp);
        if (now - time > MAX_REQUEST) {
            System.out.println("时间戳已过期");
            System.out.println("444");
            return false;
        }
        String crypt = MD5Utils.getMD5(SECRET+"");
        return StringUtils.equals(crypt, token);
    }
}

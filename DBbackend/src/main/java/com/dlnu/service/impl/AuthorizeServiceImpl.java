package com.dlnu.service.impl;

import com.dlnu.entity.Account;
import com.dlnu.mapper.UserMapper;
import com.dlnu.service.AuthorizeService;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.mail.MailException;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;
import java.util.concurrent.TimeUnit;

@Service
public class AuthorizeServiceImpl implements AuthorizeService {
    @Value("${spring.mail.username}")
    private String from;
    @Resource
    UserMapper userMapper;
    @Resource
    MailSender mailSender;

    @Resource
    StringRedisTemplate template;

   //循环引用了
    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account account = userMapper.findAccountByUsernameOrEmail(username);
        if (username == null) {
            throw new UsernameNotFoundException("用户名不能为空");
        }
        if (account == null) {
            throw new UsernameNotFoundException("用户或密码错误");
        }
        return User
                .withUsername(account.getUsername())
                .password(account.getPassword())
                .roles("user")
                .build();
    }

    /**
     * 1.先生成验证码
     * 2.把邮箱和验证码放到redis里
     * 3.发送邮件
     * 4.失败,就redis删除
     * 5.注册时候 取出  看是否一致
     */
    @Override
    public String sendValidateEmail(String email, String sessionId) {
        String key = "email:" + sessionId + ":" + email;
        if (Boolean.TRUE.equals(template.hasKey(key))) {
            Long expire = Optional.ofNullable(template.getExpire(key, TimeUnit.SECONDS)).orElse(0L);
            if (expire > 120) {
                return "请求频繁,请稍后再试";
            }
        }
        if (userMapper.findAccountByUsernameOrEmail(email) != null) {
            return "邮箱已经被注册";
        }
        Random random = new Random();
        int code = random.nextInt(899999) + 100000;
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(from);
        message.setTo(email);
        message.setSubject("验证码");
        message.setText("您的验证码为:" + code + "，请在3分钟内使用，过期无效。");
        try {
            mailSender.send(message);

            template.opsForValue().set(key, String.valueOf(code), 3, TimeUnit.MINUTES);
            return null;
        } catch (MailException e) {
            e.printStackTrace();
            return "邮件发送失败";
        }

    }

    @Override
    public String validateAndRegister(String username, String password, String email, String code, String sessionId) {
        String key = "email:" + sessionId + ":" + email;
        if (Boolean.TRUE.equals(template.hasKey(key))) {
            String s = template.opsForValue().get(key);
            if (s == null) return "验证码已过期";
            if (s.equals(code)) {
                password = encoder.encode(password);
                int i = userMapper.createAccount(username, password, email);
                if (i == 1) {
                    return null;
                } else {
                    return "注册失败";
                }

            } else {
                return "验证码错误";
            }
        } else {
            return "请先请求验证码";
        }

    }
}

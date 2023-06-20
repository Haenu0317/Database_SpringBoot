package com.dlnu.service.impl;

import com.dlnu.entity.Account;
import com.dlnu.mapper.UserMapper;
import com.dlnu.service.AuthorizeService;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.mail.MailException;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
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
    public boolean sendValidateEmail(String email, String sessionId) {
        String key ="email:" + sessionId +":" + email;
        if (Boolean.TRUE.equals(template.hasKey(key))) {
            Long expire = Optional.ofNullable(template.getExpire(key, TimeUnit.SECONDS)).orElse(0L);
            if(expire > 120){
                return false;
            }
        }
        Random random=new Random();
        int code = random.nextInt(899999) + 100000;
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(from);
        message.setTo(email);
        message.setSubject("验证码");
        message.setText("您的验证码为:" + code + "，请在3分钟内使用，过期无效。");
        try {
            mailSender.send(message);

            template.opsForValue().set(key,String.valueOf(code),3, TimeUnit.MINUTES);
            return true;
        } catch (MailException e) {
            e.printStackTrace();
            return false;
        }

    }
}

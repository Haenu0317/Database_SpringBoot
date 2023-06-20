package com.dlnu.controller;

import com.dlnu.common.R;
import com.dlnu.service.AuthorizeService;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.constraints.Pattern;
import org.hibernate.validator.constraints.Length;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Validated
@RestController
@RequestMapping("/api/auth")
public class AuthorizeController {
    private final String EMAIL_REGEX = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
    private final String USERNAME_REGEX = "^[a-zA-Z0-9\\u4e00-\\u9fa5]+$";
    @Resource
    AuthorizeService authorizeService;

    @PostMapping("valid-email")
    public R<String> validateEmail(@Pattern(regexp = EMAIL_REGEX) @RequestParam("email") String email, HttpSession session) {
        String s = authorizeService.sendValidateEmail(email, session.getId());
        if (s == null) {
            return R.success("邮件发送成功!注意查收");
        } else {
            return R.error(400, s);
        }
    }

    @PostMapping("/register")
    public R<String> register(
            @Pattern(regexp = USERNAME_REGEX) @Length(min = 2, max = 10) @RequestParam("username") String username,
            @Length(min = 6, max = 20) @RequestParam("password") String password,
            @RequestParam("email") String email,
            @Length(min = 6, max = 6) @RequestParam("code") String code,
            HttpSession session) {
        String s = authorizeService.validateAndRegister(username, password, email, code, session.getId());
        if (s == null) {
            return R.success("注册成功");
        } else {
            return R.error(400, s);
        }
    }
}

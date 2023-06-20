package com.dlnu.controller;

import com.dlnu.common.R;
import com.dlnu.service.AuthorizeService;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.constraints.Pattern;
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
    @Resource
    AuthorizeService authorizeService;
    @PostMapping("valid-email")
    public R<String> validateEmail(@Pattern (regexp = EMAIL_REGEX)@RequestParam("email") String email, HttpSession session) {
    if (authorizeService.sendValidateEmail(email,session.getId())) {

        return R.success("邮件发送成功!注意查收");
    }else {
        return R.error(400,"邮件发送失败!");
    }
    }
}

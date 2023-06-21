package com.dlnu.controller;

import com.dlnu.common.R;
import com.dlnu.entity.auth.AccountUser;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttribute;

@RestController
@RequestMapping("/api/user")
public class UserController {
    @GetMapping("/me")
    public R<AccountUser> me(@SessionAttribute("account") AccountUser account) {
        return R.success(account);
    }
}

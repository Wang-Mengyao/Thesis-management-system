package com.example.demo.controller;

import com.example.demo.common.Constants;
import com.example.demo.common.Result;
import com.example.demo.pojo.User;
import com.example.demo.service.AuthorizeService;
import jakarta.annotation.Resource;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthorizeController {

    @Resource
    AuthorizeService authorizeService;

    @PostMapping("/register")
    public Result registerUser(@RequestParam("username") String username,
                               @RequestParam("password") String password,
                               @RequestParam("email") String email) {
        if(StringUtils.isBlank(username)||StringUtils.isBlank(password)||StringUtils.isBlank(email))
            return Result.error(Constants.CODE_400,"参数错误");
        return authorizeService.register(username, password, email);
    }
}

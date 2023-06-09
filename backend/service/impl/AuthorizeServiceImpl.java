package com.example.demo.service.impl;

import com.example.demo.common.Constants;
import com.example.demo.common.Result;
import com.example.demo.exception.ServiceException;
import com.example.demo.mapper.AuthorizeMapper;

import com.example.demo.utils.TokenUtils;
import jakarta.annotation.Resource;
import com.example.demo.pojo.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthorizeServiceImpl implements com.example.demo.service.AuthorizeService {
    @Resource
    AuthorizeMapper authorizeMapper;

    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
    @Override
    public UserDetails loadUserByUsername(String username) throws ServiceException {
        User user = authorizeMapper.findUserByNameOrEmail(username);
        if(user == null)
            throw new ServiceException(Constants.CODE_401,"用户名或密码错误");
        return org.springframework.security.core.userdetails.User
                .withUsername(user.getUsername())
                .password(user.getPassword())
                .build();

    }


    @Override
    public Result register(String username, String password, String email) {
        if(authorizeMapper.findUserByNameOrEmail(username)!=null||authorizeMapper.findUserByNameOrEmail(email)!=null)
            return Result.error(Constants.CODE_400,"注册失败，用户名或邮箱已注册");
        password = encoder.encode(password);
        if(authorizeMapper.createUser(username,password,email)>0){
            User res = authorizeMapper.findUserByNameOrEmail(username);
            String token=TokenUtils.genToken(res.getId().toString(),res.getPassword());
            res.setToken(token);
            return Result.success(res);
        } else {
            return Result.error(Constants.CODE_500,"内部错误，请联系管理员");
        }
    }
}

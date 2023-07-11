package com.example.demo.service.impl;

import cn.hutool.log.Log;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.common.Constants;
import com.example.demo.common.Result;
import com.example.demo.controller.DTO.UserDTO;
import com.example.demo.exception.ServiceException;
import com.example.demo.pojo.User;
import com.example.demo.service.UserService;
import com.example.demo.mapper.UserMapper;

import com.example.demo.utils.TokenUtils;
import jakarta.annotation.Resource;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
* @author qqy20001120
* @description 针对表【sys_user】的数据库操作Service实现
* @createDate 2023-05-27 17:36:38
*/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService{

    private static final Log LOG = Log.get();
//    @Override
//    public UserDTO login(UserDTO userDTO) {
//        User one = getUserInfo(userDTO);
//        if(one!=null) {
//            BeanUtils.copyProperties(one,userDTO);
//            // 设置token
//            String token=TokenUtils.genToken(one.getId().toString(),one.getPassword());
//            userDTO.setToken(token);
//            return userDTO;
//        } else {
//            throw new ServiceException(Constants.CODE_600,"用户名或密码错误");
//        }
//    }
    @Resource
    UserMapper userMapper;

    @Resource
    private BCryptPasswordEncoder bCryptPasswordEncoder; //注入bcryct加密

    @Override
    public User login(User userParam) {
        User userPwd = userMapper.findUserByNameOrEmail(userParam.getUsername());
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", userParam.getUsername());
        queryWrapper.eq("password", userPwd.getPassword());
        User res;
        try {
            res = getOne(queryWrapper);
        } catch (Exception e) {
            LOG.error(e);
            throw new ServiceException(Constants.CODE_500, "系统错误");
        }

        if(!bCryptPasswordEncoder.matches(userParam.getPassword(),userPwd.getPassword())){
            throw new ServiceException(Constants.CODE_401,"密码错误");
        }
        if(res == null) {
            throw new ServiceException(Constants.CODE_401,"用户名错误");
        }
        // 设置token
        String token=TokenUtils.genToken(res.getId().toString(),res.getPassword());
        res.setToken(token);

        return res;
    }



    @Override
    public User register(UserDTO userDTO) {
        User one = getUserInfo(userDTO);
        if(one==null){
            one = new User();
            BeanUtils.copyProperties(userDTO,one);
            save(one); // 用户对象存储到数据库
        } else {
            throw new ServiceException(Constants.CODE_600, "用户已存在");
        }
        return one;
    }

    private User getUserInfo(UserDTO userDTO){
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", userDTO.getUsername());
        queryWrapper.eq("password", userDTO.getPassword());
        // 希望是唯一的，在注册时注意，username应该是唯一键
        User one;
        try {
            one = getOne(queryWrapper);
        } catch (Exception e) {
            LOG.error(e);
            throw new ServiceException(Constants.CODE_500, "系统错误");
        }
        return one;
    }

    @Override
    public Result loadUser(String username){
        User one = userMapper.findUserByNameOrEmail(username);
        one.setToken(TokenUtils.genToken(one.getId().toString(),one.getPassword()));
        return Result.success(one);
    }

}





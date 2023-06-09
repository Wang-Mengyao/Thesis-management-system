package com.example.demo.service;

import com.example.demo.controller.DTO.UserDTO;
import com.example.demo.pojo.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author qqy20001120
* @description 针对表【sys_user】的数据库操作Service
* @createDate 2023-05-27 17:36:38
*/
public interface UserService extends IService<User> {

    User login(User user);

    User register(UserDTO userDTO);


}

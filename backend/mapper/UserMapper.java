package com.example.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.pojo.User;
import jakarta.annotation.Resource;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
* @author qqy20001120
* @description 针对表【sys_user】的数据库操作Mapper
* @createDate 2023-05-27 17:36:38
* @Entity com.example.demo.pojo.User
*/
@Mapper
public interface UserMapper extends BaseMapper<User> {

    @Select("select * from sys_user where username = #{text} or email = #{text}")
    User findUserByNameOrEmail(String text);


}





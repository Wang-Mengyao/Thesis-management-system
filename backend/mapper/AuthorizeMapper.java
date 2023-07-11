package com.example.demo.mapper;
import com.example.demo.pojo.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
@Mapper
public interface AuthorizeMapper {
    @Select("select * from sys_user where username = #{text} or email = #{text}")
    User findUserByNameOrEmail(String text);
    @Insert("insert into sys_user (username,password, email) values (#{username}, #{password}, #{email})")
    int createUser(String username, String password, String email);
}

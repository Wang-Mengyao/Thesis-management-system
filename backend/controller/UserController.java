package com.example.demo.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.common.Constants;
import com.example.demo.common.Result;
import com.example.demo.controller.DTO.UserDTO;
import com.example.demo.pojo.User;
import com.example.demo.service.UserService;
import com.example.demo.utils.TokenUtils;
import jakarta.annotation.Resource;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;

    @GetMapping("/me")
    public Result me(@SessionAttribute("user") User user){
        String token=TokenUtils.genToken(user.getId().toString(),user.getPassword());
        user.setToken(token);
        return Result.success(user);
    }

    @PostMapping("/login")
    public Result login(@RequestBody User user) {
        String username = user.getUsername();
        String password = user.getPassword();
        if(StringUtils.isBlank(username)||StringUtils.isBlank(password)){
            return Result.error(Constants.CODE_400, "参数错误");
        }
        User res = userService.login(user);
        return Result.success(res);
    }

    @PostMapping("/register")
    public Result register(@RequestBody UserDTO userDTO) {
        String username = userDTO.getUsername();
        String password = userDTO.getPassword();
        if(StringUtils.isBlank(username)||StringUtils.isBlank(password)){
            return Result.error(Constants.CODE_400, "参数错误");
        }
        return Result.success(userService.register(userDTO));
    }

    // 新增&更新
    @PostMapping
    public Result save(@RequestBody User user) {
        return Result.success(userService.saveOrUpdate(user));
    }

    // 查找所有数据
    @GetMapping
    public Result findAll() {
        return Result.success(userService.list());
    }

    @DeleteMapping("/{id}")//这是路径，每个接口名字不一样 /user/{id}
    public Result delete(@PathVariable Integer id) {
        return Result.success(userService.removeById(id));
    }

    @PostMapping("/del/batch")
    public Result deleteBatch(@RequestBody List<Integer> ids) {
        return Result.success(userService.removeByIds(ids));
    }

    // 分页查询
    // 接口路径：/user/page
    // @RequestParam接收pagenum和pagesize
//    @GetMapping("/page")
//    public List<User> findPage(@RequestParam Integer pageNum, @RequestParam Integer pageSize) {
//        pageNum = (pageNum-1)*pageSize;
//        return userMapper.selectPage(pageNum, pageSize);
//    }
    // 分页查询
    // 接口路径：/user/page
    // @RequestParam接收pagenum和pagesize
    @GetMapping("/page")
    public Result findPage(@RequestParam Integer pageNum, @RequestParam Integer pageSize,
                                @RequestParam(defaultValue = "") String username, @RequestParam(defaultValue = "") String nickname) {
        IPage<User> page = new Page<>(pageNum, pageSize);
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("username", username);
        queryWrapper.like("nickname", nickname);
        return Result.success(userService.page(page, queryWrapper));
    }
}

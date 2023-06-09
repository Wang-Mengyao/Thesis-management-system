package com.example.demo.config.interceptor;

import cn.hutool.core.util.StrUtil;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.example.demo.common.Constants;
import com.example.demo.exception.ServiceException;
import com.example.demo.mapper.UserMapper;
//import com.example.demo.pojo.User;
import com.example.demo.service.UserService;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

public class JwtInterceptor implements HandlerInterceptor {

    //    @Autowired
//    private UserService userService;
    @Resource
    UserMapper userMapper;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        SecurityContext context = SecurityContextHolder.getContext();
        Authentication authentication = context.getAuthentication();
        User user = (User) authentication.getPrincipal();
        String username = user.getUsername();
        com.example.demo.pojo.User userWithId = userMapper.findUserByNameOrEmail(username);
        request.getSession().setAttribute("user", userWithId);
        return true;
    }
}
//        String token = request.getHeader("token");
//        if(!(handler instanceof HandlerMethod)){
//            return true;
//        }
//        if (StrUtil.isBlank(token)) {
//            token = request.getParameter("token");
//        }
//
//        // 执行认证
//        if (StrUtil.isBlank(token)) {
//            throw new ServiceException(Constants.CODE_401, "无token，请重新登录");
//        }
//        // 获取 token 中的userId
//        String userId;
//        try {
//            userId = JWT.decode(token).getAudience().get(0);
//        } catch (Exception e) {
//            throw new ServiceException(Constants.CODE_401, "token验证失败，请重新登录");
//        }
//        // 根据token中的userid查询数据库
//        User user = userService.getById(userId);
//        if (user == null) {
//            throw new ServiceException(Constants.CODE_401, "用户不存在，请重新登录");
//        }
//
//        try {
//            // 用户密码加签验证 token
//            JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(user.getPassword())).build();
//            jwtVerifier.verify(token); // 验证token
//        } catch (JWTVerificationException e) {
//            throw new ServiceException(Constants.CODE_401, "token验证失败，请重新登录");
//        }
//        return true;
//    }
//}

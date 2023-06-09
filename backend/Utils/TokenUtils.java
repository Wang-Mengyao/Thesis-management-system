package com.example.demo.utils;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.example.demo.pojo.User;
import com.example.demo.service.UserService;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.Resource;
import jakarta.servlet.ServletRequestAttributeEvent;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Date;

@Component
public class TokenUtils {

    private static UserService staticUserService;

    @Resource
    private UserService userService;

    @PostConstruct
    public void setUserService() {
        staticUserService = userService;
    }

    /**
     * 生成token
     * @param userId
     * @param sign
     * @return
     */
    public static String genToken(String userId, String sign) {
        return JWT.create().withAudience(userId) //userid放在token里作为载荷
                .withExpiresAt(DateUtil.offsetHour(new Date(),2)) //两小时后过期
                .sign(Algorithm.HMAC256(sign)); //以password作为密钥
    }

    /**
     * 获取当前登录的用户信息
     * @return user对象
     */
    public static User getCurrentUser() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String token = request.getHeader("token");
        if(StrUtil.isNotBlank(token)) {
            String userId = JWT.decode(token).getAudience().get(0);
            return staticUserService.getById(Integer.valueOf(userId));
        }
        return null;
    }
}

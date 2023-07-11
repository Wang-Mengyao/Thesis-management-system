package com.example.demo.service;

import com.example.demo.common.Result;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface AuthorizeService extends UserDetailsService {

//    Result login(String username, String password);
    Result register(String username, String password, String email);

}

package com.jwt_auth.service;

import com.jwt_auth.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface CustomUserDetailsService extends UserDetailsService {
    User getByUsername(String username);
}

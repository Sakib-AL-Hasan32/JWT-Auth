package com.jwt_auth.dto.response;

import com.jwt_auth.entity.Role;

import java.util.Set;

public record UserResponse(
        String username,
        String email,
        Set<Role> roles
) {
}

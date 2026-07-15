package com.jwt_auth.dto.response;

import java.util.Set;

public record LoginResponse (
        String token,
        String username,
        Set<String > roles
) {
}
package com.jwt_auth.constants;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class PublicEndpoints {
    public static final String[] PUBLIC_ENDPOINTS = {
            ApiEndpoints.Auth.BASE + ApiEndpoints.Auth.REGISTER,
            ApiEndpoints.Auth.BASE + ApiEndpoints.Auth.LOGIN,
    };
}

package com.jwt_auth.constants;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class RoleNames {
    public static final String ADMIN = "ADMIN";
    public static final String USER = "USER";

    public static final String ADMIN_AUTHORITY = "ROLE_" + ADMIN;
    public static final String USER_AUTHORITY = "ROLE_" + USER;
}

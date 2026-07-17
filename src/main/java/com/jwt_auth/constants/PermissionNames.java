package com.jwt_auth.constants;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class PermissionNames {
    public static final String PROFILE_READ = "PROFILE_READ";
    public static final String ADD_PRODUCT = "ADD_PRODUCT";
    public static final String GET_ALL_PRODUCT = "GET_ALL_PRODUCT";
    public static final String GET_PRODUCT_BY_ID = "GET_PRODUCT_BY_ID";
}

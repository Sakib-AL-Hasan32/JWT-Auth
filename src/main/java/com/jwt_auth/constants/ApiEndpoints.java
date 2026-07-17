package com.jwt_auth.constants;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ApiEndpoints {
    public static final String API_VERSION = "/api/v1";

    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static final class Auth {
        public static final String BASE = API_VERSION + "/auth";
        public static final String LOGIN = "/login";
        public static final String REGISTER = "/register";

    }

    @NoArgsConstructor(access = lombok.AccessLevel.PRIVATE)
    public static final class Profile {
        public static final String BASE = API_VERSION + "/profile";
    }

    @NoArgsConstructor(access = lombok.AccessLevel.PRIVATE)
    public static final class Product {
        public static final String BASE = API_VERSION + "/product";
        public static final String ADD_PRODUCT = "/addProduct";
        public static final String ID = "/{id}";
        public static final String NAME = "/name";
    }

    @NoArgsConstructor(access = lombok.AccessLevel.PRIVATE)
    public static final class User {
        public static final String BASE = API_VERSION + "/user";
        public static final String ALL_USERS = "/all-users";
    }
}

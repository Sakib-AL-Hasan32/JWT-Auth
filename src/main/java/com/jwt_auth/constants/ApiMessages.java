package com.jwt_auth.constants;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ApiMessages {

    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static final class Success {
        public static final String USER_REGISTERED = "User registered successfully";
        public static final String USER_LOGGED_IN = "User logged in successfully";
        public static final String PROFILE_FETCHED = "Successfully fetched profile";
        public static final String FETCHED_ALL_USERS = "Successfully fetched all users";
        public static final String FETCHED_USER_BY_ID = "Successfully fetched user by id";

        public static final String PRODUCT_ADDED = "Product added successfully";
        public static final String FETCHED_ALL_PRODUCTS = "Successfully fetched all products";
        public static final String PRODUCT_FETCHED_BY_ID = "Successfully fetched product by id";
        public static final String PRODUCT_FETCHED_BY_NAME = "Successfully fetched product by name";
        public static final String PRODUCT_UPDATED = "Product updated successfully";
        public static final String PRODUCT_DELETED = "Product deleted successfully";
    }

    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static final class Error {
        public static final String ROLE_NOT_FOUND = "Role not found";
        public static final String USER_NOT_FOUND = "User not found";
        public static final String PRODUCT_NOT_FOUND = "Product not found";
    }

    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static final class Validation {
        public static final String USER_ALREADY_EXISTS = "User already exists";
        public static final String EMAIL_ALREADY_EXISTS = "Email already exists";
    }

    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static final class Authentication {

    }
}

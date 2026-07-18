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
        public static final String FETCHED_USER_BY_USERNAME = "Successfully fetched user by username";
        public static final String USER_ADDED = "Successfully added user";
        public static final String USER_UPDATED = "Successfully updated user";
        public static final String USER_DELETED = "Successfully deleted user";

        public static final String ROLE_UPDATED = "Role updated successfully";
        public static final String ROLE_REMOVED = "Role removed successfully";

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
        public static final String ROLE_CONFLICT = "User already has this role";
        public static final String ROLE_NOT_ASSIGNED = "Role not assigned";
        public static final String CANNOT_REMOVE_LAST_ROLE = "Cannot remove last role";
        public static final String USER_ALREADY_EXISTS = "User already exists";
        public static final String EMAIL_ALREADY_EXISTS = "Email already exists";
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

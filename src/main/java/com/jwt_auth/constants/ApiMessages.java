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
    }

    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static final class Error {
        public static final String ROLE_NOT_FOUND = "Role not found";
        public static final String USER_NOT_FOUND = "User not found";
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

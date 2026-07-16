package com.jwt_auth.constants;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class AdminCredentials {

    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static final class Admin1 {
        public static final String ADMIN_FIRST_NAME = "Sakib";
        public static final String ADMIN_LAST_NAME = "Al Hasan";
        public static final String ADMIN_USERNAME = "shsakib32";
        public static final String ADMIN_EMAIL = "shsakib32@gmail.com";
        public static final String ADMIN_PASSWORD = "admin123";
    }

}

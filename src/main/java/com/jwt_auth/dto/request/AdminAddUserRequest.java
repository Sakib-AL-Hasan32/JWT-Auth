package com.jwt_auth.dto.request;

import com.jwt_auth.entity.Role;
import jakarta.validation.constraints.NotBlank;

import java.util.Set;

public record AdminAddUserRequest(
        @NotBlank(message = "Username Can Not Be Blanked.")
        String username,

        @NotBlank(message = "Password Can Not Be Blanked.")
        String password,

        @NotBlank(message = "Email Can Not Be Blanked.")
        String email,

        String firstName,
        String lastName,

        Set<Role>roles
) {
}

package com.jwt_auth.dto.response;

import com.jwt_auth.entity.Role;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

import java.util.Set;

public record AdminUpdateUserResponse(
        @Positive
        Long id,

        @NotBlank(message = "Username Can Not Be Blanked.")
        String username,

        @NotBlank(message = "Email Can Not Be Blanked.")
        String email,

        String firstName,
        String lastName,

        Set<Role> roles
) {
}

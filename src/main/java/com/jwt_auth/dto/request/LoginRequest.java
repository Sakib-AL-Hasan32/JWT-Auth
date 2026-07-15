package com.jwt_auth.dto.request;

import jakarta.validation.constraints.NotBlank;

public record LoginRequest(
        @NotBlank(message = "User Can Not Be Blanked.")
        String username,

        @NotBlank(message = "Password Can Not Be Blanked.")
        String password
) {
}

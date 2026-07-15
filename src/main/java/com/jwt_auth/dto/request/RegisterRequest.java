package com.jwt_auth.dto.request;

import jakarta.validation.constraints.NotBlank;

public record RegisterRequest(
    @NotBlank(message = "Username Can Not Be Blanked.")
    String username,

    @NotBlank(message = "Password Can Not Be Blanked.")
    String password,

    @NotBlank(message = "Email Can Not Be Blanked.")
    String email,

    String firstName,
    String lastName
) {
}

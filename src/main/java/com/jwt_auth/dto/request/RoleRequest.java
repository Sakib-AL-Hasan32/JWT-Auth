package com.jwt_auth.dto.request;

import jakarta.validation.constraints.NotBlank;

public record RoleRequest(
        @NotBlank(message = "Role Can Not Be Blanked.")
        String role
) {
}

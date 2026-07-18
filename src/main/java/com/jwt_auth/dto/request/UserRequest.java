package com.jwt_auth.dto.request;

import jakarta.validation.constraints.NotBlank;

public record UserRequest(
        @NotBlank(message = "Searching Name Can Not Be Blanked.")
        String name
) {
}

package com.jwt_auth.dto.request;

import jakarta.validation.constraints.NotBlank;

public record SearchProductRequest(
        @NotBlank(message = "Name Can Not Be Blanked")
        String name
) {
}

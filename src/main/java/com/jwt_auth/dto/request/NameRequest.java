package com.jwt_auth.dto.request;

import jakarta.validation.constraints.NotBlank;

public record NameRequest(
        @NotBlank(message = "This Can Not Be Blanked")
        String name
) {
}

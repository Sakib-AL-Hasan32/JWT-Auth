package com.jwt_auth.dto.response;

public record ProfileResponse(
        String firstName,
        String lastName,
        String username,
        String email
) {
}

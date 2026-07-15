package com.jwt_auth.service;

import com.jwt_auth.dto.request.RegisterRequest;
import com.jwt_auth.dto.response.ApiResponse;

public interface AuthService {
    ApiResponse<Void> register(RegisterRequest registerRequest);
}

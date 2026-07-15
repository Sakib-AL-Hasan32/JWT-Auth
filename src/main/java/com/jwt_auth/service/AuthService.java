package com.jwt_auth.service;

import com.jwt_auth.dto.request.LoginRequest;
import com.jwt_auth.dto.request.RegisterRequest;
import com.jwt_auth.dto.response.common.ApiResponse;
import com.jwt_auth.dto.response.LoginResponse;
import com.jwt_auth.dto.response.RegisterResponse;

public interface AuthService {
    ApiResponse<RegisterResponse> register(RegisterRequest registerRequest);

    ApiResponse<LoginResponse> login(LoginRequest loginRequest);
}

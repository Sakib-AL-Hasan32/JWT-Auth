package com.jwt_auth.controller;

import com.jwt_auth.constants.ApiEndpoints;
import com.jwt_auth.dto.request.LoginRequest;
import com.jwt_auth.dto.request.RegisterRequest;
import com.jwt_auth.dto.response.common.ApiResponse;
import com.jwt_auth.dto.response.LoginResponse;
import com.jwt_auth.dto.response.RegisterResponse;
import com.jwt_auth.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(ApiEndpoints.Auth.BASE)
public class AuthController {
    private final AuthService authService;

    @PostMapping(ApiEndpoints.Auth.REGISTER)
    public ResponseEntity<ApiResponse<RegisterResponse>> register (@Valid @RequestBody RegisterRequest registerRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(authService.register(registerRequest));
    }

    @PostMapping(ApiEndpoints.Auth.LOGIN)
    public ResponseEntity<ApiResponse<LoginResponse>> login (@Valid @RequestBody LoginRequest loginRequest) {
        return ResponseEntity.status(HttpStatus.OK).body(authService.login(loginRequest));
    }
}

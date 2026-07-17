package com.jwt_auth.service;

import com.jwt_auth.dto.request.RoleRequest;
import com.jwt_auth.dto.response.UserResponse;
import com.jwt_auth.dto.response.common.ApiResponse;

import java.util.List;

public interface UserService {
    ApiResponse<List<UserResponse>> getAllUsers();
    ApiResponse<UserResponse> getUserById(Long id);
    ApiResponse<UserResponse> addRole(RoleRequest roleRequest, Long id);
    ApiResponse<UserResponse> deleteRole(RoleRequest roleRequest, Long id);
}

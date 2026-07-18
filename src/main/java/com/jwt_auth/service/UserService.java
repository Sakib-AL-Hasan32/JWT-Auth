package com.jwt_auth.service;

import com.jwt_auth.dto.request.AdminAddUserRequest;
import com.jwt_auth.dto.request.NameRequest;
import com.jwt_auth.dto.response.AdminAddUserResponse;
import com.jwt_auth.dto.response.UserResponse;
import com.jwt_auth.dto.response.common.ApiResponse;

import java.util.List;

public interface UserService {
    ApiResponse<List<UserResponse>> getAllUsers();
    ApiResponse<UserResponse> getUserById(Long id);
    ApiResponse<UserResponse> addRole(NameRequest roleRequest, Long id);
    ApiResponse<UserResponse> deleteRole(NameRequest roleRequest, Long id);
    ApiResponse<UserResponse> getUserByUsername(NameRequest userRequest);
    ApiResponse<AdminAddUserResponse> addUser(AdminAddUserRequest adminAddUserRequest);
}

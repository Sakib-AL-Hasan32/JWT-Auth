package com.jwt_auth.controller;

import com.jwt_auth.constants.ApiEndpoints;
import com.jwt_auth.dto.request.AdminAddUserRequest;
import com.jwt_auth.dto.request.AdminUpdateUserRequest;
import com.jwt_auth.dto.request.NameRequest;
import com.jwt_auth.dto.response.AdminAddUserResponse;
import com.jwt_auth.dto.response.AdminUpdateUserResponse;
import com.jwt_auth.dto.response.UserResponse;
import com.jwt_auth.dto.response.common.ApiResponse;
import com.jwt_auth.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(ApiEndpoints.User.BASE)
public class UserController {
    private final UserService userService;

    @GetMapping(ApiEndpoints.User.ALL_USERS)
    public ResponseEntity<ApiResponse<List<UserResponse>>> getAllUsers() {
        return ResponseEntity.status(HttpStatus.OK).body(userService.getAllUsers());
    }

    @GetMapping(ApiEndpoints.User.ID)
    public ResponseEntity<ApiResponse<UserResponse>> getUserById(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.getUserById(id));
    }

    @GetMapping(ApiEndpoints.User.NAME)
    public ResponseEntity<ApiResponse<UserResponse>> getUserByUsername(@RequestBody NameRequest userRequest) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.getUserByUsername(userRequest));
    }

    @PatchMapping(ApiEndpoints.User.ADD_ROLE)
    public ResponseEntity<ApiResponse<UserResponse>> addRole (@Valid @RequestBody NameRequest roleRequest,
                                                              @PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.addRole(roleRequest, id));
    }

    @PatchMapping(ApiEndpoints.User.DELETE_ROLE)
    public ResponseEntity<ApiResponse<UserResponse>> deleteRole (@Valid @RequestBody NameRequest roleRequest,
                                                              @PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.deleteRole(roleRequest, id));
    }

    @PostMapping(ApiEndpoints.User.ADD_USER)
    public ResponseEntity<ApiResponse<AdminAddUserResponse>> addUser (@Valid @RequestBody AdminAddUserRequest adminAddUserRequest) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.addUser(adminAddUserRequest));
    }

    @PutMapping(ApiEndpoints.User.UPDATE_USER)
    public ResponseEntity<ApiResponse<AdminUpdateUserResponse>> updateUser (@Valid @RequestBody AdminUpdateUserRequest adminUpdateUserRequest,
                                                                            @PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.updateUser(adminUpdateUserRequest, id));
    }
}

package com.jwt_auth.controller;

import com.jwt_auth.constants.ApiEndpoints;
import com.jwt_auth.dto.request.RoleRequest;
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

    @PatchMapping(ApiEndpoints.User.ADD_ROLE)
    public ResponseEntity<ApiResponse<UserResponse>> addRole (@Valid @RequestBody RoleRequest roleRequest,
                                                              @PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.addRole(roleRequest, id));
    }
}

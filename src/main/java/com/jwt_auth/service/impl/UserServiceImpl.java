package com.jwt_auth.service.impl;

import com.jwt_auth.constants.ApiMessages;
import com.jwt_auth.constants.PermissionNames;
import com.jwt_auth.constants.RoleNames;
import com.jwt_auth.dto.request.RoleRequest;
import com.jwt_auth.dto.request.UserRequest;
import com.jwt_auth.dto.response.UserResponse;
import com.jwt_auth.dto.response.common.ApiResponse;
import com.jwt_auth.entity.Role;
import com.jwt_auth.entity.User;
import com.jwt_auth.exception.ResourceNotFoundException;
import com.jwt_auth.repository.RoleRepository;
import com.jwt_auth.repository.UserRepository;
import com.jwt_auth.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    @Override
    @PreAuthorize("hasAuthority('" + PermissionNames.GET_ALL_USERS + "')")
    public ApiResponse<List<UserResponse>> getAllUsers() {
        List<User> users = userRepository.findAll();
        List<UserResponse> userResponseList = new ArrayList<>();
        for (User user : users) {
            boolean isUser = user.getRoles().stream()
                    .anyMatch(role -> role.getName().equals(RoleNames.USER));
            if (isUser) {
                userResponseList.add(new UserResponse(
                        user.getUsername(),
                        user.getEmail(),
                        user.getRoles()
                ));
            }
        }
        return ApiResponse.<List<UserResponse>>builder()
                .data(userResponseList)
                .message(ApiMessages.Success.FETCHED_ALL_USERS)
                .build();
    }

    @Override
    @PreAuthorize("hasAuthority('" + PermissionNames.GET_USER_BY_ID + "')")
    public ApiResponse<UserResponse> getUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UsernameNotFoundException(ApiMessages.Error.USER_NOT_FOUND));
        UserResponse userResponse = new UserResponse(
                user.getUsername(),
                user.getEmail(),
                user.getRoles()
        );
        return ApiResponse.<UserResponse>builder()
                .data(userResponse)
                .message(ApiMessages.Success.FETCHED_USER_BY_ID)
                .build();
    }

    @Override
    @PreAuthorize("hasAuthority('" + PermissionNames.GET_USER_BY_USERNAME + "')")
    public ApiResponse<UserResponse> getUserByUsername(UserRequest userRequest) {
        User user = userRepository.findByUsername(userRequest.name())
                .orElseThrow(() -> new UsernameNotFoundException(ApiMessages.Error.USER_NOT_FOUND));
        UserResponse userResponse = new UserResponse(
                user.getUsername(),
                user.getEmail(),
                user.getRoles()
        );
        return ApiResponse.<UserResponse>builder()
                .data(userResponse)
                .message(ApiMessages.Success.FETCHED_USER_BY_USERNAME)
                .build();
    }

    @Override
    @PreAuthorize("hasAuthority('" + PermissionNames.ADD_ROLE + "')")
    public ApiResponse<UserResponse> addRole(RoleRequest roleRequest, Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UsernameNotFoundException(ApiMessages.Error.USER_NOT_FOUND));
        Role role = roleRepository.findByName(roleRequest.role())
                .orElseThrow(() -> new ResourceNotFoundException(ApiMessages.Error.ROLE_NOT_FOUND));
        boolean alreadyAssigned = user.getRoles().stream()
                .anyMatch(r -> r.getName().equals(role.getName()));
        if (alreadyAssigned) {
            throw new IllegalArgumentException(ApiMessages.Error.ROLE_CONFLICT);
        }
        user.getRoles().add(role);
        userRepository.save(user);
        return ApiResponse.<UserResponse>builder()
                .data(new UserResponse(
                        user.getUsername(),
                        user.getEmail(),
                        user.getRoles()
                ))
                .message(ApiMessages.Success.ROLE_UPDATED)
                .build();
    }

    @Override
    @PreAuthorize("hasAuthority('" + PermissionNames.DELETE_ROLE + "')")
    public ApiResponse<UserResponse> deleteRole(RoleRequest roleRequest, Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UsernameNotFoundException(ApiMessages.Error.USER_NOT_FOUND));

        Role role = roleRepository.findByName(roleRequest.role())
                .orElseThrow(() -> new ResourceNotFoundException(ApiMessages.Error.ROLE_NOT_FOUND));

        boolean assigned = user.getRoles().stream()
                .anyMatch(r -> r.getName().equals(role.getName()));

        if (!assigned) {
            throw new IllegalArgumentException(ApiMessages.Error.ROLE_NOT_ASSIGNED);
        }
        if (user.getRoles().size() == 1) {
            throw new IllegalArgumentException(ApiMessages.Error.CANNOT_REMOVE_LAST_ROLE);
        }
        user.getRoles().removeIf(r -> r.getName().equals(role.getName()));
        userRepository.save(user);
        return ApiResponse.<UserResponse>builder()
                .data(new UserResponse(
                        user.getUsername(),
                        user.getEmail(),
                        user.getRoles()
                ))
                .message(ApiMessages.Success.ROLE_REMOVED)
                .build();
    }
}

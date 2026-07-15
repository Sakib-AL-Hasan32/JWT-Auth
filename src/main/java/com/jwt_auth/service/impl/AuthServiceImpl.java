package com.jwt_auth.service.impl;

import com.jwt_auth.constants.ApiMessages;
import com.jwt_auth.constants.RoleNames;
import com.jwt_auth.dto.request.RegisterRequest;
import com.jwt_auth.dto.response.ApiResponse;
import com.jwt_auth.entity.Role;
import com.jwt_auth.entity.User;
import com.jwt_auth.exception.DuplicateResourceException;
import com.jwt_auth.exception.ResourceNotFoundException;
import com.jwt_auth.repository.RoleRepository;
import com.jwt_auth.repository.UserRepository;
import com.jwt_auth.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.LinkedHashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public ApiResponse<Void> register(RegisterRequest registerRequest) {
        validateDuplicateUser(registerRequest);

        Role userRole = roleRepository.findByName(RoleNames.USER)
                .orElseThrow(() -> new ResourceNotFoundException(ApiMessages.Error.ROLE_NOT_FOUND));

        User user = User.builder()
                .firstName(registerRequest.firstName())
                .lastName(registerRequest.lastName())
                .username(registerRequest.username())
                .email(registerRequest.email())
                .password(passwordEncoder.encode(registerRequest.password()))
                .roles(new LinkedHashSet<>(Set.of(userRole)))
                .build();

        userRepository.save(user);

        return ApiResponse.<Void>builder()
                .message(ApiMessages.Success.USER_REGISTERED)
                .build();
    }

    private void validateDuplicateUser(RegisterRequest registerRequest) {
        if(userRepository.existsByUsername(registerRequest.username())) {
            throw new DuplicateResourceException(ApiMessages.Validation.USER_ALREADY_EXISTS);
        }

        if (userRepository.existsByEmail(registerRequest.email())) {
            throw new DuplicateResourceException(ApiMessages.Validation.EMAIL_ALREADY_EXISTS);
        }
    }
}

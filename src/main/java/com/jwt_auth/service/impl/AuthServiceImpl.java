package com.jwt_auth.service.impl;

import com.jwt_auth.constants.ApiMessages;
import com.jwt_auth.constants.RoleNames;
import com.jwt_auth.dto.request.LoginRequest;
import com.jwt_auth.dto.request.RegisterRequest;
import com.jwt_auth.dto.response.common.ApiResponse;
import com.jwt_auth.dto.response.LoginResponse;
import com.jwt_auth.dto.response.RegisterResponse;
import com.jwt_auth.entity.Role;
import com.jwt_auth.entity.User;
import com.jwt_auth.exception.DuplicateResourceException;
import com.jwt_auth.exception.ResourceNotFoundException;
import com.jwt_auth.repository.RoleRepository;
import com.jwt_auth.repository.UserRepository;
import com.jwt_auth.security.JwtTokenService;
import com.jwt_auth.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
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
    private final AuthenticationManager authenticationManager;
    private final JwtTokenService jwtTokenService;

    @Override
    public ApiResponse<RegisterResponse> register(RegisterRequest registerRequest) {
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

        return ApiResponse.<RegisterResponse>builder()
                .data(new RegisterResponse(user.getId(), user.getUsername()))
                .message(ApiMessages.Success.USER_REGISTERED)
                .build();
    }

    @Override
    public ApiResponse<LoginResponse> login(LoginRequest loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.username(), loginRequest.password())
        );

        String username = authentication.getName();
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(ApiMessages.Error.USER_NOT_FOUND));
        Set<String> roles = user.getRoles().stream()
                .map(Role::getName)
                .collect(java.util.stream.Collectors.toCollection(LinkedHashSet::new));

        String jwtToken = jwtTokenService.generateAccessToken(user);

        return ApiResponse.<LoginResponse>builder()
                .data(new LoginResponse(jwtToken, user.getUsername(), roles))
                .message(ApiMessages.Success.USER_LOGGED_IN)
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

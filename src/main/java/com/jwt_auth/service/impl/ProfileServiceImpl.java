package com.jwt_auth.service.impl;

import com.jwt_auth.constants.ApiMessages;
import com.jwt_auth.constants.PermissionNames;
import com.jwt_auth.dto.response.ProfileResponse;
import com.jwt_auth.dto.response.common.ApiResponse;
import com.jwt_auth.entity.User;
import com.jwt_auth.repository.UserRepository;
import com.jwt_auth.service.ProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProfileServiceImpl implements ProfileService {
    private final UserRepository userRepository;

    @Override
    @PreAuthorize("hasAuthority('" + PermissionNames.PROFILE_READ + "')")
    public ApiResponse<ProfileResponse> getProfile(String username) {
        User user = userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(ApiMessages.Error.USER_NOT_FOUND));

        return ApiResponse.<ProfileResponse>builder()
                .data(new ProfileResponse(user.getFirstName(), user.getLastName(), user.getUsername(), user.getEmail()))
                .message(ApiMessages.Success.PROFILE_FETCHED)
                .build();
    }
}

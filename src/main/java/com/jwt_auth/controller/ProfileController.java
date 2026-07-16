package com.jwt_auth.controller;

import com.jwt_auth.constants.ApiEndpoints;
import com.jwt_auth.dto.response.ProfileResponse;
import com.jwt_auth.dto.response.common.ApiResponse;
import com.jwt_auth.service.ProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(ApiEndpoints.Profile.BASE)
public class ProfileController {
    private final ProfileService profileService;

    @GetMapping

    public ResponseEntity<ApiResponse<ProfileResponse>> getProfile(@AuthenticationPrincipal UserDetails currentUser) {
        return ResponseEntity.status(HttpStatus.OK).body(profileService.getProfile(currentUser.getUsername()));
    }
}

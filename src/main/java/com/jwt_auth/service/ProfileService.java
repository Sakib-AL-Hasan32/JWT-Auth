package com.jwt_auth.service;

import com.jwt_auth.dto.response.ProfileResponse;
import com.jwt_auth.dto.response.common.ApiResponse;

public interface ProfileService {
    ApiResponse<ProfileResponse> getProfile(String username);
}

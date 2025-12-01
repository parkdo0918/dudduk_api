package com.example.dudduk_api.domain.user.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class LoginResponse {

    private Long userId;
    private String nickname;
    private String accessToken;
    private String refreshToken;
}
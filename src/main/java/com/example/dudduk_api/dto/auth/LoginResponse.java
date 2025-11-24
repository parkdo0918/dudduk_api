package com.example.dudduk_api.dto.auth;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class LoginResponse {

    private String accessToken;  // 우리 서비스 JWT 토큰

    private String refreshToken;  // 리프레시 토큰

    private Long userId;

    private String email;

    private String name;

    private boolean isNewUser;  // 신규 가입 여부
}
package com.example.dudduk_api.dto.auth;

import com.example.dudduk_api.domain.user.SocialProvider;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class LoginRequest {

    private String accessToken;  // 카카오/구글에서 받은 토큰

    private SocialProvider provider;  // KAKAO or GOOGLE
}
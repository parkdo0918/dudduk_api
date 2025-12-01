package com.example.dudduk_api.domain.user.dto;

import com.example.dudduk_api.domain.user.entity.SocialType;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class LoginRequest {

    private SocialType socialType;
    private String socialId;
}
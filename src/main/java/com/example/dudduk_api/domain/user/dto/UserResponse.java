package com.example.dudduk_api.domain.user.dto;

import com.example.dudduk_api.domain.user.entity.User;
import lombok.Getter;

@Getter
public class UserResponse {

    private Long id;
    private String nickname;
    private String profileImageUrl;

    public UserResponse(User user) {
        this.id = user.getId();
        this.nickname = user.getNickname();
        this.profileImageUrl = user.getProfileImageUrl();
    }
}
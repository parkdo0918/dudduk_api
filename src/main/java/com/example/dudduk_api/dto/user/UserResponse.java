package com.example.dudduk_api.dto.user;

import com.example.dudduk_api.domain.user.Gender;
import com.example.dudduk_api.domain.user.User;
import lombok.Getter;

@Getter
public class UserResponse {

    private Long id;

    private String email;

    private String name;

    private String nickname;

    private Integer age;

    private Gender gender;

    private String profileImageUrl;

    public UserResponse(User user) {
        this.id = user.getId();
        this.email = user.getEmail();
        this.name = user.getName();
        this.nickname = user.getNickname();
        this.age = user.getAge();
        this.gender = user.getGender();
        this.profileImageUrl = user.getProfileImageUrl();
    }
}
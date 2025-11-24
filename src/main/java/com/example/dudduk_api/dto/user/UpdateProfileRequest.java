package com.example.dudduk_api.dto.user;

import com.example.dudduk_api.domain.user.Gender;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UpdateProfileRequest {

    private String name;

    private String nickname;

    private Integer age;

    private Gender gender;
}
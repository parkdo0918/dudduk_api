package com.example.dudduk_api.service;

import com.example.dudduk_api.domain.user.User;
import com.example.dudduk_api.domain.user.UserRepository;
import com.example.dudduk_api.dto.user.UpdateProfileRequest;
import com.example.dudduk_api.dto.user.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {

    private final UserRepository userRepository;

    // 내 정보 조회
    public UserResponse getMyProfile(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다."));

        return new UserResponse(user);
    }

    // 프로필 수정
    @Transactional
    public UserResponse updateProfile(Long userId, UpdateProfileRequest request) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다."));

        // 엔티티 업데이트 (Dirty Checking)
        user.updateProfile(request.getName(), request.getNickname(),
                request.getAge(), request.getGender());

        return new UserResponse(user);
    }
}
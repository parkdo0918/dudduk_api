package com.example.dudduk_api.domain.user.service;

import com.example.dudduk_api.domain.user.entity.SocialType;
import com.example.dudduk_api.domain.user.entity.User;
import com.example.dudduk_api.domain.user.repository.UserRepository;
import com.example.dudduk_api.domain.user.dto.UpdateProfileRequest;
import com.example.dudduk_api.domain.user.dto.UserResponse;
import com.example.dudduk_api.exception.UnauthorizedException;
import com.example.dudduk_api.exception.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {

    private final UserRepository userRepository;

    // 소셜 로그인 또는 회원가입
    @Transactional
    public User loginOrRegister(SocialType socialType, String socialId) {
        return userRepository.findBySocialTypeAndSocialId(socialType, socialId) // 로그인
                .orElseGet(() -> { // 정보 없으면 회원가입

                    User newUser = User.builder()
                            .socialType(socialType)
                            .socialId(socialId)
                            .build();
                    return userRepository.save(newUser);
                });
    }

    public User getUser(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("사용자를 찾을 수 없습니다."));
    }

    // Refresh Token DB 저장
    @Transactional
    public void updateRefreshToken(Long userId, String refreshToken) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("사용자를 찾을 수 없습니다."));

        user.updateRefreshToken(refreshToken, LocalDateTime.now().plusWeeks(2));
    }

    // Refresh Token으로 Access Token 재발급
    @Transactional
    public User refreshAccessToken(String refreshToken) {
        // 1. Refresh Token으로 사용자 조회
        User user = userRepository.findByRefreshToken(refreshToken)
                .orElseThrow(() -> new UnauthorizedException("유효하지 않은 Refresh Token입니다."));

        // 2. Refresh Token 만료 확인
        if (user.getRefreshTokenExpiresAt().isBefore(LocalDateTime.now())) {
            throw new UnauthorizedException("Refresh Token이 만료되었습니다. 다시 로그인해주세요.");
        }

        return user;
    }

    // 닉네임 중복 체크
    public boolean isNicknameAvailable(String nickname) {
        return !userRepository.existsByNickname(nickname);
    }

    // 프로필 수정
    @Transactional
    public UserResponse updateProfile(Long userId, UpdateProfileRequest request) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("사용자를 찾을 수 없습니다."));

        // 닉네임 변경 시 중복 체크
        if (request.getNickname() != null && !request.getNickname().equals(user.getNickname())) {
            if (!isNicknameAvailable(request.getNickname())) {
                throw new IllegalArgumentException("이미 사용 중인 닉네임입니다.");
            }
        }

        user.updateProfile(request.getNickname(), request.getProfileImageUrl());
        return new UserResponse(user);
    }


}
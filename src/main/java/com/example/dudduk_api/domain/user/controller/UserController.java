package com.example.dudduk_api.domain.user.controller;

import com.example.dudduk_api.domain.user.dto.*;
import com.example.dudduk_api.domain.user.entity.User;
import com.example.dudduk_api.domain.user.service.UserService;
import com.example.dudduk_api.security.JwtTokenProvider;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final JwtTokenProvider jwtTokenProvider;

    // 소셜 로그인
    @PostMapping("/auth/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) {
        User user = userService.loginOrRegister(
                request.getSocialType(),
                request.getSocialId()
        );

        // JWT 토큰 생성
        String accessToken = jwtTokenProvider.generateAccessToken(user.getId());
        String refreshToken = jwtTokenProvider.generateRefreshToken(user.getId());

        // Refresh Token DB 저장
        userService.updateRefreshToken(user.getId(), refreshToken);

        LoginResponse response = LoginResponse.builder()
                .userId(user.getId())
                .nickname(user.getNickname())
                .accessToken(accessToken)  // 토큰 전달
                .refreshToken(refreshToken)
                .build();

        return ResponseEntity.ok(response);
    }

    // 프로필 수정
    @PutMapping("/users/{userId}")
    public ResponseEntity<UserResponse> updateProfile(
            @PathVariable Long userId,
            @Valid @RequestBody UpdateProfileRequest request) {
        UserResponse response = userService.updateProfile(userId, request);
        return ResponseEntity.ok(response);
    }

    // 프로필 조회
    @GetMapping("/users/{userId}")
    public ResponseEntity<UserResponse> getMyProfile(@PathVariable Long userId) {
        UserResponse response = new UserResponse(userService.getUser(userId));
        return ResponseEntity.ok(response);
    }

    // 닉네임 중복 체크
    @GetMapping("/users/check-nickname")
    public ResponseEntity<Map<String, Boolean>> checkNickname(@RequestParam String nickname) {
        boolean isAvailable = userService.isNicknameAvailable(nickname);
        Map<String, Boolean> response = new HashMap<>();
        response.put("available", isAvailable);
        return ResponseEntity.ok(response);
    }


    // Refresh Token으로 Access Token 재발급
    @PostMapping("/auth/refresh")
    public ResponseEntity<RefreshTokenResponse> refreshToken(@RequestBody RefreshTokenRequest request) {
        // 1. Refresh Token 검증
        if (!jwtTokenProvider.validateToken(request.getRefreshToken())) {
            return ResponseEntity.status(401).build();
        }

        // 2. 사용자 조회
        User user = userService.refreshAccessToken(request.getRefreshToken());

        // 3. 새 Access Token 생성
        String newAccessToken = jwtTokenProvider.generateAccessToken(user.getId());

        // 4. 새 Refresh Token 생성 (선택: 갱신할지 말지)
        String newRefreshToken = jwtTokenProvider.generateRefreshToken(user.getId());
        userService.updateRefreshToken(user.getId(), newRefreshToken);

        RefreshTokenResponse response = RefreshTokenResponse.builder()
                .accessToken(newAccessToken)
                .refreshToken(newRefreshToken)
                .build();

        return ResponseEntity.ok(response);
    }
}

package com.example.dudduk_api.domain.user.entity;

import com.example.dudduk_api.domain.common.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "users")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String socialId;  // 카카오/구글 고유 ID

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 10)
    private SocialType socialType;  // KAKAO, GOOGLE

    @Column(unique = true, length = 50)
    private String nickname;

    private String profileImageUrl;  // 소셜 프로필 사진

    private LocalDateTime refreshTokenExpiresAt;

    // Refresh Token 관련 필드 추가
    @Column(length = 500)
    private String refreshToken;

    @Builder
    public User(String socialId, SocialType socialType, String email, String name,
                String nickname, String profileImageUrl) {
        this.socialId = socialId;
        this.socialType = socialType;
        this.nickname = nickname;
        this.profileImageUrl = profileImageUrl;
    }

    // 비즈니스 메서드
    //프로필 업데이트
    public void updateProfile(String nickname, String profileImageUrl) {
        if (nickname != null && !nickname.isBlank()) {
            this.nickname = nickname;
        }
        if (profileImageUrl != null && !profileImageUrl.isBlank()) {
            this.profileImageUrl = profileImageUrl;
        }
    }

    // Refresh Token 관련 메서드

    // 로그인 시 저장
    public void updateRefreshToken(String token, LocalDateTime expiresAt) {
        this.refreshToken = token;
        this.refreshTokenExpiresAt = expiresAt;
    }

    // 로그아웃 시 삭제
    public void clearRefreshToken() {
        this.refreshToken = null;
        this.refreshTokenExpiresAt = null;
    }

    // 유효성 체크
    public boolean isRefreshTokenValid() {
        if (this.refreshToken == null || this.refreshTokenExpiresAt == null) {
            return false;
        }
        return LocalDateTime.now().isBefore(this.refreshTokenExpiresAt);
    }
}
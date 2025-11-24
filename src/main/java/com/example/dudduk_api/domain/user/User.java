package com.example.dudduk_api.domain.user;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Table(name = "users")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class) // 접속
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String socialId;  // 카카오/구글 고유 ID

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 10)
    private SocialProvider provider;  // KAKAO, GOOGLE

    @Column(nullable = false, unique = true, length = 100)
    private String email;

    @Column(nullable = false, length = 50)
    private String name;

    @Column(unique = true, length = 50)
    private String nickname;

    private Integer age;

    @Enumerated(EnumType.STRING)
    @Column(length = 10)
    private Gender gender;

    private String profileImageUrl;  // 소셜 프로필 사진

    @CreatedDate
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(nullable = false)
    private LocalDateTime updatedAt;

    @Builder
    public User(String socialId, SocialProvider provider, String email, String name,
                String nickname, Integer age, Gender gender, String profileImageUrl) {
        this.socialId = socialId;
        this.provider = provider;
        this.email = email;
        this.name = name;
        this.nickname = nickname;
        this.age = age;
        this.gender = gender;
        this.profileImageUrl = profileImageUrl;
    }

    // 비즈니스 메서드
    public void updateProfile(String name, String nickname, Integer age, Gender gender) {
        this.name = name;
        this.nickname = nickname;
        this.age = age;
        this.gender = gender;
    }
}
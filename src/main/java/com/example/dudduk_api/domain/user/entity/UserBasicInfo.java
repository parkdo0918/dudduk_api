package com.example.dudduk_api.domain.user.entity;

import com.example.dudduk_api.domain.common.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Table(name = "user_basic_info")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserBasicInfo extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false, unique = true)
    private User user;

    // 유저 기본 정보 (회원가입 직후 입력)
    private Integer age;  // 나이

    private Integer height;  // 키 (cm)

    @Column(precision = 5, scale = 2)
    private BigDecimal weight;  // 몸무게 (kg)

    public UserBasicInfo(User user, Integer age, Integer height, BigDecimal weight) {
        this.user = user;
        this.age = age;
        this.height = height;
        this.weight = weight;
    }
}

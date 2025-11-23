package com.example.dudduk_api.domain.user;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "user_health_profiles")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
public class UserHealthProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false, unique = true)
    private User user;

    // 신체 정보
    private Integer height;  // 키 (cm)

    @Column(precision = 5, scale = 2)
    private BigDecimal weight;  // 몸무게 (kg)

    // 건강 프로필
    @Column(length = 255)
    private String painArea;  // 통증 부위

    @Column(length = 255)
    private String symptoms;  // 증상

    private Double averageSleepHours;  // 평균 수면 시간

    @Column(length = 255)
    private String activityType;  // 활동 유형

    @Column(length = 255)
    private String exerciseExperience;  // 운동 경험

    @Column(length = 50)
    private String preferredTime;  // 운동 선호 시간

    @Column(length = 50)
    private String exerciseFrequency;  // 운동 빈도

    @Column(columnDefinition = "TEXT")
    private String exerciseGoal;  // 운동 목표

    @CreatedDate
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(nullable = false)
    private LocalDateTime updatedAt;

    public UserHealthProfile(User user, Integer height, BigDecimal weight,
                             String painArea, String symptoms, Double averageSleepHours,
                             String activityType, String exerciseExperience,
                             String preferredTime, String exerciseFrequency,
                             String exerciseGoal) {
        this.user = user;
        this.height = height;
        this.weight = weight;
        this.painArea = painArea;
        this.symptoms = symptoms;
        this.averageSleepHours = averageSleepHours;
        this.activityType = activityType;
        this.exerciseExperience = exerciseExperience;
        this.preferredTime = preferredTime;
        this.exerciseFrequency = exerciseFrequency;
        this.exerciseGoal = exerciseGoal;
    }
}
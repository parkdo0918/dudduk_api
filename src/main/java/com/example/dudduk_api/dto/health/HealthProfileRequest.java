package com.example.dudduk_api.dto.health;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Getter
@NoArgsConstructor
public class HealthProfileRequest {

    // 신체 정보
    private Integer height;

    private BigDecimal weight;

    // 건강 정보
    private String painArea;

    private String symptoms;

    // 일상 생활 패턴
    private Double averageSleepHours;

    private String dailyPattern;

    // 운동 경험
    private String exerciseExperience;

    private String exerciseType;

    // 운동 선호
    private String preferredTime;

    private String exerciseFrequency;
}
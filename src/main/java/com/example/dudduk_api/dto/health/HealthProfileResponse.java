package com.example.dudduk_api.dto.health;

import com.example.dudduk_api.domain.user.UserHealthProfile;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
public class HealthProfileResponse {

    private Long id;

    private Integer height;

    private BigDecimal weight;

    private String painArea;

    private String symptoms;

    private Double averageSleepHours;

    private String dailyPattern;

    private String exerciseExperience;

    private String exerciseType;  // 수정!

    private String preferredTime;

    private String exerciseFrequency;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    public HealthProfileResponse(UserHealthProfile profile) {
        this.id = profile.getId();
        this.height = profile.getHeight();
        this.weight = profile.getWeight();
        this.painArea = profile.getPainArea();
        this.symptoms = profile.getSymptoms();
        this.averageSleepHours = profile.getAverageSleepHours();
        this.dailyPattern = profile.getDailyPattern();
        this.exerciseExperience = profile.getExerciseExperience();
        this.exerciseType = profile.getExerciseType();
        this.preferredTime = profile.getPreferredTime();
        this.exerciseFrequency = profile.getExerciseFrequency();
        this.createdAt = profile.getCreatedAt();
        this.updatedAt = profile.getUpdatedAt();
    }
}
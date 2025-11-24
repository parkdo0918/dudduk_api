package com.example.dudduk_api.dto.survey;

import com.example.dudduk_api.domain.survey.KneeSurvey;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class KneeSurveyResponse {

    private Long id;

    // 설문 응답
    private String painSide;

    private Integer painLevel;

    private Boolean increasedActivity;

    private String painLocation;

    private Boolean stairPain;

    private Boolean downhillPain;

    private Boolean injuryRelated;

    private String lockingSensation;

    private String swellingHeat;

    private String morningStiffness;

    private String redFlags;

    // AI 응답
    private String diagnosis;  // AI 진단 결과

    private String recommendation;  // AI 추천 운동

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    public KneeSurveyResponse(KneeSurvey survey) {
        this.id = survey.getId();
        this.painSide = survey.getPainSide();
        this.painLevel = survey.getPainLevel();
        this.increasedActivity = survey.getIncreasedActivity();
        this.painLocation = survey.getPainLocation();
        this.stairPain = survey.getStairPain();
        this.downhillPain = survey.getDownhillPain();
        this.injuryRelated = survey.getInjuryRelated();
        this.lockingSensation = survey.getLockingSensation();
        this.swellingHeat = survey.getSwellingHeat();
        this.morningStiffness = survey.getMorningStiffness();
        this.redFlags = survey.getRedFlags();
        this.diagnosis = survey.getDiagnosis();
        this.recommendation = survey.getRecommendation();
        this.createdAt = survey.getCreatedAt();
        this.updatedAt = survey.getUpdatedAt();
    }
}
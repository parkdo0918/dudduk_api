package com.example.dudduk_api.domain.user.entity;

import com.example.dudduk_api.domain.common.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "pain_surveys")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PainSurvey extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    // 통증 부위
    @Column(nullable = false, length = 50)
    private String painArea;  // "무릎", "허리", "어깨" 등

    // 통증 기본 정보
    @Column(length = 20)
    private String affectedSide;  // 아픈 쪽 ("왼쪽", "오른쪽", "양쪽")

    private LocalDate painStartedDate;  // 언제부터

    // 통증 세부 정보
    private Integer painLevel;  // 아픈 정도 (0-10)

    @Column(length = 255)
    private String painTrigger;  // 언제 통증이 더 심한지

    @Column(length = 255)
    private String painSensation;  // 어떤 느낌으로 아픈지

    @Column(length = 100)
    private String painDuration;  // 지속 시간

    // 위험 신호
    @Column(columnDefinition = "TEXT")
    private String redFlags;  // JSON 형식 또는 콤마 구분

    // AI 진단 결과
    @Column(columnDefinition = "TEXT")
    private String diagnosis;  // AI 진단

    @Column(columnDefinition = "TEXT")
    private String recommendation;  // AI 추천 운동/조치사항

    public PainSurvey(User user, String painArea, String affectedSide,
                      LocalDate painStartedDate, Integer painLevel,
                      String painTrigger, String painSensation,
                      String painDuration, String redFlags) {
        this.user = user;
        this.painArea = painArea;
        this.affectedSide = affectedSide;
        this.painStartedDate = painStartedDate;
        this.painLevel = painLevel;
        this.painTrigger = painTrigger;
        this.painSensation = painSensation;
        this.painDuration = painDuration;
        this.redFlags = redFlags;
    }

    // AI 응답 저장 메서드
    public void updateAIResponse(String diagnosis, String recommendation) {
        this.diagnosis = diagnosis;
        this.recommendation = recommendation;
    }
}
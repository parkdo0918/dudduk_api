package com.example.dudduk_api.domain.survey;

import com.example.dudduk_api.domain.user.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Table(name = "knee_surveys")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
public class KneeSurvey {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    // Q1. 통증 위치
    @Column(length = 20)
    private String painSide;  // "왼쪽", "오른쪽", "양쪽 모두"

    // Q2. 통증 정도 (VAS)
    private Integer painLevel;  // 0-10

    // Q3. 운동량 증가 여부
    private Boolean increasedActivity;

    // Q4. 통증 부위
    @Column(length = 50)
    private String painLocation;  // "내측", "외측", "앞쪽", "잘 모르겠다"

    // Q5. 계단 통증
    private Boolean stairPain;

    // Q6. 내리막길 통증
    private Boolean downhillPain;

    // Q7. 부상 여부
    private Boolean injuryRelated;

    // Q8. 무릎 잠김
    @Column(length = 20)
    private String lockingSensation;  // "자주", "가끔", "없다"

    // Q9. 붓기/열감
    @Column(length = 20)
    private String swellingHeat;  // "많이", "약간", "없다"

    // Q10. 조조강직
    @Column(length = 20)
    private String morningStiffness;  // "30분 이상", "15-30분", "5-15분", "거의 없음"

    // Q11. Red Flag (JSON 문자열)
    @Column(columnDefinition = "TEXT")
    private String redFlags;  // JSON: ["심한통증", "붓기와열감", ...]

    // AI 진단 결과
    @Column(columnDefinition = "TEXT")
    private String diagnosis;  // AI가 반환한 진단 결과

    @Column(columnDefinition = "TEXT")
    private String recommendation;  // AI 추천 운동/조치사항

    @CreatedDate
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(nullable = false)
    private LocalDateTime updatedAt;

    public KneeSurvey(User user, String painSide, Integer painLevel,
                      Boolean increasedActivity, String painLocation,
                      Boolean stairPain, Boolean downhillPain,
                      Boolean injuryRelated, String lockingSensation,
                      String swellingHeat, String morningStiffness,
                      String redFlags) {
        this.user = user;
        this.painSide = painSide;
        this.painLevel = painLevel;
        this.increasedActivity = increasedActivity;
        this.painLocation = painLocation;
        this.stairPain = stairPain;
        this.downhillPain = downhillPain;
        this.injuryRelated = injuryRelated;
        this.lockingSensation = lockingSensation;
        this.swellingHeat = swellingHeat;
        this.morningStiffness = morningStiffness;
        this.redFlags = redFlags;
    }

    // AI 응답 저장 메서드
    public void updateAIResponse(String diagnosis, String recommendation) {
        this.diagnosis = diagnosis;
        this.recommendation = recommendation;
    }
}
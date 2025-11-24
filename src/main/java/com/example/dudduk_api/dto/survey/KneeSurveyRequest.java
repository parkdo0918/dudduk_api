package com.example.dudduk_api.dto.survey;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class KneeSurveyRequest {

    // Q1. 통증 위치
    private String painSide;  // "왼쪽", "오른쪽", "양쪽 모두"

    // Q2. 통증 정도 (VAS)
    private Integer painLevel;  // 0-10

    // Q3. 운동량 증가 여부
    private Boolean increasedActivity;

    // Q4. 통증 부위
    private String painLocation;  // "내측", "외측", "앞쪽", "잘 모르겠다"

    // Q5. 계단 통증
    private Boolean stairPain;

    // Q6. 내리막길 통증
    private Boolean downhillPain;

    // Q7. 부상 여부
    private Boolean injuryRelated;

    // Q8. 무릎 잠김
    private String lockingSensation;  // "자주", "가끔", "없다"

    // Q9. 붓기/열감
    private String swellingHeat;  // "많이", "약간", "없다"

    // Q10. 조조강직
    private String morningStiffness;  // "30분 이상", "15-30분", "5-15분", "거의 없음"

    // Q11. Red Flag
    private String redFlags;  // JSON 문자열
}
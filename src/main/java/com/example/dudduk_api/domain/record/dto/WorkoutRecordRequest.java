package com.example.dudduk_api.domain.record.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@NoArgsConstructor
public class WorkoutRecordRequest {

    private String exerciseId;  // 운동 ID

    private Long workoutRoutineId;  // 루틴 ID (선택, null 가능)

    private LocalDate date;  // 운동 날짜

    private Integer actualSets;  // 실제 수행 세트

    private Integer actualReps;  // 실제 수행 횟수

    private Boolean completed;  // 완료 여부
}
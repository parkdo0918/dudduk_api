package com.example.dudduk_api.domain.routine.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class RoutineRequest {

    private String name;  // 루틴 이름

    private String description;  // 루틴 설명

    private List<RoutineExerciseRequest> exercises;  // 포함된 운동 목록

    @Getter
    @NoArgsConstructor
    public static class RoutineExerciseRequest {

        private String exerciseId;  // 운동 ID (E01, E02, ...)

        private Integer customSets;  // 커스텀 세트 (null이면 기본값)

        private Integer customReps;  // 커스텀 횟수 (null이면 기본값)

        private Integer exerciseOrder;  // 운동 순서
    }
}
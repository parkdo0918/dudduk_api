package com.example.dudduk_api.dto.routine;

import com.example.dudduk_api.domain.routine.WorkoutRoutine;
import com.example.dudduk_api.domain.routine.WorkoutRoutineExercise;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class RoutineResponse {

    private Long id;

    private String name;

    private String description;

    private Boolean isActive;

    private Integer exerciseCount;  // 운동 개수

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    public RoutineResponse(WorkoutRoutine routine, int exerciseCount) {
        this.id = routine.getId();
        this.name = routine.getName();
        this.description = routine.getDescription();
        this.isActive = routine.getIsActive();
        this.exerciseCount = exerciseCount;
        this.createdAt = routine.getCreatedAt();
        this.updatedAt = routine.getUpdatedAt();
    }
}
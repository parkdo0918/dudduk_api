package com.example.dudduk_api.domain.routine.dto;

import com.example.dudduk_api.domain.routine.entity.WorkoutRoutine;
import com.example.dudduk_api.domain.routine.entity.WorkoutRoutineExercise;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

// 루틴+ 루틴 운동 상세정보
@Getter
public class RoutineDetailResponse {

    private Long id;

    private String name;

    private String description;

    private Boolean isActive;

    private List<ExerciseInRoutine> exercises;  // 포함된 운동 상세

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    @Getter
    public static class ExerciseInRoutine {

        private String exerciseId;

        private String nameKo;

        private String nameEn;

        private String difficulty;

        private Integer customSets;

        private Integer customReps;

        private String videoUrl;

        public ExerciseInRoutine(WorkoutRoutineExercise mapping) {
            this.exerciseId = mapping.getExercise().getId();
            this.nameKo = mapping.getExercise().getNameKo();
            this.nameEn = mapping.getExercise().getNameEn();
            this.difficulty = mapping.getExercise().getDifficulty();
            this.customSets = mapping.getCustomSets();
            this.customReps = mapping.getCustomReps();
            this.videoUrl = mapping.getExercise().getVideoUrl();
        }
    }

    public RoutineDetailResponse(WorkoutRoutine routine, List<WorkoutRoutineExercise> mappings) {
        this.id = routine.getId();
        this.name = routine.getName();
        this.exercises = mappings.stream()
                .map(ExerciseInRoutine::new)
                .collect(Collectors.toList());
        this.createdAt = routine.getCreatedAt();
        this.updatedAt = routine.getUpdatedAt();
    }
}
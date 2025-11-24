package com.example.dudduk_api.dto.record;

import com.example.dudduk_api.domain.record.WorkoutRecord;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
public class WorkoutRecordResponse {

    private Long id;

    private String exerciseId;

    private String exerciseName;  // 운동 이름 (편의용)

    private Long workoutRoutineId;

    private String routineName;  // 루틴 이름 (편의용, null 가능)

    private LocalDate date;

    private Integer actualSets;

    private Integer actualReps;

    private Boolean completed;

    private LocalDateTime createdAt;

    public WorkoutRecordResponse(WorkoutRecord record) {
        this.id = record.getId();
        this.exerciseId = record.getExercise().getId();
        this.exerciseName = record.getExercise().getNameKo();
        this.workoutRoutineId = record.getWorkoutRoutine() != null ?
                record.getWorkoutRoutine().getId() : null;
        this.routineName = record.getWorkoutRoutine() != null ?
                record.getWorkoutRoutine().getName() : null;
        this.date = record.getDate();
        this.actualSets = record.getActualSets();
        this.actualReps = record.getActualReps();
        this.completed = record.getCompleted();
        this.createdAt = record.getCreatedAt();
    }
}
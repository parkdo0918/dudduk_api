package com.example.dudduk_api.domain.routine.entity;

import com.example.dudduk_api.domain.common.BaseTimeEntity;
import com.example.dudduk_api.domain.exercise.entity.Exercise;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "workout_routine_exercises")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class WorkoutRoutineExercise extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "workout_routine_id", nullable = false)
    private WorkoutRoutine workoutRoutine;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "exercise_id", nullable = false)
    private Exercise exercise;

    private Integer customSets;  // ai 추천세트

    private Integer customReps;  // ai 추천횟수

    public WorkoutRoutineExercise(WorkoutRoutine workoutRoutine, Exercise exercise,
                                  Integer customSets, Integer customReps, Integer order) {
        this.workoutRoutine = workoutRoutine;
        this.exercise = exercise;
        this.customSets = customSets;
        this.customReps = customReps;
    }
}
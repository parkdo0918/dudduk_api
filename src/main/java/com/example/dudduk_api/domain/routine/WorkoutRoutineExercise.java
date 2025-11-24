package com.example.dudduk_api.domain.routine;

import com.example.dudduk_api.domain.exercise.Exercise;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Table(name = "workout_routine_exercises")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
public class WorkoutRoutineExercise {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "workout_routine_id", nullable = false)
    private WorkoutRoutine workoutRoutine;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "exercise_id", nullable = false)
    private Exercise exercise;

    private Integer customSets;  // 사용자 커스텀 세트 (NULL이면 Exercise의 기본값 사용)

    private Integer customReps;  // 사용자 커스텀 횟수 (NULL이면 Exercise의 기본값 사용)

    @Column(name = "exercise_order", nullable = false)
    private Integer exerciseOrder; // 운동 순서 (1, 2, 3, ...)

    @CreatedDate
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    public WorkoutRoutineExercise(WorkoutRoutine workoutRoutine, Exercise exercise,
                                  Integer customSets, Integer customReps, Integer order) {
        this.workoutRoutine = workoutRoutine;
        this.exercise = exercise;
        this.customSets = customSets;
        this.customReps = customReps;
        this.exerciseOrder = order;
    }
}
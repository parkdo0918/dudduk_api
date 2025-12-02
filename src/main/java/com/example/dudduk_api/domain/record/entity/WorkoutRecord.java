package com.example.dudduk_api.domain.record.entity;

import com.example.dudduk_api.domain.common.BaseTimeEntity;
import com.example.dudduk_api.domain.exercise.entity.Exercise;
import com.example.dudduk_api.domain.user.entity.User;
import com.example.dudduk_api.domain.routine.entity.WorkoutRoutine;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "workout_records")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class WorkoutRecord extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "exercise_id", nullable = false)
    private Exercise exercise;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "workout_routine_id")
    private WorkoutRoutine workoutRoutine;  // 어떤 루틴으로 했는지 (선택)

    @Column(nullable = false)
    private LocalDate date;  // 운동 날짜

    private Integer actualSets;  // 실제 수행 세트

    private Integer actualReps;  // 실제 수행 횟수

    @Column(nullable = false)
    private Boolean completed = false;  // 완료 여부

    public WorkoutRecord(User user, Exercise exercise, WorkoutRoutine workoutRoutine,
                         LocalDate date, Integer actualSets, Integer actualReps,
                         Boolean completed) {
        this.user = user;
        this.exercise = exercise;
        this.workoutRoutine = workoutRoutine;
        this.date = date;
        this.actualSets = actualSets;
        this.actualReps = actualReps;
        this.completed = completed;
    }
}
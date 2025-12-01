package com.example.dudduk_api.domain.routine;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface WorkoutRoutineExerciseRepository extends JpaRepository<WorkoutRoutineExercise, Long> {

    List<WorkoutRoutineExercise> findByWorkoutRoutineIdOrderByExerciseOrder(Long workoutRoutineId);

    void deleteByWorkoutRoutineId(Long workoutRoutineId);

    boolean existsByWorkoutRoutineIdAndExerciseId(Long workoutRoutineId, String exerciseId);
}
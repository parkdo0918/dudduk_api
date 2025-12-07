package com.example.dudduk_api.domain.routine.repository;

import com.example.dudduk_api.domain.routine.entity.WorkoutRoutine;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface WorkoutRoutineRepository extends JpaRepository<WorkoutRoutine, Long> {

    List<WorkoutRoutine> findByUserIdOrderByCreatedAtDesc(Long userId);

    boolean existsByUserIdAndName(Long userId, String name);
}
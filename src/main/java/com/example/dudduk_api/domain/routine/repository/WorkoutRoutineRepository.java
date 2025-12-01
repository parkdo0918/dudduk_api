package com.example.dudduk_api.domain.routine;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface WorkoutRoutineRepository extends JpaRepository<WorkoutRoutine, Long> {

    List<WorkoutRoutine> findByUserIdOrderByCreatedAtDesc(Long userId);

    List<WorkoutRoutine> findByUserIdAndIsActiveOrderByCreatedAtDesc(Long userId, Boolean isActive);

    boolean existsByUserIdAndName(Long userId, String name);
}
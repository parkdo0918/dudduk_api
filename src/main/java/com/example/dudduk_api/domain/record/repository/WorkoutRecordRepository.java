package com.example.dudduk_api.domain.record;

import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDate;
import java.util.List;

public interface WorkoutRecordRepository extends JpaRepository<WorkoutRecord, Long> {

    List<WorkoutRecord> findByUserIdAndDateBetweenOrderByDateDesc(
            Long userId, LocalDate startDate, LocalDate endDate
    );

    List<WorkoutRecord> findByUserIdOrderByDateDesc(Long userId);

    List<WorkoutRecord> findByUserIdAndDate(Long userId, LocalDate date);

    Long countByUserIdAndCompleted(Long userId, Boolean completed);
}
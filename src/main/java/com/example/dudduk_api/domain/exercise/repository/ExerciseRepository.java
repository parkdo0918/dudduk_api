package com.example.dudduk_api.domain.exercise.repository;

import com.example.dudduk_api.domain.exercise.entity.Exercise;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExerciseRepository extends JpaRepository<Exercise, String> {
}

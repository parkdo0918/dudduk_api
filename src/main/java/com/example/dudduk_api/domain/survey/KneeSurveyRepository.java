package com.example.dudduk_api.domain.survey;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface KneeSurveyRepository extends JpaRepository<KneeSurvey, Long> {

    List<KneeSurvey> findByUserIdOrderByCreatedAtDesc(Long userId);

    Optional<KneeSurvey> findTopByUserIdOrderByCreatedAtDesc(Long userId);
}
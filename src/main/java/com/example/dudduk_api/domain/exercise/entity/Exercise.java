package com.example.dudduk_api.domain.exercise.entity;

import com.example.dudduk_api.domain.common.BaseTimeEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "exercise")
public class Exercise extends BaseTimeEntity {

    @Id
    @Column(name = "exercise_id", length = 10)
    private String id;

    @Column(name = "name_ko", nullable = false, length = 100)
    private String nameKo;

    @Column(name = "name_en", length = 100)
    private String nameEn;

    @Column(length = 20)
    private String difficulty;

    @Column(name = "diagnosis_tag", length = 255)
    private String diagnosisTag;

    @Column(name = "function_tag", length = 255)
    private String functionTag;

    @Column(name = "main_muscle", length = 255)
    private String mainMuscle;

    @Column(name = "default_sets")
    private Integer defaultSets;

    @Column(name = "default_reps")
    private Integer defaultReps;

    @Column(name = "rest_time")
    private Integer restTime;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(name = "video_url", length = 500)
    private String videoUrl;

    // 생성자
    public Exercise(String id, String nameKo, String nameEn, String difficulty) {
        this.id = id;
        this.nameKo = nameKo;
        this.nameEn = nameEn;
        this.difficulty = difficulty;
    }
}

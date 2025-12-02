package com.example.dudduk_api.domain.routine.entity;

import com.example.dudduk_api.domain.common.BaseTimeEntity;
import com.example.dudduk_api.domain.user.entity.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "workout_routines")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class WorkoutRoutine extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(nullable = false, length = 100)
    private String name;  // 루틴 이름 (예: "오늘의 루틴, 내일의 루틴")

    @Column(nullable = false)
    private Boolean isFavorite = false;  // 즐겨찾기 여부

    public WorkoutRoutine(User user, String name) {
        this.user = user;
        this.name = name;
    }

    public void toggleFavorite() {
        this.isFavorite = !this.isFavorite;
    }
}
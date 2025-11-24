### 1. User (회원)

- `id` (PK, BIGINT, AUTO_INCREMENT)
- `social_id` (VARCHAR(255), UNIQUE, NOT NULL) - 카카오/구글 고유 ID
- `provider` (ENUM: 'KAKAO', 'GOOGLE', NOT NULL) - 소셜 로그인 제공자
- `email` (VARCHAR(100), UNIQUE, NOT NULL)
- `name` (VARCHAR(50), NOT NULL)
- `nickname` (VARCHAR(50), UNIQUE)
- `age` (INT)
- `gender` (ENUM: 'MALE', 'FEMALE', 'OTHER')
- `profile_image_url` (VARCHAR(255)) - 소셜 프로필 사진
- `created_at` (TIMESTAMP)
- `updated_at` (TIMESTAMP)

**관계:** User 1:1 UserHealthProfile, User 1:N WorkoutRoutine, User 1:N BodyRecord, User 1:N WorkoutRecord

**비고:** 소셜 로그인 전용 (password 필드 없음)

---

### 2. UserHealthProfile (회원 건강 프로필)
- `id` (PK, BIGINT, AUTO_INCREMENT)
- `user_id` (FK → User.id, NOT NULL, UNIQUE)

**신체 정보:**
- `height` (INT) - 키 (cm)
- `weight` (DECIMAL(5,2)) - 몸무게 (kg)

**건강 정보:**
- `pain_area` (VARCHAR(255)) - 통증 부위 (예: "무릎,발목")
- 
**통증 기본 정보:**
- `symptoms` (VARCHAR(255)) - 증상

**일상 생활 패턴:**
- `average_sleep_hours` (DOUBLE) - 평균 수면 시간
- `daily_pattern` (VARCHAR(255)) - 생활 패턴

**운동 경험:**
- `exercise_experience` (VARCHAR(255)) - 운동 경험
- `exercise_type` (VARCHAR(255)) - 운동 유형
- 
**운동 선호시간:**
- `exercise_frequency` (VARCHAR(50)) - 운동 빈도 (예: "주 3~4회")
- `preferred_time` (VARCHAR(50)) - 운동 선호 시간 (예: "20분")

- `created_at` (TIMESTAMP)
- `updated_at` (TIMESTAMP)

**관계:** User 1 : 1 UserHealthProfile

---

### 3. Exercise (운동 종류 - 마스터 데이터)

- `id` (PK, VARCHAR(10)) - 코드 (E01, E02, ...)
- `name_ko` (VARCHAR(100), NOT NULL) - 한글명
- `name_en` (VARCHAR(100)) - 영문명
- `difficulty` (ENUM: '하', '중', '상')
- `diagnosis_tag` (VARCHAR(100)) - 질환 분류 (INF, TRM, OA, OVR)
- `function_tag` (VARCHAR(100)) - 기능 분류 (Mobility, Strength, ...)
- `main_muscle` (VARCHAR(100)) - 주요 근육
- `default_sets` (INT) - 기본 세트
- `default_reps` (INT) - 기본 횟수
- `rest_time` (INT) - 휴식 시간 (초)
- `description` (TEXT) - 설명
- `video_url` (VARCHAR(255)) - 영상 링크
- `created_at` (TIMESTAMP)

**비고:** 50개 운동 데이터 미리 INSERT

---

### 4. WorkoutRoutine (운동 루틴)

- `id` (PK, BIGINT, AUTO_INCREMENT)
- `user_id` (FK → User.id, NOT NULL)
- `name` (VARCHAR(100), NOT NULL) - 루틴 이름 (예: "월요일 루틴")
- `description` (TEXT) - 루틴 설명
- `is_active` (BOOLEAN, DEFAULT TRUE) - 활성화 여부
- `created_at` (TIMESTAMP)
- `updated_at` (TIMESTAMP)

**관계:** User 1 : N WorkoutRoutine

---

### 5. WorkoutRoutineExercise (루틴-운동 매핑)

- `id` (PK, BIGINT, AUTO_INCREMENT)
- `workout_routine_id` (FK → WorkoutRoutine.id, NOT NULL)
- `exercise_id` (FK → Exercise.id, NOT NULL)
- `custom_sets` (INT) - 사용자 커스텀 세트 (NULL이면 기본값 사용)
- `custom_reps` (INT) - 사용자 커스텀 횟수
- `order` (INT, NOT NULL) - 운동 순서
- `created_at` (TIMESTAMP)

**관계:**

- WorkoutRoutine 1 : N WorkoutRoutineExercise
- Exercise 1 : N WorkoutRoutineExercise

**비고:** N:M 관계 해소용 중간 테이블

---

### 6. WorkoutRecord (운동 기록)

- `id` (PK, BIGINT, AUTO_INCREMENT)
- `user_id` (FK → User.id, NOT NULL)
- `exercise_id` (FK → Exercise.id, NOT NULL)
- `workout_routine_id` (FK → WorkoutRoutine.id, NULLABLE) - 어떤 루틴으로 했는지 (선택)
- `date` (DATE, NOT NULL) - 운동 날짜
- `actual_sets` (INT) - 실제 수행 세트
- `actual_reps` (INT) - 실제 수행 횟수
- `completed` (BOOLEAN, DEFAULT FALSE) - 완료 여부
- `created_at` (TIMESTAMP)

**관계:**

- User 1 : N WorkoutRecord
- Exercise 1 : N WorkoutRecord
- WorkoutRoutine 1 : N WorkoutRecord (NULLABLE)

---

### 7. KneeSurvey (무릎 설문 응답)
- `id` (PK, BIGINT, AUTO_INCREMENT)
- `user_id` (FK → User.id, NOT NULL)

**설문 응답:**
- `pain_side` (VARCHAR(20)) - 통증 위치 ("왼쪽", "오른쪽", "양쪽 모두")
- `pain_level` (INT) - 통증 정도 (0-10, VAS 척도)
- `increased_activity` (BOOLEAN) - 최근 운동량 증가 여부
- `pain_location` (VARCHAR(50)) - 통증 부위 ("내측", "외측", "앞쪽", "잘 모르겠다")
- `stair_pain` (BOOLEAN) - 계단 통증 여부
- `downhill_pain` (BOOLEAN) - 내리막길 통증 여부
- `injury_related` (BOOLEAN) - 부상 관련 여부
- `locking_sensation` (VARCHAR(20)) - 무릎 잠김 ("자주", "가끔", "없다")
- `swelling_heat` (VARCHAR(20)) - 붓기/열감 ("많이", "약간", "없다")
- `morning_stiffness` (VARCHAR(20)) - 조조강직 ("30분 이상", "15-30분", "5-15분", "거의 없음")
- `red_flags` (TEXT) - Red Flag 체크 항목 (JSON 형식)

**AI 응답:**
- `diagnosis` (TEXT) - AI 진단 결과
- `recommendation` (TEXT) - AI 추천 운동/조치사항

- `created_at` (TIMESTAMP)
- `updated_at` (TIMESTAMP)

**관계:** User 1 : N KneeSurvey

**비고:** 통증 부위별 설문 (현재는 무릎만, 향후 다른 부위 추가 가능)

## ERD 다이어그램 (관계 요약)

```
┌─────────────┐
│    User     │
└──────┬──────┘
       │
       ├──────────────────────────────────────────┬──────────────────┐
       │                                          │                  │
       │ 1:1                                      │ 1:N              │ 1:N
       ▼                                          ▼                  ▼
┌──────────────────┐                    ┌─────────────────┐  ┌─────────────┐
│ UserHealthProfile│                    │ WorkoutRoutine  │  │ KneeSurvey  │
└──────────────────┘                    └────────┬────────┘  └─────────────┘
                                                 │
                                                 │ 1:N
       ┌─────────────────────────────────────────┤
       │                                          │
       │ 1:N                                      ▼
       ▼                                ┌──────────────────────┐
┌──────────────┐                       │WorkoutRoutineExercise│◄─────┐
│WorkoutRecord │                       └──────────┬───────────┘      │
└──────┬───────┘                                  │                  │
       │                                          │ N:1              │ N:1
       │ N:1                                      ▼                  │
       └────────────────────────────────► ┌─────────────┐            │
                                          │  Exercise   │────────────┘
                                          └─────────────┘
  (User와 연결)
```

---
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
- `user_id` (FK → User.id, NOT NULL)
- `pain_area` (VARCHAR(255)) - 통증 부위 (예: "무릎,발목")
- `symptoms` (VARCHAR(255)) - 증상
- `average_sleep_hours` (DOUBLE) - 평균 수면 시간
- `activity_type` (VARCHAR(255)) - 활동 유형
- `exercise_experience` (VARCHAR(255)) - 운동 경험
- `preferred_time` (VARCHAR(50)) - 예: "아침", "점심", "저녁"
- `exercise_frequency` (VARCHAR(50)) - 예: "주 3회", "주 5회", "매일"
- `exercise_goal` (TEXT) - 예: "무릎 통증 완화", "근력 강화"
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
- `memo` (TEXT) - 메모
- `created_at` (TIMESTAMP)

**관계:**

- User 1 : N WorkoutRecord
- Exercise 1 : N WorkoutRecord
- WorkoutRoutine 1 : N WorkoutRecord (NULLABLE)

---

# 뚜둑 프로젝트 API 명세서

## 기본 정보

- **Base URL**: `http://localhost:8080/api/v1`
- **인증 방식**: JWT (Bearer Token)
- **Content-Type**: `application/json`

---

## 목차

1. [인증/회원](#1-인증회원)
2. [회원 건강 프로필](#2-회원-건강-프로필)
3. [운동 종류](#3-운동-종류)
4. [운동 루틴](#4-운동-루틴)
5. [운동 기록](#5-운동-기록)

---

## 1. 인증/회원

### 1.1 회원가입

**Endpoint**: `POST /auth/signup`

**Request Body**:
```json
{
  "email": "dohyun@example.com",
  "name": "박도현",
  "password": "password123!",
  "nickname": "뚜둑러",
  "age": 25,
  "gender": "MALE"
}
```

**Response** (201 Created):
```json
{
  "success": true,
  "message": "회원가입 성공",
  "data": {
    "userId": 1,
    "email": "dohyun@example.com",
    "name": "박도현",
    "nickname": "뚜둑러"
  }
}
```

**Error Response** (400 Bad Request):
```json
{
  "success": false,
  "message": "이미 존재하는 이메일입니다.",
  "errorCode": "DUPLICATE_EMAIL"
}
```

---

### 1.2 로그인

**Endpoint**: `POST /auth/login`

**Request Body**:
```json
{
  "email": "dohyun@example.com",
  "password": "password123!"
}
```

**Response** (200 OK):
```json
{
  "success": true,
  "message": "로그인 성공",
  "data": {
    "accessToken": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...",
    "refreshToken": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...",
    "userId": 1,
    "email": "dohyun@example.com",
    "name": "박도현"
  }
}
```

**Error Response** (401 Unauthorized):
```json
{
  "success": false,
  "message": "이메일 또는 비밀번호가 올바르지 않습니다.",
  "errorCode": "INVALID_CREDENTIALS"
}
```

---

### 1.3 로그아웃

**Endpoint**: `POST /auth/logout`

**Headers**:
```
Authorization: Bearer {accessToken}
```

**Response** (200 OK):
```json
{
  "success": true,
  "message": "로그아웃 성공"
}
```

---

### 1.4 내 정보 조회

**Endpoint**: `GET /users/me`

**Headers**:
```
Authorization: Bearer {accessToken}
```

**Response** (200 OK):
```json
{
  "success": true,
  "data": {
    "userId": 1,
    "email": "dohyun@example.com",
    "name": "박도현",
    "nickname": "뚜둑러",
    "age": 25,
    "gender": "MALE",
    "createdAt": "2024-11-14T10:00:00"
  }
}
```

---

## 2. 회원 건강 프로필

### 2.1 건강 프로필 등록/수정

**Endpoint**: `POST /health-profiles` (등록)  
**Endpoint**: `PUT /health-profiles` (수정)

**Headers**:
```
Authorization: Bearer {accessToken}
```

**Request Body**:
```json
{
  "painArea": "목,어깨,무릎",
  "symptoms": "계단 내려갈 때 통증, VAS 6점",
  "dailyPattern": "수면 6시간, 정적 활동, 운동 경험 적음",
  "preferredTime": "20분",
  "exerciseFrequency": "주 3~4회",
  "exerciseGoal": "통증완화, 근력향상"
}
```

**Response** (200 OK):
```json
{
  "success": true,
  "message": "건강 프로필이 저장되었습니다.",
  "data": {
    "id": 1,
    "userId": 1,
    "painArea": "목,어깨,무릎",
    "symptoms": "계단 내려갈 때 통증, VAS 6점",
    "dailyPattern": "수면 6시간, 정적 활동, 운동 경험 적음",
    "preferredTime": "20분",
    "exerciseFrequency": "주 3~4회",
    "exerciseGoal": "통증완화, 근력향상",
    "createdAt": "2024-11-14T10:30:00",
    "updatedAt": "2024-11-14T10:30:00"
  }
}
```

---

### 2.2 건강 프로필 조회

**Endpoint**: `GET /health-profiles`

**Headers**:
```
Authorization: Bearer {accessToken}
```

**Response** (200 OK):
```json
{
  "success": true,
  "data": {
    "id": 1,
    "userId": 1,
    "painArea": "목,어깨,무릎",
    "symptoms": "계단 내려갈 때 통증, VAS 6점",
    "dailyPattern": "수면 6시간, 정적 활동, 운동 경험 적음",
    "preferredTime": "20분",
    "exerciseFrequency": "주 3~4회",
    "exerciseGoal": "통증완화, 근력향상",
    "createdAt": "2024-11-14T10:30:00",
    "updatedAt": "2024-11-14T10:30:00"
  }
}
```

---

## 3. 운동 종류

### 3.1 전체 운동 목록 조회

**Endpoint**: `GET /exercises`

**Query Parameters**:
- `difficulty` (optional): 하, 중, 상
- `diagnosisTag` (optional): INF, TRM, OA, OVR
- `functionTag` (optional): Mobility, Strength, Balance, etc.

**Example**: `GET /exercises?difficulty=하&diagnosisTag=INF`

**Response** (200 OK):
```json
{
  "success": true,
  "data": [
    {
      "id": "E01",
      "nameKo": "힐 슬라이드",
      "nameEn": "Heel Slide",
      "difficulty": "하",
      "diagnosisTag": "INF, TRM",
      "functionTag": "Mobility",
      "mainMuscle": "햄스트링, 대퇴사두근",
      "defaultSets": 2,
      "defaultReps": 15,
      "restTime": 30,
      "description": "누워서 발꿈치를 엉덩이 쪽으로 미끄러뜨려 무릎 가동범위 회복",
      "videoUrl": "https://youtu.be/Er-Fl_poWDk"
    },
    {
      "id": "E02",
      "nameKo": "발목 펌프",
      "nameEn": "Ankle Pump",
      "difficulty": "하",
      "diagnosisTag": "INF, TRM",
      "functionTag": "Mobility, Circulation",
      "mainMuscle": "종아리, 발목",
      "defaultSets": 2,
      "defaultReps": 20,
      "restTime": 20,
      "description": "발끝을 위아래로 반복해 순환 촉진",
      "videoUrl": "https://youtu.be/IlT7SBEz33E"
    }
  ],
  "totalCount": 50
}
```

---

### 3.2 운동 상세 조회

**Endpoint**: `GET /exercises/{exerciseId}`

**Example**: `GET /exercises/E01`

**Response** (200 OK):
```json
{
  "success": true,
  "data": {
    "id": "E01",
    "nameKo": "힐 슬라이드",
    "nameEn": "Heel Slide",
    "difficulty": "하",
    "diagnosisTag": "INF, TRM",
    "functionTag": "Mobility",
    "mainMuscle": "햄스트링, 대퇴사두근",
    "defaultSets": 2,
    "defaultReps": 15,
    "restTime": 30,
    "description": "누워서 발꿈치를 엉덩이 쪽으로 미끄러뜨려 무릎 가동범위 회복",
    "videoUrl": "https://youtu.be/Er-Fl_poWDk"
  }
}
```

---

### 3.3 추천 운동 조회 (건강 프로필 기반)

**Endpoint**: `GET /exercises/recommended`

**Headers**:
```
Authorization: Bearer {accessToken}
```

**Response** (200 OK):
```json
{
  "success": true,
  "message": "통증 부위와 목표에 맞는 운동을 추천합니다.",
  "data": [
    {
      "id": "E01",
      "nameKo": "힐 슬라이드",
      "difficulty": "하",
      "mainMuscle": "햄스트링, 대퇴사두근",
      "reason": "무릎 통증 완화에 효과적"
    },
    {
      "id": "E13",
      "nameKo": "부분 스쿼트",
      "difficulty": "중",
      "mainMuscle": "대퇴사두근, 둔근",
      "reason": "근력 강화에 적합"
    }
  ]
}
```

---

## 4. 운동 루틴

### 4.1 루틴 생성

**Endpoint**: `POST /routines`

**Headers**:
```
Authorization: Bearer {accessToken}
```

**Request Body**:
```json
{
  "name": "월요일 무릎 재활 루틴",
  "description": "무릎 통증 완화를 위한 루틴",
  "exercises": [
    {
      "exerciseId": "E01",
      "customSets": 3,
      "customReps": 12,
      "order": 1
    },
    {
      "exerciseId": "E13",
      "customSets": 2,
      "customReps": 10,
      "order": 2
    },
    {
      "exerciseId": "E09",
      "customSets": 2,
      "customReps": 12,
      "order": 3
    }
  ]
}
```

**Response** (201 Created):
```json
{
  "success": true,
  "message": "루틴이 생성되었습니다.",
  "data": {
    "id": 1,
    "name": "월요일 무릎 재활 루틴",
    "description": "무릎 통증 완화를 위한 루틴",
    "isActive": true,
    "exercises": [
      {
        "id": "E01",
        "nameKo": "힐 슬라이드",
        "customSets": 3,
        "customReps": 12,
        "order": 1
      },
      {
        "id": "E13",
        "nameKo": "부분 스쿼트",
        "customSets": 2,
        "customReps": 10,
        "order": 2
      },
      {
        "id": "E09",
        "nameKo": "브리지",
        "customSets": 2,
        "customReps": 12,
        "order": 3
      }
    ],
    "createdAt": "2024-11-14T11:00:00"
  }
}
```

---

### 4.2 내 루틴 목록 조회

**Endpoint**: `GET /routines`

**Headers**:
```
Authorization: Bearer {accessToken}
```

**Query Parameters**:
- `isActive` (optional): true/false - 활성화된 루틴만 조회

**Response** (200 OK):
```json
{
  "success": true,
  "data": [
    {
      "id": 1,
      "name": "월요일 무릎 재활 루틴",
      "description": "무릎 통증 완화를 위한 루틴",
      "isActive": true,
      "exerciseCount": 3,
      "createdAt": "2024-11-14T11:00:00",
      "updatedAt": "2024-11-14T11:00:00"
    },
    {
      "id": 2,
      "name": "수요일 하체 강화 루틴",
      "description": null,
      "isActive": true,
      "exerciseCount": 5,
      "createdAt": "2024-11-14T12:00:00",
      "updatedAt": "2024-11-14T12:00:00"
    }
  ],
  "totalCount": 2
}
```

---

### 4.3 루틴 상세 조회

**Endpoint**: `GET /routines/{routineId}`

**Headers**:
```
Authorization: Bearer {accessToken}
```

**Response** (200 OK):
```json
{
  "success": true,
  "data": {
    "id": 1,
    "name": "월요일 무릎 재활 루틴",
    "description": "무릎 통증 완화를 위한 루틴",
    "isActive": true,
    "exercises": [
      {
        "exerciseId": "E01",
        "nameKo": "힐 슬라이드",
        "nameEn": "Heel Slide",
        "difficulty": "하",
        "mainMuscle": "햄스트링, 대퇴사두근",
        "customSets": 3,
        "customReps": 12,
        "restTime": 30,
        "order": 1,
        "videoUrl": "https://youtu.be/Er-Fl_poWDk"
      },
      {
        "exerciseId": "E13",
        "nameKo": "부분 스쿼트",
        "nameEn": "Partial Squat",
        "difficulty": "중",
        "mainMuscle": "대퇴사두근, 둔근",
        "customSets": 2,
        "customReps": 10,
        "restTime": 45,
        "order": 2,
        "videoUrl": "https://youtu.be/HVypdPdke-c"
      }
    ],
    "createdAt": "2024-11-14T11:00:00",
    "updatedAt": "2024-11-14T11:00:00"
  }
}
```

---

### 4.4 루틴 수정

**Endpoint**: `PUT /routines/{routineId}`

**Headers**:
```
Authorization: Bearer {accessToken}
```

**Request Body**:
```json
{
  "name": "월요일 무릎 재활 루틴 (수정)",
  "description": "강도 업그레이드",
  "isActive": true,
  "exercises": [
    {
      "exerciseId": "E01",
      "customSets": 4,
      "customReps": 15,
      "order": 1
    }
  ]
}
```

**Response** (200 OK):
```json
{
  "success": true,
  "message": "루틴이 수정되었습니다.",
  "data": {
    "id": 1,
    "name": "월요일 무릎 재활 루틴 (수정)",
    "description": "강도 업그레이드",
    "isActive": true,
    "updatedAt": "2024-11-14T15:00:00"
  }
}
```

---

### 4.5 루틴 삭제

**Endpoint**: `DELETE /routines/{routineId}`

**Headers**:
```
Authorization: Bearer {accessToken}
```

**Response** (200 OK):
```json
{
  "success": true,
  "message": "루틴이 삭제되었습니다."
}
```

---

## 5. 운동 기록

### 5.1 운동 기록 생성

**Endpoint**: `POST /workout-records`

**Headers**:
```
Authorization: Bearer {accessToken}
```

**Request Body**:
```json
{
  "exerciseId": "E01",
  "workoutRoutineId": 1,
  "date": "2024-11-14",
  "actualSets": 3,
  "actualReps": 12,
  "completed": true,
  "memo": "오늘 컨디션 좋음"
}
```

**Response** (201 Created):
```json
{
  "success": true,
  "message": "운동 기록이 저장되었습니다.",
  "data": {
    "id": 1,
    "exerciseId": "E01",
    "exerciseName": "힐 슬라이드",
    "workoutRoutineId": 1,
    "date": "2024-11-14",
    "actualSets": 3,
    "actualReps": 12,
    "completed": true,
    "memo": "오늘 컨디션 좋음",
    "createdAt": "2024-11-14T18:00:00"
  }
}
```

---

### 5.2 운동 기록 조회 (날짜별)

**Endpoint**: `GET /workout-records`

**Headers**:
```
Authorization: Bearer {accessToken}
```

**Query Parameters**:
- `startDate` (required): YYYY-MM-DD
- `endDate` (required): YYYY-MM-DD

**Example**: `GET /workout-records?startDate=2024-11-01&endDate=2024-11-14`

**Response** (200 OK):
```json
{
  "success": true,
  "data": [
    {
      "id": 1,
      "date": "2024-11-14",
      "exerciseId": "E01",
      "exerciseName": "힐 슬라이드",
      "actualSets": 3,
      "actualReps": 12,
      "completed": true,
      "memo": "오늘 컨디션 좋음"
    },
    {
      "id": 2,
      "date": "2024-11-13",
      "exerciseId": "E13",
      "exerciseName": "부분 스쿼트",
      "actualSets": 2,
      "actualReps": 10,
      "completed": true,
      "memo": null
    }
  ],
  "totalCount": 2
}
```

---

### 5.3 운동 기록 통계

**Endpoint**: `GET /workout-records/stats`

**Headers**:
```
Authorization: Bearer {accessToken}
```

**Query Parameters**:
- `period` (optional): week, month, year (기본값: month)

**Response** (200 OK):
```json
{
  "success": true,
  "data": {
    "period": "month",
    "totalWorkouts": 15,
    "completedWorkouts": 13,
    "completionRate": 86.7,
    "totalExerciseTime": 300,
    "mostFrequentExercise": {
      "exerciseId": "E01",
      "exerciseName": "힐 슬라이드",
      "count": 8
    },
    "weeklyProgress": [
      {
        "week": "2024-11-04 ~ 2024-11-10",
        "workoutCount": 3
      },
      {
        "week": "2024-11-11 ~ 2024-11-17",
        "workoutCount": 5
      }
    ]
  }
}
```


---

## 공통 에러 응답

### 400 Bad Request
```json
{
  "success": false,
  "message": "요청 데이터가 올바르지 않습니다.",
  "errorCode": "INVALID_REQUEST",
  "errors": [
    {
      "field": "email",
      "message": "이메일 형식이 올바르지 않습니다."
    }
  ]
}
```

### 401 Unauthorized
```json
{
  "success": false,
  "message": "인증이 필요합니다.",
  "errorCode": "UNAUTHORIZED"
}
```

### 403 Forbidden
```json
{
  "success": false,
  "message": "접근 권한이 없습니다.",
  "errorCode": "FORBIDDEN"
}
```

### 404 Not Found
```json
{
  "success": false,
  "message": "요청한 리소스를 찾을 수 없습니다.",
  "errorCode": "NOT_FOUND"
}
```

### 500 Internal Server Error
```json
{
  "success": false,
  "message": "서버 내부 오류가 발생했습니다.",
  "errorCode": "INTERNAL_SERVER_ERROR"
}
```

---

## 변경 이력

| 버전 | 날짜 | 변경 내용 | 작성자 |
|------|------|-----------|--------|
| 1.0 | 2024-11-14 | 최초 작성 | 박도현 |

---

**프로젝트**: 뚜둑  
**백엔드**: Spring Boot + JPA  
**인증**: JWT
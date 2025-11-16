# 뚜둑 (Dudduk) 🦴

근골격계 통증 환자를 위한 AI 맞춤 재활 솔루션

## 프로젝트 소개

뚜둑은 사용자의 통증 부위와 건강 상태에 맞는 운동을 추천하고, 
운동 루틴을 관리할 수 있는 헬스케어 애플리케이션입니다.

## 주요 기능

- 건강 프로필 기반 맞춤 운동 추천
- 운동 루틴 생성 및 관리
- 운동 기록 및 진행 상황 추적
- 통증 부위별 운동 제공
  
## 기술 스택

### Backend
- Java 21
- Spring Boot 3.5.7
- Spring Data JPA
- MySQL 8.0

### DevOps
- Docker & Docker Compose
- Gradle

## 실행 방법

### 1. MySQL 실행
```bash
docker-compose up -d
```

### 2. Spring Boot 실행
```bash
./gradlew bootRun
```

### 3. 접속 확인
- API 서버: http://localhost:8080
- MySQL: localhost:3306

## 관련 저장소
- Backend (현재): https://github.com/parkdo0918/dudduk_api
- AI Model: [링크]
- Frontend: [링크]

## 문서
- [ERD](docs/뚜둑_ERD.md)
- [API 명세서](docs/뚜둑_API명세서.md)

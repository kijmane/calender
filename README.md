# Calender 일정 관리 백엔드 시스템
## Project Overview
- 일정 관리 기능을 중심으로 한 실무형 백엔드 시스템 설계 및 고도화
- 인증/인가, 공유 기능, 쿼리 최적화, 캐싱, AOP 기반 로깅 등 다양한 백엔드 기능 구현

## Main Features
### 인증 및 보안
- Spring Security + JWT 기반 로그인/회원가입
- 사용자 인증 및 역할 기반 접근 제어
- 비밀번호는 BCrypt로 암호화 저장

### 일정 관리
- 일정 생성, 수정, 삭제, 조회 기능
- QueryDSL 기반 조건 검색 (날짜, 태그, 카테고리 등)
- 최신순 정렬 및 페이징 처리

### 일정 공유
- 사용자 간 공유 요청 및 수락
- 공유된 일정에 대한 접근 제어 로직 포함

### 태그/카테고리 관리
- 일정에 태그와 카테고리 지정
- 검색 조건으로 활용 가능

### 댓글 기능
- 일정에 대한 댓글 등록, 수정, 삭제

### 성능 최적화
- Redis 캐싱 적용 (예: 일정 목록 등 조회 결과 캐싱)
- MySQL 쿼리 튜닝 및 인덱싱을 통한 조회 성능 개선
- QueryDSL + BooleanBuilder로 유연한 동적 쿼리 구현

### AOP 기반 로깅
- 관리자 API 접근 시 AOP 로깅 기능 적용

## Tech Stack
| 구성 | 사용 기술 |
|------|-----------|
| Backend | Spring Boot, Spring Security, JWT, JPA, QueryDSL |
| Database | MySQL |
| Build Tool | Gradle |
| 문서화 도구 | Postman |
| 캐시 | Redis |

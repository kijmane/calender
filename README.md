## Project Overview
- 일정 관리 기능을 중심으로 한 실무형 백엔드 시스템 설계 및 고도화
- 인증/인가, 공유 기능, 쿼리 최적화, 캐싱, AOP 기반 로깅 등 다양한 백엔드 기능 구현

## Tech Stack
| Configuration | Tech Stack |
|------|-----------|
| Backend | Spring Boot, Spring Security, JWT, JPA, QueryDSL |
| Database | MySQL |
| Build Tool | Gradle |
| API Documentation Tool | Postman |
| Cache | Redis |
| DevOps | Docker, GitHub Actions |

## DevOps Composition
- Spring Boot 애플리케이션을 Docker로 컨테이너화하여 실행
- GitHub Actions를 활용해 `main` 브랜치에 push할 때마다 자동으로 Gradle 빌드 및 Docker 이미지 빌드가 이루어지도록 구성

## Main Features
### Authentication & Security
- Spring Security + JWT 기반 로그인/회원가입
- 사용자 인증 및 역할 기반 접근 제어
- 비밀번호는 BCrypt로 암호화 저장

### Calender Management
- 일정 생성, 수정, 삭제, 조회 기능
- QueryDSL 기반 조건 검색 (날짜, 태그, 카테고리 등)
- 최신순 정렬 및 페이징 처리

### Calender Sharing
- 사용자 간 공유 요청 및 수락
- 공유된 일정에 대한 접근 제어 로직 포함

### Tag & Category Management
- 일정에 태그와 카테고리 지정
- 검색 조건으로 활용 가능

### Comment Feature
- 일정에 대한 댓글 등록, 수정, 삭제

### Comment Feature
- Redis 캐싱 적용 (예: 일정 목록 등 조회 결과 캐싱)
- MySQL 쿼리 튜닝 및 인덱싱을 통한 조회 성능 개선
- QueryDSL + BooleanBuilder로 유연한 동적 쿼리 구현

### AOP Logging
- 관리자 API 접근 시 AOP 로깅 기능 적용

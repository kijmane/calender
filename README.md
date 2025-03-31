# Calender
## Project Overview
- 사용자는 일정을 관리할 수 있습니다.
- 성능 최적화를 위해 MySQL 쿼리 튜닝 및 Redis 캐싱을 적용했습니다.

## Main Features
- 회원가입 및 로그인 : Spring Security와 JWT를 이용한 인증 및 권한 관리
- 일정 관리 : 일정 생성 , 수정 , 삭제 , 조회 기능을 제공하고 JPA와 Querydsl을 사용해서 효율적인 데이터 처리
- 비밀번호 암호화 : BCrypt를 활용하여 비밀번호 암호화 및 안전하게 처리
- 일정 공유: 사용자 간 초대 기능 및 알림 시스템 구현
- 태그 및 카테고리: 일정에 태그와 카테고리를 추가하여 효율적인 일정 관리 및 검색 기능 제공

## To be developed
- 자동 알림: Spring Batch를 활용한 스케줄링으로 주기적인 알림 발송 기능 추가 (feature/batch-reminder)
- 실시간 알림: WebSocket을 이용한 실시간 일정 알림 발송 기능 구현

## Tech Stack
- Backend : Spring Boot , Spring Security , JWT , Spring Data JPA , Querydsl
- Database : MySQL , Redis
- API 문서화 : Postman

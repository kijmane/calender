# Advancement Calender [개발중]
## Project Overview
- 사용자는 일정을 관리하고 알림을 받을 수 있습니다.
- 성능 최적화를 위해 MySQL 쿼리 튜닝 및 Redis 캐싱을 적용했습니다.

## Main Features
- 회원가입 및 로그인 : Spring Security와 JWT를 이용한 인증 및 권한 관리
- 일정 관리 : 일정 생성 , 수정 , 삭제 , 조회 기능을 제공하고 JPA와 Querydsl을 사용해서 효율적인 데이터 처리
- 일정 공유 : 사용자 간 초대 기능 및 알림 시스템
- 태그 및 카테고리 : 일정에 태그와 카테고리를 추가해서 효율적인 일정 관리 및 검색
- 실시간 알림 : WebSocket을 이용해서 실시간 일정 알림 발송
- 자동 알림 : Spring Batch를 활용한 스케줄링으로 주기적인 알림 발송
- 비밀번호 암호화 : BCrypt를 활용하여 비밀번호 암호화 및 안전하게 처리

## Tech Stack
- Backend : Spring Boot , Spring Security , JWT , Spring Data JPA , Querydsl
- Database : MySQL , Redis
- 실시간 알림 : WebSocket
- 스케줄링 : Spring Batch
- 배포 : Docker , AWS
- CI/CD : Jenkins
- API 문서화 : Postman

## Exceptional Handling
- 커스텀 예외 클래스 : 예외를 구체적으로 정의해서 사용자에게 더 명확한 오류 메시지 제공 (UserNotFoundException , InvalidPasswordException)
- 글로벌 예외 처리 : @ControllerAdvice를 사용해서 애플리케이션 전역에서 발생하는 예외를 처리 (예외 발생 시 HTTP 상태 코드와 메시지를 반환)

## ERD
![Untitled](https://github.com/user-attachments/assets/e0e6e123-8bd1-4ea5-b5c8-92c5eef6efec)

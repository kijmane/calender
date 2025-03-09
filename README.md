# Advancement Calender [개발중]
## Project Overview
- 사용자는 시스템을 통해 일정을 관리하고 알림을 받을 수 있습니다.
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

## Execute
1. 프로젝트 클론
git clone https://github.com/kijmane/schedule.git

2. 의존성 설치 
mvn install

3. 애플리케이션 실행
mvn spring-boot:run

4. Swagger UI
애플리케이션이 실행된 후 Swagger UI에서 API 문서를 확인할 수 있습니다.

## Contribute
1. Fork
- 이 프로젝트를 자신의 GitHub 계정으로 Fork해주세요.

2. Clone
- Fork한 프로젝트를 로컬 컴퓨터에 클론합니다.
```
git clone https://github.com/your-username/schedule.git
```

3. Feature Branch
- 새로운 기능을 개발하거나 버그를 수정할 때는 Feature Branch를 생성해주세요.
```
git checkout -b feature/add-login-functionality
```

5. 변경 사항 커밋
- 변경 사항을 커밋합니다.
```
git add .
git commit -m "Add login functionality"
```

6. Push
- 로컬에서 커밋한 변경 사항을 GitHub 원격 저장소에 Push 해줍니다.
```
git push origin feature/add-login-functionality
```

8. Pull Request
- 변경 사항을 Push한 후 원본 프로젝트에 Pull Request를 생성해서 기여 제출

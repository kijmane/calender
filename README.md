# 고도화된 일정 관리 시스템
# 프로젝트 개요
고도화된 일정 관리 시스템을 구축하는 것을 목표로 합니다.
실무에서 활용 가능한 CRUD 기능을 제공하고 JWT 인증 , 스케줄링 , 실시간 알림 시스템 등의 기능을 포함하고 있습니다.
사용자는 이 시스템을 통해 일정을 관리하고 알림을 받을 수 있습니다.
성능 최적화를 위해 MySQL 쿼리 튜닝 및 Redis 캐싱을 적용했습니다.

# 핵심 기능
- 회원가입 및 로그인 : Spring Security와 JWT를 이용한 인증 및 권한 관리
- 일정 관리 : 일정 생성 , 수정 , 삭제 , 조회 기능을 제공하고 JPA와 Querydsl을 사용해서 효율적인 데이터 처리
- 일정 공유 : 사용자 간 초대 기능 및 알림 시스템
- 태그 및 카테고리 : 일정에 태그와 카테고리를 추가해서 효율적인 일정 관리 및 검색
- 실시간 알림 : WebSocket을 이용해서 실시간 일정 알림 발송
- 자동 알림 : Spring Batch를 활용한 스케줄링으로 주기적인 알림 발송

# 사용 기술 스택
- Backend : Spring Boot , Spring Security , JWT , Spring Data JPA , Querydsl
- Database : MySQL , Redis (세션 관리 및 캐싱)
- 실시간 알림 : WebSocket
- 스케줄링 : Spring Batch
- 배포 : Docker , AWS
- CI/CD : Jenkins
- API 문서화 : Swagger , Postman

# 프로젝트 차별화 포인트
- 실무 로직 포함 : 단순 CRUD에 그치지 않고 JWT 인증 , 실시간 알림 , 스케줄링 등의 기능을 포함하여 실제 업무 환경에서도 사용할 수 있는 수준으로 개발
- 성능 최적화 : MySQL 쿼리 튜닝과 Redis 캐싱 적용으로 높은 성능을 유지
- API 문서화 : Swagger와 Postman을 활용해서 API 사용 방법을 명확히 문서화
- 배포 경험 : Docker 컨테이너화 및 AWS 배포를 통해 실제 운영 환경에 배포하는 과정을 경험

# ERD 설계
![Untitled](https://github.com/user-attachments/assets/d369a3fd-91d8-4e03-b531-31b352fc24e2)

# 실행 방법
1. 프로젝트 클론
git clone https://github.com/kijmane/schedule.git

3. 의존성 설치 
mvn install

4. 애플리케이션 실행
mvn spring-boot:run

5. Swagger UI
애플리케이션이 실행된 후 Swagger UI에서 API 문서를 확인할 수 있습니다.

# API 엔드 포인트
- 회원가입
- URL : /api/auth/register
- Method : POST
- Request Body : {
  "email": "user@example.com",
  "password": "password123",
  "name": "John Doe"
}

- 로그인
- URL : /api/auth/login
- Method : POST
- Request Body : {
  "email": "user@example.com",
  "password": "password123"
}

- 일정 생성
- URL : /api/schedules
- Method : POST
- Request Body : {
  "task": "Meeting with John",
  "startDate": "2025-02-05T10:00:00",
  "endDate": "2025-02-05T11:00:00",
  "userId": 1
}

# 기여 방법
1. Fork
이 프로젝트를 자신의 GitHub 계정으로 Fork해주세요.

2. Clone
Fork한 프로젝트를 로컬 컴퓨터에 클론합니다.
EX ) git clone https://github.com/your-username/schedule.git

3. Feature Branch
새로운 기능을 개발하거나 버그를 수정할 때는 Feature Branch를 생성해주세요.
EX ) git checkout -b feature/add-login-functionality

4. 변경 사항 커밋
변경 사항을 커밋합니다.
EX ) git add .
git commit -m "Add login functionality"

5. Push
로컬에서 커밋한 변경 사항을 GitHub 원격 저장소에 Push 해줍니다.
EX ) git push origin feature/add-login-functionality

6. Pull Request
변경 사항을 Push한 후 원본 프로젝트에 Pull Request를 생성해서 기여를 제출합니다.

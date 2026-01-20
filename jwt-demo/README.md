### JWT Demo (Spring Boot 3)

간단한 로그인/회원가입과 JWT 기반 인증을 학습하기 위한 스프링 부트 데모입니다. 초보 개발자가 따라 하며 JWT 흐름과 Spring Security 구성을 이해할 수 있도록 최소한의 코드와 설정으로 구성했습니다.

#### 주요 기능

- 회원가입(`/register`)과 로그인(`/login`)
- 로그인 성공 시 HMAC256으로 서명된 JWT 발급
- 요청 헤더의 `Authorization: Bearer <token>`을 검사하는 필터 적용
- 보호된 API 예시(`/hello`)
- MySQL사용, JPA로 사용자 저장
- Swagger UI 연동으로 API 문서/테스트 제공

---

### 기술 스택

- Java 17, Spring Boot 3.5.9
- Spring Web, Spring Security, Spring Data JPA, Validation
- MySQL Database
- JWT: `com.auth0:java-jwt`
- 문서: `springdoc-openapi-starter-webmvc-ui`
- 기타: Lombok, MapStruct

---

### 프로젝트 구조 개요

```
src/main/java/com/mysite/jwtdemo
├─ config
│  ├─ SecurityConfiguration.java        # 시큐리티 필터 체인/인가 설정, JWT 필터 연결
│  ├─ SwaggerConfiguration.java         # Swagger(OpenAPI) 설정
│  └─ PasswordEncoderConfiguration.java # BCryptPasswordEncoder 빈 등록
├─ controller
│  ├─ RegistrationController.java       # POST /register
│  ├─ LoginController.java              # POST /login
│  └─ HelloController.java              # GET /hello (보호됨)
├─ exception                            # 유효성/가입/로그인 예외 처리
├─ model
│  ├─ User.java                         # users 테이블 엔티티
│  └─ UserRole.java                     # 역할(ROLE_ 접두어로 사용)
├─ repository
│  └─ UserRepository.java               # 사용자 조회/저장
├─ security
│  ├─ dto                               # 요청/응답 DTO
│  ├─ jwt
│  │  ├─ JwtAuthenticationFilter.java   # 헤더 토큰 파싱/검증 → SecurityContext 설정
│  │  ├─ JwtAuthenticationEntryPoint.java # 인증 실패(401) 처리
│  │  ├─ JwtTokenManager.java           # 토큰 생성/검증/파싱
│  │  └─ JwtTokenService.java           # 로그인 인증 후 토큰 발급
│  ├─ mapper
│  │  └─ UserMapper.java                # MapStruct로 DTO ↔ 엔티티 변환
│  └─ service
│     ├─ UserService(Impl).java         # 회원가입/조회 로직
│     └─ UserDetailsServiceImpl.java    # Spring Security 사용자 로드
└─ util / service                       # 유효성 검사 등 부가 로직
```

---

### 빠른 시작

사전 요구 사항: Java 17, Gradle(래퍼 포함), 인터넷 연결

1. 의존성 받기 및 빌드

```
./gradlew clean build
```

2. 애플리케이션 실행

```
./gradlew bootRun
```

기본 포트는 8080입니다. (변경하려면 `application.properties`에 `server.port` 추가)

---

### 설정 요약 (`src/main/resources/application.properties`)

- MySQL DB: jwt 데이터베이스 필수(MySQL Workbench 에 새 jwt 스키마 만들기)
- JPA DDL: `spring.jpa.hibernate.ddl-auto=update` (테스트에서만 사용하고 실서비스에는 none)
- JWT 시크릿: `jwt.secret=...` (예제 값이 포함되어 있으니 실서비스에서는 환경변수로 분리하세요)

---

### 보안/인증 흐름 이해하기

1. 사용자가 `/login`에 아이디/비밀번호로 요청
2. `AuthenticationManager`가 사용자 인증 성공 시 `JwtTokenService`가 JWT 발급
3. 이후 보호된 API 호출 시 헤더에 `Authorization: Bearer <JWT>` 포함
4. `JwtAuthenticationFilter`가 매 요청마다 토큰을 파싱/검증하고 `SecurityContext`에 인증 정보를 설정
5. 인가 규칙은 `SecurityConfiguration`에서 관리
   - permitAll: `/register`, `/login`, `/v3/api-docs/**`, `/swagger-ui/**`, `/swagger-ui.html`, `/actuator/**`
   - 그 외는 인증 필요

토큰 기본 만료: 발급 후 1시간 (`JwtTokenManager.generateToken` 참조)

---

### API 사용법

Swagger UI로 확인/실행: http://localhost:8080/swagger-ui/index.html

1. 회원가입

```
POST http://localhost:8080/register
Content-Type: application/json

{
  "username": "user1",
  "password": "pass1234",
  "email": "user1@example.com"
}
```

성공 시 201 Created와 메시지 반환

```
{
    "message": "drv98님 회원 가입성공!"
}

```

2. 로그인(토큰 발급)

```
POST http://localhost:8080/login
Content-Type: application/json

{
  "username": "user1",
  "password": "pass1234"
}
```

성공 시 응답 예시

```
{
  "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9..."
}
```

3. 보호된 API 호출 예시

```
GET http://localhost:8080/hello
Authorization: Bearer <로그인에서 받은 JWT>
```

성공 시 200 OK와 간단한 메시지 반환

curl 예시

```
# 회원가입
curl -X POST http://localhost:8080/register \
  -H "Content-Type: application/json" \
  -d '{"username":"user1","password":"pass1234","email":"user1@example.com"}'

# 로그인 → 토큰 변수에 저장 (PowerShell 예시는 아래 참고)
curl -X POST http://localhost:8080/login \
  -H "Content-Type: application/json" \
  -d '{"username":"user1","password":"pass1234"}'

# 보호된 API (토큰을 치환하여 사용)
curl -X GET http://localhost:8080/hello \
  -H "Authorization: Bearer <JWT>"
```

PowerShell에서 토큰 변수로 사용하기 예시

```
$resp = Invoke-RestMethod -Method Post -Uri http://localhost:8080/login -ContentType 'application/json' -Body '{"username":"user1","password":"pass1234"}'
$token = $resp.token
Invoke-RestMethod -Method Get -Uri http://localhost:8080/hello -Headers @{ Authorization = "Bearer $token" }
```

---

### 개발 시 유용한 정보

- Swagger UI: http://localhost:8080/swagger-ui/index.html
- OpenAPI JSON: http://localhost:8080/v3/api-docs
- Actuator: http://localhost:8080/actuator

---

### 자주 겪는 오류와 해결법

- 401 Unauthorized: `Authorization` 헤더가 없거나 토큰 만료/서명 오류
  - 로그인으로 새 토큰 발급 후 다시 시도
  - 요청 헤더에 `Authorization: Bearer <token>` 포함됐는지 확인
- 400 Bad Request(회원가입/로그인): 요청 JSON 필드 누락/유효성 오류
  - Swagger UI에서 스키마 확인 후 필수 필드 채우기

---

### 운영 시 주의사항(학습용 프로젝트 → 실서비스 이전 체크리스트)

- JWT 시크릿을 환경변수/외부 설정으로 분리하고 강력한 키로 교체
- HTTPS 적용 및 CORS 정책 점검(현재 샘플에서는 CORS 비활성화)
- 토큰 만료/재발급(리프레시 토큰) 전략 도입 고려
- 사용자 권한/인가 세분화 및 엔드포인트별 접근 제어
- RDBMS(Oracle, PostgreSQL 등)로 전환 가능

---

### 빌드/테스트

```
./gradlew clean test
./gradlew bootRun
```

---

### 라이선스

학습/데모 용도. 필요 시 프로젝트 정책에 맞게 라이선스를 추가하세요.

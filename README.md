# KoreaGuide_Server
This project serves as the backend of a mobile application __"Korea Guide".__   
The project is in the form of a RESTAPI.
## Download and Installation
To begin using this template, choose one of the following options to get started:
* [Fork, Clone, or Download on GitHub](https://github.com/KoreaGuide/KoreaGuide_Server)

## Requirements
- Java version 11
- Spring Boot 
- MySQL 

## Dependencies 
```
    //jwt
    implementation 'io.jsonwebtoken:jjwt-api:0.10.5'
    runtime 'io.jsonwebtoken:jjwt-impl:0.10.5'
    runtime 'io.jsonwebtoken:jjwt-jackson:0.10.5'

    compile 'mysql:mysql-connector-java'
    compile 'org.projectlombok:lombok:1.18.10'
    annotationProcessor 'org.projectlombok:lombok:1.18.10'
    implementation 'org.springframework.boot:spring-boot-starter-web'
    testImplementation 'org.springframework.boot:spring-boot-starter-test'
    compile 'org.springframework.boot:spring-boot-starter-validation'
    compile 'org.springframework.boot:spring-boot-starter-data-jpa'
    implementation 'org.springframework.boot:spring-boot-starter-security'
    
```
## Project Functions 
1. Duplicate check of emails 
2. Register
3. Login
4. Home 

## API Endpoints
__User Related Endpoints__
|**Request Type**|**Path**|**Method**|**Description**|
|---|------|---|---|
| POST | [/api/user](https://github.com/KoreaGuide/KoreaGuide_Server/blob/main/API-GUIDE/USER.md) | CREATE | Create User & save access token for user - Register (회원가입) |
| GET | [/api/user/{id}](https://github.com/KoreaGuide/KoreaGuide_Server/blob/main/API-GUIDE/USER.md) | READ | Get information of 1 particular user (사용자 정보 조회) |
| PATCH | [/api/user/{id}](https://github.com/KoreaGuide/KoreaGuide_Server/blob/main/API-GUIDE/USER.md) | UPDATE | Update information of a particular user (사용자 정보 수정) |
| DELETE | [/api/user/{id}](https://github.com/KoreaGuide/KoreaGuide_Server/blob/main/API-GUIDE/USER.md) | DELETE | Delete user (사용자 삭제) |
| POST | [/api/user/checkDuplicate](https://github.com/KoreaGuide/KoreaGuide_Server/blob/main/API-GUIDE/USER.md) | - | Check Duplicate user by email (사용자 이메일 중복 확인 - 회원가입 페이지에서 사용하면 됨) |
| POST | [/api/user/login](https://github.com/KoreaGuide/KoreaGuide_Server/blob/main/API-GUIDE/USER.md) | - | 사용자 로그인 |
      
   
__Home Related Endpoints__
|**Request Type**|**Path**|**Method**|**Description**|
|---|------|---|---|
| GET | [/api/home/{level}](https://github.com/KoreaGuide/KoreaGuide_Server/blob/main/API-GUIDE/HOME.md) | READ & CREATE | 홈 화면에서 필요한 정보 조회 |
   
## ERD
<img width="979" alt="스크린샷 2021-03-12 오후 3 42 28" src="https://user-images.githubusercontent.com/52744390/110905701-3e3ffa00-834e-11eb-8d4e-115095b1b369.png">
   
## Team Members & roles
* [Jisoo Kim](https://github.com/cindia3704) - Backend 
* [Chihyun Song](https://github.com/alzee03) - Front-end (Main)
* [Seonho Im](https://github.com/imseonho) - External API & Data filtering & Front-end (Sub)

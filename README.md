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
5. My Word (내 단어장)

## Example of Token in header 
```
Authorization Bearer eyJhbGciOiJIUzI1NiJ9.eyJ1c2VySWQiOjExLCJuYW1lIjoiQ2luZGlhIn0.vNieUBizIAzhwpAv_J2m9GSMMUO96LTaWPLxYYOG_W8
```
__Response if wrong / no Token provided (헤더에 토큰 없거나 틀려서 생기는 오류)__
```json
{
    "result_code": 401,
    "status": "UNAUTHORIZED",
    "description": "Not Logged in"
}
```
   
## API Endpoints
__User Related Endpoints__
|**Request Type**|**Path**|**Need Token**|**Method**|**Description**|
|---|------|---|---|---|
| POST | [/api/user](https://github.com/KoreaGuide/KoreaGuide_Server/blob/main/API-GUIDE/USER.md) | NO | CREATE | Create User & save access token for user - Register (회원가입) |
| GET | [/api/user/{id}](https://github.com/KoreaGuide/KoreaGuide_Server/blob/main/API-GUIDE/USER.md) | YES | READ | Get information of 1 particular user (사용자 정보 조회) |
| PATCH | [/api/user/{id}](https://github.com/KoreaGuide/KoreaGuide_Server/blob/main/API-GUIDE/USER.md) | YES | UPDATE | Update information of a particular user (사용자 정보 수정) |
| DELETE | [/api/user/{id}](https://github.com/KoreaGuide/KoreaGuide_Server/blob/main/API-GUIDE/USER.md) | YES | DELETE | Delete user (사용자 삭제) |
| POST | [/api/user/checkDuplicate](https://github.com/KoreaGuide/KoreaGuide_Server/blob/main/API-GUIDE/USER.md) | NO | - | Check Duplicate user by email (사용자 이메일 중복 확인 - 회원가입 페이지에서 사용하면 됨) |
| POST | [/api/user/login](https://github.com/KoreaGuide/KoreaGuide_Server/blob/main/API-GUIDE/USER.md) | NO | - | 사용자 로그인 |
      
   
__Home Related Endpoints__
|**Request Type**|**Path**|**Need Token**|**Method**|**Description**|
|---|------|---|---|---|
| GET | [/api/home/{level}](https://github.com/KoreaGuide/KoreaGuide_Server/blob/main/API-GUIDE/HOME.md) | YES | READ & CREATE | 홈 화면에서 필요한 정보 조회 |
   
   
__MyWord (내 단어장) Related Endpoints__
|**Request Type**|**Path**|**Need Token**|**Method**|**Description**|
|---|------|---|---|---|
| POST | [/api/myWord/{id}](https://github.com/KoreaGuide/KoreaGuide_Server/blob/main/API-GUIDE/MYWORD.md) | YES | CREATE | 내 단어장에 특정 단어 추가 |
| GET | [/api/myWord/{id}](https://github.com/KoreaGuide/KoreaGuide_Server/blob/main/API-GUIDE/MYWORD.md) | YES | READ | 내 단어장에 있는 단어들 정보 조회 |
| DELETE | [/api/myWord/{id}](https://github.com/KoreaGuide/KoreaGuide_Server/blob/main/API-GUIDE/MYWORD.md) | YES | DELETE | 내 단어장에 특정 단어 삭제 |
   
## ERD
   
## Team Members & roles
* [Jisoo Kim](https://github.com/cindia3704) - Backend 
* [Chihyun Song](https://github.com/alzee03) - Front-end (Main)
* [Seonho Im](https://github.com/imseonho) - External API & Data filtering & Front-end (Sub)

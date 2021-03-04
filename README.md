# KoreaGuide_Server
This project serves as the backend of a mobile application <b>"Korea Guide".   
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

## API Endpoints
**User Related Enpoints**
|**Request Type**|**Path**|**Method**|**Description**|
|---|------|---|---|
| POST | [/api/user]() | CREATE | Create User & save access token for user - Register (회원가입) |
| GET | [/api/user/{id}]() | READ | Get information of 1 particular user (사용자 정보 조회) |
| PATCH | [/api/user/{id}]() | UPDATE | Update information of a particular user (사용자 정보 수정) |
| DELETE | [/api/user/{id}]() | DELETE | Delete user (사용자 삭제) |
| POST | [/api/user/checkDuplicate]() | - | Check Duplicate user by email (사용자 이메일 중복 확인 - 회원가입 페이지에서 사용하면 됨) |

## Team Members & roles
* [Jisoo Kim](https://github.com/cindia3704) - Backend 
* [ChiHyun Song](https://github.com/alzee03) - Front-end (Main)
* [SeonHo Lim](https://github.com/imseonho) - External API & Data filtering & Front-end (Sub)

# KoreaGuide_Server
This project serves as the backend of a mobile application __"Korea Guide".__   
The project is in the form of a RESTAPI. 
## Download and Installation
To begin using this template, choose one of the following options to get started:
* [Fork, Clone, or Download on GitHub](https://github.com/KoreaGuide/KoreaGuide_Server)

## Requirements
- Java Version 11
- Spring Boot Version 2.4.3
- MySQL Version 8.0.23

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
4. User (사용자 관련) 
5. Home
6. My Word Folder (내 단어장 폴더)
7. My Word (내 단어장)
8. Regions (지역 리스트 받아오기 & 지역별 색깔 바꾸기)
9. Place (특정 지역에 대한 관광지 정보 받아오기) 
10. Place Detail (장소 정보 & 관련 단어리스트)
11. My Map (내 지도) 
12. 내 단어장 폴더별 단어 학습 
13. Quiz 

## Example of Token in header 
```
Authorization Bearer eyJhbGciOiJIUzI1NiJ9.eyJ1c2VySWQiOjExLCJuYW1lIjoiQ2luZGlhIn0.vNieUBizIAzhwpAv_J2m9GSMMUO96LTaWPLxYYOG_W8
```
## Token for login & register & checkDuplicate
```
Authorization Bearer no_value
```
__Response if wrong / no Token provided (헤더에 토큰 없거나 틀려서 생기는 오류)__
```json
{
    "result_code": 401,
    "status": "UNAUTHORIZED",
    "description": "Not Logged in"
}
```

## Explanation of Pagination
```json
"pagination": {
            "total_pages": 2,
            "total_elements": 2,
            "current_page": 1,
            "current_elements": 1
        }
```
```
    - total pages: 총 페이지
    - total elements: 페이지네이션하는 아이템의 총 수
    - current page: 현재 페이지 
    - current elements: 현재 페이지에 있는 아이템의 수 

** pageNumber은 1부터 시작! 
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
| GET | [/api/home/](https://github.com/KoreaGuide/KoreaGuide_Server/blob/main/API-GUIDE/HOME.md) | YES | READ & CREATE | 홈 화면에서 필요한 정보 조회 |

__MyWordFolder (내 단어장 폴더) Related Endpoints__
|**Request Type**|**Path**|**Need Token**|**Method**|**Description**|
|---|------|---|---|---|
| POST | [api/myWordFolder/](https://github.com/KoreaGuide/KoreaGuide_Server/blob/main/API-GUIDE/MYWORDFOLDER.md) | YES | CREATE | 내 단어장 폴더 생성 |
| GET | [api/myWordFolder/{id}](https://github.com/KoreaGuide/KoreaGuide_Server/blob/main/API-GUIDE/MYWORDFOLDER.md) | YES | READ | 내 단어장 폴더 목록 보기 |
| DELETE | [api/myWordFolder/{id}](https://github.com/KoreaGuide/KoreaGuide_Server/blob/main/API-GUIDE/MYWORDFOLDER.md) | YES | DELETE | 내 특정 단어장 폴더 삭제 |
| PATCH | [api/myWordFolder/{id}](https://github.com/KoreaGuide/KoreaGuide_Server/blob/main/API-GUIDE/MYWORDFOLDER.md) | YES | UPDATE | 내 특정 단어장 이름 변경 |
| GET | [api/myWordFolder/learn/{id}?page={pageNumber}](https://github.com/KoreaGuide/KoreaGuide_Server/blob/main/API-GUIDE/MYWORDFOLDER.md) | YES | READ | 내 특정 단어장 폴더에 있는 단어들 학습하기 |
| GET | [api/myWordFolder/learnWord/{id}/{folderId}](https://github.com/KoreaGuide/KoreaGuide_Server/blob/main/API-GUIDE/MYWORDFOLDER.md) | YES | READ | 내 특정 단어장 폴더에 있는 단어들 학습하기 |

__MyWord (내 단어장) Related Endpoints__
|**Request Type**|**Path**|**Need Token**|**Method**|**Description**|
|---|------|---|---|---|
| POST | [/api/myWord/{id}](https://github.com/KoreaGuide/KoreaGuide_Server/blob/main/API-GUIDE/MYWORD.md) | YES | CREATE | 특정 단어장 폴더에 특정 단어 추가 |
| GET | [api/myWord/{id}/{wordFolderId](https://github.com/KoreaGuide/KoreaGuide_Server/blob/main/API-GUIDE/MYWORD.md) | YES | READ | 특정 단어장 폴더에 있는 단어들 정보 조회 |
| DELETE | [/api/myWord/{id}](https://github.com/KoreaGuide/KoreaGuide_Server/blob/main/API-GUIDE/MYWORD.md) | YES | DELETE | 특정 단어장 폴더에 있는 특정 단어 삭제 |

__Place Related Endpoints__
|**Request Type**|**Path**|**Need Token**|**Method**|**Description**|
|---|------|---|---|---|
| GET | [/api/place/regionList/{id}](https://github.com/KoreaGuide/KoreaGuide_Server/blob/main/API-GUIDE/PLACE.md) | YES | READ | 모든 지역에 대한 정보 조회(지역 리스트 받기) |
| PATCH | [/api/place/regionList/{id}](https://github.com/KoreaGuide/KoreaGuide_Server/blob/main/API-GUIDE/PLACE.md) | YES | UPDATE | 어떤 지역의 색 바꾸기 |
| GET | [/api/place/region/{userId}/{id}](https://github.com/KoreaGuide/KoreaGuide_Server/blob/main/API-GUIDE/PLACE.md) | YES | READ | 특정 지역에 대한 관광리 리스트 조회 |

__Place Detail Related Endpoints__
|**Request Type**|**Path**|**Need Token**|**Method**|**Description**|
|---|------|---|---|---|
| GET | [/api/place/detail/{userId}/{id}](https://github.com/KoreaGuide/KoreaGuide_Server/blob/main/API-GUIDE/PLACEDETAIL.md) | YES | READ | 특정 관광지에 대한 정보 조회 (영어 & 한국어)|
| GET | [/api/place/detail/eng/{userId}/{id}](https://github.com/KoreaGuide/KoreaGuide_Server/blob/main/API-GUIDE/PLACEDETAIL.md) | YES | READ | 특정 관광지에 대한 정보 조회 (영어)|
| GET | [/api/place/detail/kor/{userId}/{id}](https://github.com/KoreaGuide/KoreaGuide_Server/blob/main/API-GUIDE/PLACEDETAIL.md) | YES | READ | 특정 관광지에 대한 정보 조회 (한국어)|
| GET | [/api/place/word/{userId}/{id}?page={pageNumber}](https://github.com/KoreaGuide/KoreaGuide_Server/blob/main/API-GUIDE/PLACEDETAIL.md) | YES | READ | 특정 관광지의 단어 리스트 pagination|
| GET | [/api/place/wordList/{userId}/{id}](https://github.com/KoreaGuide/KoreaGuide_Server/blob/main/API-GUIDE/PLACEDETAIL.md) | YES | READ | 특정 관광지의 단어 리스트 pagination 없이| 
   
__My Map Related Endpoints__
|**Request Type**|**Path**|**Need Token**|**Method**|**Description**|
|---|------|---|---|---|
| POST | [api/myMap/{id}](https://github.com/KoreaGuide/KoreaGuide_Server/blob/main/API-GUIDE/MYMAP.md) | YES | CREATE | 내 지도에 장소 넣기(wish & have been to)|
| GET | [api/myMap/all/{id}](https://github.com/KoreaGuide/KoreaGuide_Server/blob/main/API-GUIDE/MYMAP.md) | YES | READ | 내 지도에 포함된 모든 장소 보기(wish & have been to)|
| GET | [api/myMap/wish/{id}](https://github.com/KoreaGuide/KoreaGuide_Server/blob/main/API-GUIDE/MYMAP.md) | YES | READ | 내 지도에 포함된 장소 보기(wish ONLY)|
| GET | [api/myMap/haveBeen/{id}](https://github.com/KoreaGuide/KoreaGuide_Server/blob/main/API-GUIDE/MYMAP.md) | YES | READ | 내 지도에 포함된 장소 보기(have been to ONLY)|
| PATCH | [api/myMap/{id}](https://github.com/KoreaGuide/KoreaGuide_Server/blob/main/API-GUIDE/MYMAP.md) | YES | UPDATE | 내 지도에 있는 장소 수정|
| DELETE | [api/myMap/{id}](https://github.com/KoreaGuide/KoreaGuide_Server/blob/main/API-GUIDE/MYMAP.md) | YES | DELETE | 내 지도에 있는 장소 삭제|

__WORD Related Endpoints__
|**Request Type**|**Path**|**Need Token**|**Method**|**Description**|
|---|------|---|---|---|
| GET | [api/word/{wordId}](https://github.com/KoreaGuide/KoreaGuide_Server/blob/main/API-GUIDE/WORD.md) | YES | READ | 특정 단어에 대한 정보 조회 ( HOME의 단어랑 연결시 사용) |

__Quiz Related Endpoints__
|**Request Type**|**Path**|**Need Token**|**Method**|**Description**|
|---|------|---|---|---|
| POST | [api/quiz/{id}](https://github.com/KoreaGuide/KoreaGuide_Server/blob/main/API-GUIDE/QUIZ.md) | YES | READ | 해당 퀴즈에 출제될 문제목록 및 객관식 목록 받아오기 |
| POST | [api/quiz/result/{id}](https://github.com/KoreaGuide/KoreaGuide_Server/blob/main/API-GUIDE/QUIZ.md) | YES | UPDATE | 퀴즈 종료 후 해당 퀴즈의 결과 서버에 업데이트 |

## ERD
   
## Team Members & roles
* [Jisoo Kim](https://github.com/cindia3704) - Backend 
* [Chihyun Song](https://github.com/alzee03) - Front-end (Main)
* [Seonho Im](https://github.com/imseonho) - External API & Data filtering & Front-end (Sub)

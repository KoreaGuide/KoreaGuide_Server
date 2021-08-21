# 🇰🇷KoreaGuide_Server🇰🇷
This project serves as the backend of a mobile application __"Forword".__   
The backend is developed in the form of a RESTAPI.   
> Forword is an abbreviation of Foreign + Word    
> The pronounciation of this word is similar to the English word "Forward", which means to move furthur to a certain destination
>    
> __We hope that users of FORWORD could move FORWARD with their journey of learning the Korean culture, especially the language and famous places__

## 🌃Project Introduction🌃
### Problem:
* Decreasing foreign tourists visiting Korea, due to COVID-19 
* Lack of true knowledge of the Korean culture and language of foreigners despite the increasing popularity of k-pop and k-drama
* No services available for foreigners to learn the Korean language in a beginner level
### Our Service: 
* Provides a contactless way of touring Korea 
* Allows users to learn the Korean language through the introduction of famous tourist spots 

## 📱Project Images📱
|**Image**|**Description**|   
|:----:|:----:|   
|<img width="518" alt="스크린샷 2021-08-21 오전 9 39 21" src="https://user-images.githubusercontent.com/52744390/130305082-f40c6698-d932-4ef8-8eb2-d8288e5c57c6.png">|__Splash, Login, Register__|
|<img width="518" alt="스크린샷 2021-08-21 오전 9 40 52" src="https://user-images.githubusercontent.com/52744390/130305125-3677ca43-e82f-4065-b84d-c91ff36c71a2.png">|__Home__</br>with "Word of the Day"</br>& 3 tourist spots|
|<img width="512" alt="스크린샷 2021-08-21 오후 12 36 37" src="https://user-images.githubusercontent.com/52744390/130309301-015f5bae-e843-4c18-922e-a8136671cd5b.png">|__Place List & Map Customization__|
|<img width="862" alt="스크린샷 2021-08-21 오전 9 50 56" src="https://user-images.githubusercontent.com/52744390/130305367-d881371e-7da3-49c1-a300-29661d7ddfd5.png">|__Place Detail__</br>with Information & Related words|
|<img width="517" alt="스크린샷 2021-08-21 오전 10 00 46" src="https://user-images.githubusercontent.com/52744390/130305606-67b7117e-bcb0-4d5a-b358-01d9dd3a78fb.png">|__My Word Folder & WordList__|
|<img width="680" alt="스크린샷 2021-08-21 오전 10 02 24" src="https://user-images.githubusercontent.com/52744390/130305642-cf5b8273-0ab0-444e-846f-13c05ca5ca28.png">|__Quiz (Type 1)__</br>Complete the spelling of word letter by letter|
|<img width="680" alt="스크린샷 2021-08-21 오전 10 05 13" src="https://user-images.githubusercontent.com/52744390/130305708-6dc4a9e2-75b7-42a9-883f-1cc934485233.png">|__Quiz(Type 2 & 3)__</br>Type2: Listen to the pronounciation & match word</br>Type3:Enter the Korean spelling|
|<img width="512" alt="스크린샷 2021-08-21 오전 10 05 49" src="https://user-images.githubusercontent.com/52744390/130305729-13ad798a-7869-4a3a-9138-2d16a6774400.png">|__Quiz(Type 4)__</br>Match the meaning word|
|<img width="512" alt="스크린샷 2021-08-21 오후 12 37 42" src="https://user-images.githubusercontent.com/52744390/130309334-0422c8b3-4779-4a5c-a9be-046cb628e266.png">|__My Profile__|


## 💻Installation and Set up💻
To begin using this template, choose one of the following options to get started:
* [Fork, Clone, or Download on GitHub](https://github.com/KoreaGuide/KoreaGuide_Server)  
* Open the project through Intellij IDEA  

To set up the database with pre-defined SQL statements, please refer to the file below:
* [See SQL](https://github.com/KoreaGuide/KoreaGuide_Server/blob/main/sqlStmts.md)  

## 📝Requirements📝
- Java Version 11
- Spring Boot Version 2.4.3
- MySQL Version 8.0.23

## 📕Dependencies📕
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
## 🔍Project Functions🔍
1. Duplicate check of emails 
2. User Registeration
3. Login
4. Modify / Delete User information 
5. Provide Today's word 
6. Provide 3 randomly selected tourist place everyday
7. Create/ Update/ DeleteMy Word Folder 
8. Add / Delete to My Wordlist 
9. Map customization (change color of regions)
10. Read placelists (in all regions, per region)
12. Place Detail (detailed information & related words) 
14. Learn words in my wordlist(READ)
15. Quiz (4Types)
16. Create / Read my Quiz Result 
17. Create / Delete / Update My map (wishlist & have been to lists)

   
## 📚API Endpoints📚
__User Related Endpoints__
|**Request Type**|**Path**|**Need Token**|**Method**|**Description**|
|---|------|---|---|---|
| POST | [/api/user](https://github.com/KoreaGuide/KoreaGuide_Server/blob/main/API-GUIDE/USER.md) | NO | CREATE | Create User & save access token for user - Register (회원가입) |
| GET | [/api/user/{id}](https://github.com/KoreaGuide/KoreaGuide_Server/blob/main/API-GUIDE/USER.md) | YES | READ | Get information of 1 particular user (사용자 정보 조회) |
| PATCH | [/api/user/{id}](https://github.com/KoreaGuide/KoreaGuide_Server/blob/main/API-GUIDE/USER.md) | YES | UPDATE | Update information of a particular user (사용자 정보 수정) |
| DELETE | [/api/user/{id}](https://github.com/KoreaGuide/KoreaGuide_Server/blob/main/API-GUIDE/USER.md) | YES | DELETE | Delete user (사용자 삭제) |
| POST | [/api/user/checkDuplicate](https://github.com/KoreaGuide/KoreaGuide_Server/blob/main/API-GUIDE/USER.md) | NO | - | Check Duplicate user by email (사용자 이메일 중복 확인 - 회원가입 페이지에서 사용하면 됨) |
| POST | [/api/user/login](https://github.com/KoreaGuide/KoreaGuide_Server/blob/main/API-GUIDE/USER.md) | NO | - | User Login(사용자 로그인) |
      
   
__Home Related Endpoints__
|**Request Type**|**Path**|**Need Token**|**Method**|**Description**|
|---|------|---|---|---|
| GET | [/api/home/](https://github.com/KoreaGuide/KoreaGuide_Server/blob/main/API-GUIDE/HOME.md) | YES | READ & CREATE | Get information needed in home page(홈 화면에서 필요한 정보 조회) |

__MyWordFolder (내 단어장 폴더) Related Endpoints__
|**Request Type**|**Path**|**Need Token**|**Method**|**Description**|
|---|------|---|---|---|
| POST | [api/myWordFolder/](https://github.com/KoreaGuide/KoreaGuide_Server/blob/main/API-GUIDE/MYWORDFOLDER.md) | YES | CREATE | Make my word folder (내 단어장 폴더 생성) |
| GET | [api/myWordFolder/{id}](https://github.com/KoreaGuide/KoreaGuide_Server/blob/main/API-GUIDE/MYWORDFOLDER.md) | YES | READ | Get my word folder list (내 단어장 폴더 목록 보기) |
| DELETE | [api/myWordFolder/{id}](https://github.com/KoreaGuide/KoreaGuide_Server/blob/main/API-GUIDE/MYWORDFOLDER.md) | YES | DELETE | Delete my word folder list(내 특정 단어장 폴더 삭제) |
| PATCH | [api/myWordFolder/{id}](https://github.com/KoreaGuide/KoreaGuide_Server/blob/main/API-GUIDE/MYWORDFOLDER.md) | YES | UPDATE | Modify my word folder name(내 특정 단어장 이름 변경) |
| GET | [api/myWordFolder/learn/{id}?page={pageNumber}](https://github.com/KoreaGuide/KoreaGuide_Server/blob/main/API-GUIDE/MYWORDFOLDER.md) | YES | READ | Learn certain words in a particular word folder (내 특정 단어장 폴더에 있는 단어들 학습하기) - With Pagination |
| GET | [api/myWordFolder/learnWord/{id}/{folderId}](https://github.com/KoreaGuide/KoreaGuide_Server/blob/main/API-GUIDE/MYWORDFOLDER.md) | YES | READ |Learn certain words in a particular word folder (내 특정 단어장 폴더에 있는 단어들 학습하기) - Without Pagination|

__MyWord (내 단어장) Related Endpoints__
|**Request Type**|**Path**|**Need Token**|**Method**|**Description**|
|---|------|---|---|---|
| POST | [/api/myWord/{id}](https://github.com/KoreaGuide/KoreaGuide_Server/blob/main/API-GUIDE/MYWORD.md) | YES | CREATE | Add certain word to a particular word folder(특정 단어장 폴더에 특정 단어 추가) |
| GET | [api/myWord/{id}/{wordFolderId](https://github.com/KoreaGuide/KoreaGuide_Server/blob/main/API-GUIDE/MYWORD.md) | YES | READ |Get all wordlist in a particular word folder(특정 단어장 폴더에 있는 단어들 정보 조회) |
| DELETE | [/api/myWord/{id}](https://github.com/KoreaGuide/KoreaGuide_Server/blob/main/API-GUIDE/MYWORD.md) | YES | DELETE | Delete a particular word in a word folder(특정 단어장 폴더에 있는 특정 단어 삭제) |

__Place Related Endpoints__
|**Request Type**|**Path**|**Need Token**|**Method**|**Description**|
|---|------|---|---|---|
| GET | [/api/place/regionList/{id}](https://github.com/KoreaGuide/KoreaGuide_Server/blob/main/API-GUIDE/PLACE.md) | YES | READ | Get all region list (모든 지역에 대한 정보 조회(지역 리스트 받기))|
| PATCH | [/api/place/regionList/{id}](https://github.com/KoreaGuide/KoreaGuide_Server/blob/main/API-GUIDE/PLACE.md) | YES | UPDATE | Change color of a certain region (어떤 지역의 색 바꾸기) |
| GET | [/api/place/region/{userId}/{id}](https://github.com/KoreaGuide/KoreaGuide_Server/blob/main/API-GUIDE/PLACE.md) | YES | READ | Get all place list in a certain region(특정 지역에 대한 관광지 리스트 조회) |

__Place Detail Related Endpoints__
|**Request Type**|**Path**|**Need Token**|**Method**|**Description**|
|---|------|---|---|---|
| GET | [/api/place/detail/{userId}/{id}](https://github.com/KoreaGuide/KoreaGuide_Server/blob/main/API-GUIDE/PLACEDETAIL.md) | YES | READ | Get information of a certain place in both English & Korean (특정 관광지에 대한 정보 조회 (영어 & 한국어))|
| GET | [/api/place/detail/eng/{userId}/{id}](https://github.com/KoreaGuide/KoreaGuide_Server/blob/main/API-GUIDE/PLACEDETAIL.md) | YES | READ | Get information of a certain place in English (특정 관광지에 대한 정보 조회 (영어))|
| GET | [/api/place/detail/kor/{userId}/{id}](https://github.com/KoreaGuide/KoreaGuide_Server/blob/main/API-GUIDE/PLACEDETAIL.md) | YES | READ | Get information of a certain place in Korean (특정 관광지에 대한 정보 조회 (한국어))|
| GET | [/api/place/word/{userId}/{id}?page={pageNumber}](https://github.com/KoreaGuide/KoreaGuide_Server/blob/main/API-GUIDE/PLACEDETAIL.md) | YES | READ |Get all wordlist related to a certain place (특정 관광지의 단어 리스트) - with pagination|
| GET | [/api/place/wordList/{userId}/{id}](https://github.com/KoreaGuide/KoreaGuide_Server/blob/main/API-GUIDE/PLACEDETAIL.md) | YES | READ | Get all wordlist related to a certain place (특정 관광지의 단어 리스트) - without pagination| 
   
__My Map Related Endpoints__
|**Request Type**|**Path**|**Need Token**|**Method**|**Description**|
|---|------|---|---|---|
| POST | [api/myMap/{id}](https://github.com/KoreaGuide/KoreaGuide_Server/blob/main/API-GUIDE/MYMAP.md) | YES | CREATE | Add place to my map(내 지도에 장소 넣기(wish & have been to))|
| GET | [api/myMap/all/{id}](https://github.com/KoreaGuide/KoreaGuide_Server/blob/main/API-GUIDE/MYMAP.md) | YES | READ | Get all place list in my map(내 지도에 포함된 모든 장소 보기(wish & have been to))|
| GET | [api/myMap/wish/{id}](https://github.com/KoreaGuide/KoreaGuide_Server/blob/main/API-GUIDE/MYMAP.md) | YES | READ | Get place list in my map(내 지도에 포함된 장소 보기(wish ONLY))|
| GET | [api/myMap/haveBeen/{id}](https://github.com/KoreaGuide/KoreaGuide_Server/blob/main/API-GUIDE/MYMAP.md) | YES | READ |Get place list in my map(내 지도에 포함된 장소 보기(have been to ONLY))|
| PATCH | [api/myMap/{id}](https://github.com/KoreaGuide/KoreaGuide_Server/blob/main/API-GUIDE/MYMAP.md) | YES | UPDATE | Modify place information in my map(내 지도에 있는 장소 수정)|
| DELETE | [api/myMap/{id}](https://github.com/KoreaGuide/KoreaGuide_Server/blob/main/API-GUIDE/MYMAP.md) | YES | DELETE | Delete place in my map(내 지도에 있는 장소 삭제)|
| POST | [api/myMap/upload/{id}](https://github.com/KoreaGuide/KoreaGuide_Server/blob/main/API-GUIDE/MYMAP.md) | YES | CREATE / UPDATE | Create and update json file for map customization(지도 색 부분에서 필요한 json 파일 올리기 / 업데이트)|
| GET | [api/myMap/download/{id}](https://github.com/KoreaGuide/KoreaGuide_Server/blob/main/API-GUIDE/MYMAP.md) | YES | READ | Get json file related to map color(지도 색 부분에서 필요한 json 파일 받기)|

__WORD Related Endpoints__
|**Request Type**|**Path**|**Need Token**|**Method**|**Description**|
|---|------|---|---|---|
| GET | [api/word/{wordId}](https://github.com/KoreaGuide/KoreaGuide_Server/blob/main/API-GUIDE/WORD.md) | YES | READ | Get specific information of a certain word(특정 단어에 대한 정보 조회 (HOME의 단어랑 연결시 사용)) |

__Quiz Related Endpoints__
|**Request Type**|**Path**|**Need Token**|**Method**|**Description**|
|---|------|---|---|---|
| POST | [api/quiz/{id}](https://github.com/KoreaGuide/KoreaGuide_Server/blob/main/API-GUIDE/QUIZ.md) | YES | READ | Get quiz material(해당 퀴즈에 출제될 문제목록 및 객관식 목록 받아오기) |
| POST | [api/quiz/result/{id}](https://github.com/KoreaGuide/KoreaGuide_Server/blob/main/API-GUIDE/QUIZ.md) | YES | UPDATE | Update quiz result(퀴즈 종료 후 해당 퀴즈의 결과 서버에 업데이트) |

__Profile Related Endpoints__
|**Request Type**|**Path**|**Need Token**|**Method**|**Description**|
|---|------|---|---|---|
| GET | [api/profile/{id}](https://github.com/KoreaGuide/KoreaGuide_Server/blob/main/API-GUIDE/PROFILE.md) | YES | READ | Get information of users attendance & quiz result for this week (사용자의 1주일치 출석 & 단어 시험 결과 조회) |

## ERD
[See SQL](https://github.com/KoreaGuide/KoreaGuide_Server/blob/main/sqlStmts.md)
   <img width="981" alt="스크린샷 2021-06-07 오전 12 52 42" src="https://user-images.githubusercontent.com/52744390/120931007-ac684300-c72a-11eb-9a01-4ec084502d6c.png">
   
## 📥Examples & Explanation of Basic Request & Responses📥 
<details>
<summary> Example of Token in header </summary>      
<b>Basic Token header</b>         

```
Authorization Bearer eyJhbGciOiJIUzI1NiJ9.eyJ1c2VySWQiOjExLCJuYW1lIjoiQ2luZGlhIn0.vNieUBizIAzhwpAv_J2m9GSMMUO96LTaWPLxYYOG_W8
```   
 
<b>Token for login & register & checkDuplicate (Made due to front-end request)</b>   
```
Authorization Bearer no_value
```
</details>


<details>
<summary>
Response if wrong / no Token provided (헤더에 토큰 없거나 틀려서 생기는 오류)</summary>     
 <img width="842" alt="스크린샷 2021-08-21 오후 1 01 42" src="https://user-images.githubusercontent.com/52744390/130309914-5243bae8-51d5-40f7-adb2-5d95f1120537.png">   

</details>

<details>
<summary> Explanation of Pagination </summary>   
<img width="842" alt="스크린샷 2021-08-21 오후 1 01 07" src="https://user-images.githubusercontent.com/52744390/130309904-b6fd9b5e-5957-4863-a85f-cf13bb3fac8e.png">
    
    
```
    - total pages: 총 페이지
    - total elements: 페이지네이션하는 아이템의 총 수
    - current page: 현재 페이지 
    - current elements: 현재 페이지에 있는 아이템의 수 

** pageNumber은 1부터 시작! 
```
</details>


## 👩🏻‍💻Team Members & role👩🏻‍💻
* [Jisoo Kim](https://github.com/cindia3704) - Backend & UI design
* [Chihyun Song](https://github.com/alzee03) - Front-end (Main)
* [Seonho Im](https://github.com/imseonho) - External API & Data filtering & Front-end (Sub)

## 📆Overall Development Schedule📆
🔴 All Team Members    
🟢 [Jisoo Kim](https://github.com/cindia3704)   
🔵 [Chihyun Song](https://github.com/alzee03)   
🟡 [Seonho Im](https://github.com/imseonho)   
<img width="558" alt="스크린샷 2021-08-21 오후 12 42 48" src="https://user-images.githubusercontent.com/52744390/130309440-2855f485-30ec-4a07-a6c9-0a476a89d2d8.png">


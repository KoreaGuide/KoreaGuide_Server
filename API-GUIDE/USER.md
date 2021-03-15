# This is a document related to the User services

## User Create (Register)
__Request Form:__   
Path: api/user   
Request Type: POST   
```json
{
    "data": {
        "email": "cindi@gmail.com",
        "password": "1111",
        "nickname": "Jisoo Kim",
        "level": "LOW"
    }
}
```
   
__Response Form(Good Response):__
```json
{
    "result_code": 201,
    "status": "CREATED",
    "description": "OK",
    "data": {
        "id": 9,
        "email": "cindi@gmail.com",
        "password": "$2a$10$nluD0omnUu1SQmv.3xRP3e5ITR2FJailmsF4JmD6BQfFsSZc3Er3e",
        "nickname": "Jisoo Kim",
        "level": "LOW",
        "created_at": "2021-03-12T20:22:28.292666",
        "created_by": "Admin",
        "week_attendance": 0
    }
}
```
Example from Postman: 
<img width="1416" alt="스크린샷 2021-03-12 오후 8 22 39" src="https://user-images.githubusercontent.com/52744390/110933918-b28b9500-8370-11eb-9a21-e8f9293b5335.png">

__Response Form(Bad Response - user with email exists):__
```json
{
    "result_code": 409,
    "status": "CONFLICT",
    "description": "Unique Field Error"
}
```
Example from Postman: 
<img width="1416" alt="스크린샷 2021-03-12 오후 8 22 20" src="https://user-images.githubusercontent.com/52744390/110933885-a69fd300-8370-11eb-8e96-3655324ba243.png">

## User Read
__Request Form:__   
Path: api/user/{id}   
Request Type: GET   
<span style="color:red">주의!! --헤더에 TOKEN 필요!!</span>   
__헤더 토큰 예시__
```
Authorization Bearer eyJhbGciOiJIUzI1NiJ9.eyJ1c2VySWQiOjExLCJuYW1lIjoiQ2luZGlhIn0.vNieUBizIAzhwpAv_J2m9GSMMUO96LTaWPLxYYOG_W8
```
   
__Response Form (Good Response):__ 
```json
{
    "result_code": 200,
    "status": "OK",
    "description": "OK",
    "data": {
        "id": 9,
        "email": "cindi@gmail.com",
        "password": "$2a$10$nluD0omnUu1SQmv.3xRP3e5ITR2FJailmsF4JmD6BQfFsSZc3Er3e",
        "nickname": "Jisoo Kim",
        "level": "LOW",
        "created_at": "2021-03-12T20:22:28",
        "created_by": "Admin",
        "week_attendance": 0
    }
}
```
Example from Postman: 
<img width="1416" alt="스크린샷 2021-03-12 오후 8 29 20" src="https://user-images.githubusercontent.com/52744390/110934654-a227ea00-8371-11eb-884a-a3a8ac309aac.png">
   
__Response Form (BAD Response - user with id does not exist):__ 
```json
{
    "result_code": 500,
    "status": "INTERNAL_SERVER_ERROR",
    "description": "Cannot Find Entity"
}
```
__Response Form (BAD Response - 토큰 잘못왔거나 안온경우):__ 
```json
{
    "result_code": 401,
    "status": "UNAUTHORIZED",
    "description": "Not Logged in"
}
```
## User Delete 
__Request Form:__   
Path: api/user/{id}   
Request Type: DELETE      
<span style="color:red">주의!! --헤더에 TOKEN 필요!!</span>   
__헤더 토큰 예시__
```
Authorization Bearer eyJhbGciOiJIUzI1NiJ9.eyJ1c2VySWQiOjExLCJuYW1lIjoiQ2luZGlhIn0.vNieUBizIAzhwpAv_J2m9GSMMUO96LTaWPLxYYOG_W8
```
__Response Form(Good Response):__   
```json
{
    "result_code": 200,
    "status": "OK",
    "description": "OK"
}
```

__Response Form(Bad Response):__   
```json
{
    "result_code": 500,
    "status": "INTERNAL_SERVER_ERROR",
    "description": "Cannot Find User"
}
```
__Response Form (BAD Response - 토큰 잘못왔거나 안온경우):__ 
```json
{
    "result_code": 401,
    "status": "UNAUTHORIZED",
    "description": "Not Logged in"
}
```

## Check Duplicate Email 
__Request Form:__   
Path: api/user/checkDuplicate   
Request Type: POST    
```json
{
    "data": {
        "email": "amy1230@gmail.com"
    }
}
```
   
__Response Form (IF CAN USE) - 해당 이메일을 사용해도 되는 경우:__ 
```json
{
    "result_code": 200,
    "status": "OK",
    "description": "Email good to use"
}
```
Example from Postman: 
<img width="1416" alt="스크린샷 2021-03-12 오후 8 33 33" src="https://user-images.githubusercontent.com/52744390/110935036-38f4a680-8372-11eb-8ed1-02a36d54f894.png">

__Response Form (IF CANNOT USE) - 해당 이메일을 사용하면 안되는 경우( 이미 사용자 있음)__
```json
{
    "result_code": 409,
    "status": "CONFLICT",
    "description": "Unique Field Error"
}
```
Example from Postman: 
<img width="1416" alt="스크린샷 2021-03-12 오후 8 32 34" src="https://user-images.githubusercontent.com/52744390/110934949-16fb2400-8372-11eb-8702-aac00e9371f3.png">

## Login
__Request Form:__   
Path: api/user/login  
Request Type: POST   
```json
{
    "data": {
        "email": "amy1230@gmail.com",
        "password": "1111"
    }
}
```
   
__Response Form(Good Response):__
```json
{
    "result_code": 200,
    "status": "OK",
    "description": "OK",
    "data": {
        "id": 7,
        "email": "cindia@gmail.com",
        "password": "$2a$10$c2fvZdal64OLKUw3h0GW0ONfvrzSh2BSTmocPjfs2otGQpxW3KKXS",
        "nickname": "Jisoo Kim",
        "level": "LOW",
        "created_at": "2021-03-12T16:08:16",
        "created_by": "Admin",
        "token": "eyJhbGciOiJIUzI1NiJ9.eyJ1c2VySWQiOjcsIm5hbWUiOiJKaXNvbyBLaW0ifQ.xZTkLViaP4Bk1aQgdqTPRVb7a8aHJWdCmZCZy7v9ilI",
        "last_login_at": "2021-03-12",
        "week_attendance": 1
    }
}
```
Example from Postman: 
<img width="1416" alt="스크린샷 2021-03-12 오후 8 34 31" src="https://user-images.githubusercontent.com/52744390/110935133-5b86bf80-8372-11eb-818d-cee70428bcab.png">
__Response Form(Bad Response - email and password does not match):__
```json
{
    "result_code": 409,
    "status": "CONFLICT",
    "description": "Wrong Password"
}
```
Example from Postman: 
<img width="1416" alt="스크린샷 2021-03-12 오후 8 35 22" src="https://user-images.githubusercontent.com/52744390/110935218-79ecbb00-8372-11eb-82a2-87529dc98167.png">
__Response Form(Bad Response - User with eamil does not exist):__
```json
{
    "result_code": 500,
    "status": "INTERNAL_SERVER_ERROR",
    "description": "Cannot Find User"
}
```
Example from Postman: 
<img width="1416" alt="스크린샷 2021-03-12 오후 8 36 21" src="https://user-images.githubusercontent.com/52744390/110935318-9d176a80-8372-11eb-9249-bd00da4a526c.png">

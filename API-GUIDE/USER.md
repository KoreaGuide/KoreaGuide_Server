# This is a document related to the User services

## User Create (Register)
__Request Form:__   
Path: api/user   
Request Type: POST   
```json
{
    "data": {
        "email": "cindia@gmail.com",
        "password": "1111",
        "nickname": "Jisoo Kim",
        "level": "LOW"
    }
}
```
   
__Response Form(Good Response):__
```json
{
    "result_code": "OK",
    "status": "CREATED",
    "description": "OK",
    "data": {
        "id": 7,
        "email": "cindia@gmail.com",
        "password": "$2a$10$c2fvZdal64OLKUw3h0GW0ONfvrzSh2BSTmocPjfs2otGQpxW3KKXS",
        "nickname": "Jisoo Kim",
        "level": "LOW",
        "created_at": "2021-03-12T16:08:15.545682",
        "created_by": "Admin",
        "week_attendance": 0
    }
}
```
__Response Form(Bad Response - user with email exists):__
```json
{
    "result_code": "ERROR",
    "status": "CONFLICT",
    "description": "Email already exists"
}
```
Example from Postman: 
<img width="1416" alt="스크린샷 2021-03-12 오후 4 09 07" src="https://user-images.githubusercontent.com/52744390/110905111-5105ff00-834d-11eb-9134-cf4a795fb8ad.png">

## User Read
__Request Form:__   
Path: api/user/{id}   
Request Type: GET   
__Response Form (Good Response):__ 
```json
{
    "result_code": "OK",
    "status": "FOUND",
    "description": "OK",
    "data": {
        "id": 7,
        "email": "cindia@gmail.com",
        "password": "$2a$10$c2fvZdal64OLKUw3h0GW0ONfvrzSh2BSTmocPjfs2otGQpxW3KKXS",
        "nickname": "Jisoo Kim",
        "level": "LOW",
        "created_at": "2021-03-12T16:08:16",
        "created_by": "Admin",
        "week_attendance": 0
    }
}
```
Example from Postman: 
<img width="1416" alt="스크린샷 2021-03-12 오후 4 10 15" src="https://user-images.githubusercontent.com/52744390/110905182-709d2780-834d-11eb-9201-f2959ac13339.png">
   
__Response Form (BAD Response - user with id does not exist):__ 
```json
{
    "result_code": "ERROR",
    "status": "NOT_FOUND",
    "description": "Cannot find user"
}
```

## User Delete 
__Request Form:__   
Path: api/user/{id}   
Request Type: DELETE   
   
__Response Form(Good Response):__   
```json
{
    "result_code": "OK",
    "status": "OK",
    "description": "OK"
}
```

__Response Form(Bad Response):__   
```json
{
    "result_code": "ERROR",
    "status": "BAD_REQUEST",
    "description": "Cannot find user"
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
    "result_code": "OK",
    "status": "OK",
    "description": "Email good to use"
}
```

__Response Form (IF CANNOT USE) - 해당 이메일을 사용하면 안되는 경우( 이미 사용자 있음)__
```json
{
    "result_code": "ERROR",
    "status": "CONFLICT",
    "description": "Email already exists"
}
```

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
    "result_code": "OK",
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
<img width="1416" alt="스크린샷 2021-03-12 오후 4 11 45" src="https://user-images.githubusercontent.com/52744390/110905329-a6421080-834d-11eb-8bb7-76fd0ff1cda6.png">
__Response Form(Bad Response - email and password does not match):__
```json
{
    "result_code": "ERROR",
    "status": "CONFLICT",
    "description": "Wrong Password"
}
```
Example from Postman: 
<img width="1417" alt="스크린샷 2021-03-06 오후 4 38 32" src="https://user-images.githubusercontent.com/52744390/110199185-7ea8ff00-7e9a-11eb-9f6d-7748537ca94e.png">
__Response Form(Bad Response - User with eamil does not exist):__
```json
{ }
```

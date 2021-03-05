# This is a document related to the User services

## User Create (Register)
__Request Form:__   
Path: api/user   
Request Type: POST   
```json
{
    "data": {
        "email": "amy1230@gmail.com",
        "password": "1111",
        "nickname": "Amy Lee",
        "level": "HIGH"
    }
}
```
   
__Response Form(Good Response):__
```json
{
    "result_code": "OK",
    "description": "OK",
    "data": {
        "id": 1,
        "email": "amy1230@gmail.com",
        "password": "$2a$10$.xjGjPf4//5s9PQHVKjALeVqs05lh/EmY0O2PQHdUyh09yyOepukW",
        "nickname": "Amy Lee",
        "level": "HIGH",
        "created_at": "2021-03-06T05:04:22.505976",
        "created_by": "Admin"
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
<img width="1417" alt="스크린샷 2021-03-06 오전 5 07 54" src="https://user-images.githubusercontent.com/52744390/110168647-d31c9200-7e3a-11eb-8a3b-3dcb296c2152.png">

## User Read
__Request Form:__   
Path: api/user/{id}   
Request Type: GET   
__Response Form (Good Response):__ 
```json
{
    "result_code": "OK",
    "description": "OK",
    "data": {
        "id": 1,
        "email": "amy1230@gmail.com",
        "password": "$2a$10$.xjGjPf4//5s9PQHVKjALeVqs05lh/EmY0O2PQHdUyh09yyOepukW",
        "nickname": "Amy Lee",
        "level": "HIGH",
        "created_at": "2021-03-06T05:04:23",
        "created_by": "Admin"
    }
}
```
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
    "description": "OK",
    "data": {
        "id": 1,
        "email": "amy1230@gmail.com",
        "password": "$2a$10$.xjGjPf4//5s9PQHVKjALeVqs05lh/EmY0O2PQHdUyh09yyOepukW",
        "nickname": "Amy Lee",
        "level": "HIGH",
        "created_at": "2021-03-06T05:04:23",
        "created_by": "Admin",
        "token": "eyJhbGciOiJIUzI1NiJ9.eyJ1c2VySWQiOjEsIm5hbWUiOiJBbXkgTGVlIn0._T4Z5zuA9a0XJIWkwg3zkfuckJ00woF7-ZuAs3-rn2M"
    }
}
```
Example from Postman: 
<img width="1417" alt="스크린샷 2021-03-06 오전 5 10 51" src="https://user-images.githubusercontent.com/52744390/110168498-a2d4f380-7e3a-11eb-971d-1dd4a924433a.png">
__Response Form(Bad Response - email and password does not match):__
```json
{
    "result_code": "ERROR",
    "status": "CONFLICT",
    "description": "Wrong Password"
}
```
Example from Postman: 
<img width="1417" alt="스크린샷 2021-03-06 오전 5 11 58" src="https://user-images.githubusercontent.com/52744390/110168506-a6687a80-7e3a-11eb-9ad3-8efffe4b2755.png">

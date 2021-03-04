# This is a document related to the User services

## User Create (Register)
Request Form:
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

Response Form:
```json
    "result_code": "OK",
    "description": "OK",
    "access_token": null,
    "data": {
        "id": 11,
        "email": "amy1230@gmail.com",
        "password": "$2a$10$63.JGTFUh.pmPpZvojFTR..WWbDxzLHR6YsstziQ/yzJMugVVhAXi",
        "nickname": "Amy Lee",
        "level": "HIGH",
        "created_at": "2021-03-04T23:51:50.957336",
        "created_by": "Admin"
    }
}
```

## User Read
Request Form:
Path: api/user/{id}
Request Type: GET
Response Form:
```json
    "result_code": "OK",
    "description": "OK",
    "access_token": null,
    "data": {
        "id": 11,
        "email": "amy1230@gmail.com",
        "password": "$2a$10$63.JGTFUh.pmPpZvojFTR..WWbDxzLHR6YsstziQ/yzJMugVVhAXi",
        "nickname": "Amy Lee",
        "level": "HIGH",
        "created_at": "2021-03-04T23:51:50.957336",
        "created_by": "Admin"
    }
}
```

## User Delete 
Request Form:
Path: api/user/{id}
Request Type: DELETE
   
Response Form:
```json
{
    "result_code": "OK",
    "description": "OK",
    "access_token": null,
    "data": null
}
```


## Check Duplicate Email 
Request Form: 
Path: api/user/checkDuplicate
Request Type: POST 
```json
{
    "data": {
        "email": "amy1230@gmail.com"
    }
}
```
   
Response Form (IF CAN USE) - 해당 이메일을 사용해도 되는 경우: 
```json
{
    "result_code": "OK",
    "description": "Email good to use",
    "access_token": null,
    "data": null
}
```

Response Form (IF CANNOT USE) - 해당 이메일을 사용하면 안되는 경우( 이미 사용자 있음)
```json
{
    "result_code": "OK",
    "description": "Email already exists",
    "access_token": null,
    "data": null
}
```

# This is a document related to the MyWordFolder services

## MyWordFolder CREATE (단어장 폴더 생성)
__Request Form:__   
Path: api/myWordFolder/  
Request Type: POST    
__Request Form:__
```json
{
    "data": {
        "user_id": 18,
        "folder_name": "음식관련 단어들"
    }
}
```
      
__Response Form(Good Response):__   
```json
{
    "result_code": 201,
    "status": "CREATED",
    "description": "Successfully created",
    "data": {
        "user_id": 18,
        "word_folder_id": 1,
        "folder_name": "음식관련 단어들"
    }
}
```
   

## MyWordFolder READ (단어장 폴더 목록 읽기)
__Request Form:__      
Path: api/myWordFolder/{id} __**여기서 id = userID(integer)__     
Request Type: GET      

__Response Form(Good Response):__
```json
{
    "result_code": 200,
    "status": "OK",
    "description": "OK",
    "data": [
        {
            "word_folder_id": 1,
            "folder_name": "음식관련 단어들",
            "word_count": 0
        }
    ]
}
```
   
__Response Form(Good Response: 단어장 폴더 목록이 없는 경우):__
```json
{
    "result_code": 204,
    "status": "NO_CONTENT",
    "description": "MyWordFolder is empty"
}
```
   
## MyWordFolder DELETE (특정 단어장 폴더 삭제)
__Request Form:__      
Path: api/myWordFolder/{id} __**여기서 id = userID(integer)__     
Request Type: DELETE     
   
__Response Form(Good Response):__
```json
{
    "result_code": 200,
    "status": "OK",
    "description": "OK",
    "data": {}
}
```
   
__Response Form(Bad Response: 삭제하려는 단어장 폴더가 없음):__
```json
{
    "result_code": 500,
    "status": "INTERNAL_SERVER_ERROR",
    "description": "Cannot Find MyWordFolder"
}
```

   
## MyWordFolder UPDATE (특정 단어장 폴더 이름 변경)
__Request Form:__   
Path: api/myWordFolder/{id} __**여기서 id = userID(integer)__     
Request Type: PATCH   
__Request Form:__
```json
{
    "data": {
        "word_folder_id": 2,
        "folder_name": "장소관련 단어들"
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
        "user_id": 18,
        "word_folder_id": 2,
        "folder_name": "장소관련 단어들"
    }
}
```
   
__Response Form(Bad Response: 수정하려는 단어장 폴더가 없음):__
```json
{
    "result_code": 500,
    "status": "INTERNAL_SERVER_ERROR",
    "description": "Cannot Find MyWordFolder"
}
```

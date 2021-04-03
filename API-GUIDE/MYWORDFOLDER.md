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
__Request Form:__
```json
{
  "data": {
    "word_folder_id": 2
  }
}
```
   
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

## MyWordFolder LEARN (특정 단어장 폴더에 있는 단어들 학습하기)
__Request Form:__   
Path: api/myWordFolder/learn/{id}?page={pageNumber} __**여기서 id = userID(integer)__ __**pageNumber = 1부터 시작!__     
__Path 예시:__
```
http://localhost:8080/api/myWordFolder/learn/18?page=1
```
Request Type: GET   
__Request Form:__
```json
{
    "data":{
        "word_folder_id":4
    }
}
```
__Response Form:__
```json
{
    "result_code": 200,
    "status": "OK",
    "description": "OK",
    "data": {
        "folder_id": 3,
        "folder_name": "장소 관련 단어들",
        "pagination": {
            "total_pages": 2,
            "total_elements": 2,
            "current_page": 1,
            "current_elements": 1
        },
        "word_list": [
            {
                "id": 15,
                "word_eng": "island",
                "word_kor": "섬",
                "meaning_eng": "island is an island",
                "meaning_kor": "섬은 섬입니다",
                "image": "http://tong.visitkorea.or.kr/cms/resource/23/2678623_image2_1.jpg",
                "audio": "https://drive.google.com/file/d/1RzIwX_41dpNL6M8J7VA19wjG20nBV6uA/view?usp=sharing",
                "my_word_status": "IN_MY_LIST"
            }
        ]
    }
}

```
__Response Form(Good Response: 해당 단어장에 단어 없음):__
```json
{
    "result_code": 204,
    "status": "NO_CONTENT",
    "description": "MyWordFolder is empty"
}
```

__Response Form(Bad Response: pageNumber이 잘못된 경우):__
```json
{
    "result_code": 500,
    "status": "INTERNAL_SERVER_ERROR",
    "description": "Pagination out of index"
}
```

__Response Form(Bad Response: 해당 단어장 폴더를 못찾겠는 경우):__
```json
{
    "result_code": 500,
    "status": "INTERNAL_SERVER_ERROR",
    "description": "Cannot Find MyWordFolder"
}
```

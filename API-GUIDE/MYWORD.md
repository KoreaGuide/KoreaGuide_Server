# This is a document related to the Home services

## my_word_status 종류: 
```
 - "NO_STATUS": 해당 단어가 내 단어장에 포함 되어있지 않음
 - "IN_MY_LIST": 단어가 내 단어장에 포함 되어있음 (BUT 정답 / 오답 기록 없음)
 - "KNOW": 단어가 내 단어장에 있으며, 사용자가 해당 단어를 안다 (학습시 know로 지정함)  
 - "DONT_KNOW":단어가 내 단어장에 있으며, 사용자가 해당 단어를 모른다 (학습 시 don't know 로 지정함) 
```


## MyWord CREATE (특정 단어를 내 단어장 폴더에 추가하기)
__Request Form:__   
Path: api/myWord/{id} __**여기서 id는 user의 id (Integer)__   
Request Type: POST   
__Request Form:__
```json
{
    "data": {
        "word_folder_id": 2,
        "word_id": 1
    }
}

```
__Response Form(Good Response):__
```json
{
    "result_code": 200,
    "status": "OK",
    "description": "Successfully added",
    "data": {
        "my_word_folder_id": 2,
        "previous_word_count": 0,
        "now_word_count": 1
    }
}
```
__Response Form(BAD Response - 추가하려는 단어가 없는 경우):__
```json
{
    "result_code": 500,
    "status": "INTERNAL_SERVER_ERROR",
    "description": "Cannot Find Word"
}
```
   
__Response Form(BAD Response - 추가하려는 단어가 이미 특정 단어장 폴더에 있는 경우):__
```json
{
    "result_code": 409,
    "status": "CONFLICT",
    "description": "MyWord already exists"
}
```
   
__Response Form(BAD Response - 단어를 추가하려는 단어장 폴더가 없는 경우):__
```json
{
    "result_code": 500,
    "status": "INTERNAL_SERVER_ERROR",
    "description": "Cannot Find MyWordFolder"
}
```
   
## MyWord READ (내 단어장 정보 읽기)
__Request Form:__   
Path: api/myWord/{id}/{wordFolderId} __**여기서 id는 user의 id (Integer)__   
Request Type: GET

__Response Form(Good Response):__
```json
{
    "result_code": 200,
    "status": "OK",
    "description": "OK",
    "data": {
        "my_word_folder_id": 2,
        "now_word_count": 2,
        "my_word_list": [
            {
                "id": 1,
                "word_eng": "fire",
                "word_kor": "불",
                "meaning_eng": "fire is hot",
                "meaning_kor": "불은 뜨거운 것",
                "image": "http://tong.visitkorea.or.kr/cms/resource/23/2678623_image2_1.jpg",
                "audio": "https://drive.google.com/file/d/12pQ8P_H2M2rxzwz_leTaYOvEo2CPzMqd/view?usp=sharing",
                "my_word_status": "IN_MY_LIST"
            },
            {
                "id": 5,
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
   
__Response Form(GOOD Response - 사용자의 단어장에 단어가 하나도 없는 경우):__
```json
{
    "result_code": 204,
    "status": "NO_CONTENT",
    "description": "MyWordFolder is empty"
}
```


__Response Form(BAD Response - 사용자가 없는 경우 / 토큰 잘못된 경우):__
```json
{
  "result_code": 401,
  "status": "UNAUTHORIZED",
  "description": "Not Logged in"
}
```

## MyWord DELETE (내 단어장에서 단어 삭제)
__Request Form:__   
Path: api/myWord/{id} __**여기서 id는 user의 id (Integer)__   
Request Type: DELETE
```json
{
    "data": {
        "word_folder_id": 2,
        "word_id":5
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
        "my_word_folder_id": 2,
        "previous_word_count": 2,
        "now_word_count": 1
    }
}
```

__Response Form(BAD Response - 삭제하려는 단어가 내 단어장에 없는 경우):__
```json
{
    "result_code": 500,
    "status": "INTERNAL_SERVER_ERROR",
    "description": "Cannot Find MyWord"
}
```




# This is a document related to the Word services

## WORD READ (특정 단어 정보 조회)
__Request Form:__   
Path: api/word/   
Request Type: GET   
__Response Form(Good Response):__
```json
{
    "data":{
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
    "id": 5,
    "word_eng": "island",
    "word_kor": "섬",
    "meaning_eng": "island is an island",
    "meaning_kor": "섬은 섬입니다",
    "image": "http://tong.visitkorea.or.kr/cms/resource/23/2678623_image2_1.jpg",
    "audio": "https://drive.google.com/file/d/1RzIwX_41dpNL6M8J7VA19wjG20nBV6uA/view?usp=sharing"
  }
}
```

__Response Form(Bad Response : 해당 아이디 갖는 단어 없음):__
```json
{
    "result_code": 500,
    "status": "INTERNAL_SERVER_ERROR",
    "description": "Cannot Find Word"
}
```

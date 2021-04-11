# This is a document related to the Word services

## WORD READ (특정 단어 정보 조회)
__Request Form:__   
Path: api/word/   
Request Type: GET   
__Request Form:__
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
        "id": 3,
        "word_kor": "건축물",
        "word_eng": "building",
        "meaning_kor1": "땅 위에 지은 건물이나 시설.",
        "meaning_kor2": "",
        "meaning_eng1": "A building or facility built on the ground.",
        "meaning_eng2": "",
        "pronunciation_eng": "geonchukmul",
        "audio": "",
        "image": "https://img.maisonkorea.com/2019/06/msk_5d1306577e2f0.jpg"
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

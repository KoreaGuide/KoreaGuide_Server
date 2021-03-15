# This is a document related to the Home services

## MyWord CREATE (특정 단어를 내 단어장에 추가하기)
__Request Form:__   
Path: api/myWord/{id} __**여기서 id는 user의 id (Integer)__   
Request Type: POST   
__Request Form:__
```json
{   
    "data": {
       "word_id":4
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
    "user_id": 11,
    "previous_word_count": 4,
    "now_word_count": 5
  }
}
```
__Response Form(BAD Response - 추가하려는 단어가 없는 경우):__
```json
{
  "result_code": 500,
  "status": "INTERNAL_SERVER_ERROR",
  "description": "Cannot Find Entity"
}
```
   
## MyWord READ (내 단어장 정보 읽기)
__Request Form:__   
Path: api/myWord/{id} __**여기서 id는 user의 id (Integer)__   
Request Type: GET

__Response Form(Good Response):__
```json
{
  "result_code": 200,
  "status": "OK",
  "description": "OK",
  "data": {
    "user_id": 11,
    "now_word_count": 5,
    "my_word_list": [
      {
        "id": 1,
        "word_eng": "fire",
        "word_kor": "불",
        "meaning_eng": "fire is hot",
        "meaning_kor": "불은 뜨거운 것",
        "image": "http://tong.visitkorea.or.kr/cms/resource/23/2678623_image2_1.jpg",
        "level": "LOW",
        "my_word_status": "NO_STATUS"
      },
      {
        "id": 2,
        "word_eng": "cat",
        "word_kor": "고양이",
        "meaning_eng": "cat is an animal",
        "meaning_kor": "고양이는 동물이다",
        "image": "http://tong.visitkorea.or.kr/cms/resource/23/2678623_image2_1.jpg",
        "level": "LOW",
        "my_word_status": "NO_STATUS"
      },
      {
        "id": 3,
        "word_eng": "korea",
        "word_kor": "한국",
        "meaning_eng": "Korea is a country in Asia",
        "meaning_kor": "한국은 아시아에 있는 나라다",
        "image": "http://tong.visitkorea.or.kr/cms/resource/23/2678623_image2_1.jpg",
        "level": "MID",
        "my_word_status": "NO_STATUS"
      },
      {
        "id": 5,
        "word_eng": "island",
        "word_kor": "섬",
        "meaning_eng": "island is an island",
        "meaning_kor": "섬은 섬입니다",
        "image": "http://tong.visitkorea.or.kr/cms/resource/23/2678623_image2_1.jpg",
        "level": "MID",
        "my_word_status": "NO_STATUS"
      },
      {
        "id": 4,
        "word_eng": "CAU",
        "word_kor": "중앙대학교",
        "meaning_eng": "CAU is a university in Korea",
        "meaning_kor": "중앙대학교는 서울에 있는 학교다.",
        "image": "http://tong.visitkorea.or.kr/cms/resource/23/2678623_image2_1.jpg",
        "level": "MID",
        "my_word_status": "NO_STATUS"
      }
    ]
  }
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



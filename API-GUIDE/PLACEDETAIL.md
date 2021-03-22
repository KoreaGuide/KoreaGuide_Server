# This is a document related to the Place Detail services

## Place Detail ALL READ (특정 관광지 정보 조회 -- 영어 & 한국어)
__Request Form:__   
Path: api/place/detail/{id} __**여기서 id 는 place_id(integer)__   
Request Type: GET   
   
  
__Response Form(Good Response):__
```json
{
    "result_code": 200,
    "status": "OK",
    "description": "OK",
    "data": {
        "user_id": 18,
        "place_status": "NO_STATUS",
        "id": 100,
        "title": "Gyeongbokgung Palace (경복궁)",
        "content_id": 264337,
        "area_code": 1,
        "address1": "161, Sajik-ro, Jongno-gu, Seoul",
        "address2": "null",
        "first_image": "http://tong.visitkorea.or.kr/cms/resource/23/2678623_image2_1.jpg",
        "first_image2": "http://tong.visitkorea.or.kr/cms/resource/23/2678623_image3_1.jpg",
        "map_x": "126.9769930325",
        "map_y": "37.5788222356",
        "overview_english": "Built in 1395, Gyeongbokgung Palace is also commonly referred to as the Northern Palace because its location is furthest north when compared to the neighboring palaces of Changdeokgung (Eastern Palace) and Gyeonghuigung (Western Palace) Palace. Gyeongbokgung Palace is arguably the most beautiful, and remains the largest of all five palaces.    The premises were once destroyed by fire during the Imjin War (1592-1598). However, all of the palace buildings were later restored under the leadership of Heungseondaewongun during the reign of King Gojong (1852-1919).       Remarkably, the most representative edifices of the Joseon dynasty, Gyeonghoeru Pavilion and the pond around Hyangwonjeong Pavillion have remained relatively intact. The raised dias and stone markers of Geunjeongjeon showcase the representative art style of their time.      The National Palace Museum of Korea is located south of Heungnyemun Gate, and the National Folk Museum is located on the eastern side of Hyangwonjeong Pavillion.",
        "overview_korean": "경복궁에 대한 설명입니다.. 경복궁은 좋은 곳 입니다!!",
        "category1": "A02",
        "category2": "A0201",
        "category3": "A02010100"
    }
}
```

__Response Form(Bad Response- place_id 가 잘못됨 <-- 이런 관광지 없음):__
```json
{
    "result_code": 500,
    "status": "INTERNAL_SERVER_ERROR",
    "description": "Cannot Find Place"
}
```

## Place Detail English READ (특정 관광지 정보 조회 -- 영어만)
__Request Form:__   
Path: api/place/detail/eng/{id} __**여기서 id 는 place_id(integer)__   
Request Type: GET   
   
  
__Response Form(Good Response):__
```json
{
    "result_code": 200,
    "status": "OK",
    "description": "OK",
    "data": {
        "user_id": 18,
        "place_status": "NO_STATUS",
        "id": 100,
        "title": "Gyeongbokgung Palace (경복궁)",
        "content_id": 264337,
        "area_code": 1,
        "address1": "161, Sajik-ro, Jongno-gu, Seoul",
        "address2": "null",
        "first_image": "http://tong.visitkorea.or.kr/cms/resource/23/2678623_image2_1.jpg",
        "first_image2": "http://tong.visitkorea.or.kr/cms/resource/23/2678623_image3_1.jpg",
        "map_x": "126.9769930325",
        "map_y": "37.5788222356",
        "overview_english": "Built in 1395, Gyeongbokgung Palace is also commonly referred to as the Northern Palace because its location is furthest north when compared to the neighboring palaces of Changdeokgung (Eastern Palace) and Gyeonghuigung (Western Palace) Palace. Gyeongbokgung Palace is arguably the most beautiful, and remains the largest of all five palaces.    The premises were once destroyed by fire during the Imjin War (1592-1598). However, all of the palace buildings were later restored under the leadership of Heungseondaewongun during the reign of King Gojong (1852-1919).       Remarkably, the most representative edifices of the Joseon dynasty, Gyeonghoeru Pavilion and the pond around Hyangwonjeong Pavillion have remained relatively intact. The raised dias and stone markers of Geunjeongjeon showcase the representative art style of their time.      The National Palace Museum of Korea is located south of Heungnyemun Gate, and the National Folk Museum is located on the eastern side of Hyangwonjeong Pavillion.",
        "category1": "A02",
        "category2": "A0201",
        "category3": "A02010100"
    }
}
```

__Response Form(Bad Response- place_id 가 잘못됨 <-- 이런 관광지 없음):__
```json
{
    "result_code": 500,
    "status": "INTERNAL_SERVER_ERROR",
    "description": "Cannot Find Place"
}
```

## Place Detail Korean READ (특정 관광지 정보 조회 -- 한국어만)
__Request Form:__   
Path: api/place/detail/kor/{id} __**여기서 id 는 place_id(integer)__   
Request Type: GET   
   
  
__Response Form(Good Response):__
```json
{
    "result_code": 200,
    "status": "OK",
    "description": "OK",
    "data": {
        "user_id": 18,
        "place_status": "NO_STATUS",
        "id": 100,
        "title": "Gyeongbokgung Palace (경복궁)",
        "content_id": 264337,
        "area_code": 1,
        "address1": "161, Sajik-ro, Jongno-gu, Seoul",
        "address2": "null",
        "first_image": "http://tong.visitkorea.or.kr/cms/resource/23/2678623_image2_1.jpg",
        "first_image2": "http://tong.visitkorea.or.kr/cms/resource/23/2678623_image3_1.jpg",
        "map_x": "126.9769930325",
        "map_y": "37.5788222356",
        "overview_korean": "경복궁에 대한 설명입니다.. 경복궁은 좋은 곳 입니다!!",
        "category1": "A02",
        "category2": "A0201",
        "category3": "A02010100"
    }
}
}
```

__Response Form(Bad Response- place_id 가 잘못됨 <-- 이런 관광지 없음):__
```json
{
    "result_code": 500,
    "status": "INTERNAL_SERVER_ERROR",
    "description": "Cannot Find Place"
}
```

## Place Related Words  (특정 관광지와 관련된 단어 페이지네이션)
__Request Form:__   
Path: api/place/word/{id}?page={pageNumber} __**여기서 id 는 place_id(integer)__  __**pageNumber = 1부터 시작!__    
__Path 예시:__
```
http://localhost:8080/api/place/word/100?page=1
```
Request Type: GET     
__Response Form(Good Response):__
```json
{
  "result_code": 200,
  "status": "OK",
  "description": "OK",
  "data": {
    "user_id": 18,
    "place_id": 100,
    "pagination": {
      "total_pages": 2,
      "total_elements": 3,
      "current_page": 1,
      "current_elements": 2
    },
    "word_list": [
      {
        "word_status": "NO_STATUS",
        "word_id": 1,
        "word_kor": "불",
        "word_eng": "fire",
        "word_image": "http://tong.visitkorea.or.kr/cms/resource/23/2678623_image2_1.jpg",
        "word_audio": "https://drive.google.com/file/d/12pQ8P_H2M2rxzwz_leTaYOvEo2CPzMqd/view?usp=sharing"
      },
      {
        "word_status": "NO_STATUS",
        "word_id": 2,
        "word_kor": "고양이",
        "word_eng": "cat",
        "word_image": "http://tong.visitkorea.or.kr/cms/resource/23/2678623_image2_1.jpg",
        "word_audio": "https://drive.google.com/file/d/1pHRW50oxel6UbOdlLNo6e-LUfpaAik43/view?usp=sharing"
      }
    ]
  }
}
```

__Response Form(Bad Response -- PageNumber가 잘못됨):__
```json
{
    "result_code": 500,
    "status": "INTERNAL_SERVER_ERROR",
    "description": "Pagination out of index"
}
```
__Response Form(Bad Response- place_id 가 잘못됨 <-- 이런 관광지 없음):__
```json
{
    "result_code": 500,
    "status": "INTERNAL_SERVER_ERROR",
    "description": "Cannot Find Place"
}
```
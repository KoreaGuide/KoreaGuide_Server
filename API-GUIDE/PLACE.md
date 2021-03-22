# This is a document related to the Place services

## REGION LIST READ (모든 지역 리스트 가져오기)
__Request Form:__   
Path: api/place/regionList/{id} __**여기서 id 는 userId(integer)__   
Request Type: GET   
   
  
__Response Form(Good Response):__
```json
{
    "result_code": 200,
    "status": "OK",
    "description": "OK",
    "data": {
        "region_list": [
            {
                "id": 1,
                "areacode": 1,
                "areaname_kor": "서울",
                "areaname_eng": "Seoul",
                "color": "NONE"
            },
            {
                "id": 31,
                "areacode": 31,
                "areaname_kor": "경기도",
                "areaname_eng": "Gyeonggi-do",
                "color": "NONE"
            },
            {
                "id": 32,
                "areacode": 32,
                "areaname_kor": "강원도",
                "areaname_eng": "Gangwon-do",
                "color": "NONE"
            },
            {
                "id": 33,
                "areacode": 33,
                "areaname_kor": "충청북도",
                "areaname_eng": "Chungcheongbuk-do",
                "color": "NONE"
            },
            {
                "id": 34,
                "areacode": 34,
                "areaname_kor": "충청남도",
                "areaname_eng": "Chungcheongnam-do",
                "color": "NONE"
            },
            {
                "id": 35,
                "areacode": 35,
                "areaname_kor": "경상북도",
                "areaname_eng": "Gyeongsangbuk-do",
                "color": "NONE"
            },
            {
                "id": 36,
                "areacode": 36,
                "areaname_kor": "경상남도",
                "areaname_eng": "Gyeongsangnam-do",
                "color": "NONE"
            },
            {
                "id": 37,
                "areacode": 37,
                "areaname_kor": "전라북도",
                "areaname_eng": "Jeollabuk-do",
                "color": "NONE"
            },
            {
                "id": 38,
                "areacode": 38,
                "areaname_kor": "전라남도",
                "areaname_eng": "Jeollanam-do",
                "color": "NONE"
            },
            {
                "id": 39,
                "areacode": 39,
                "areaname_kor": "제주도",
                "areaname_eng": "Jeju-do",
                "color": "NONE"
            }
        ]
    }
}
```

## REGION LIST COLOR UPDATE (사용자의 지역 색 변경)
__Request Form:__   
Path: api/place/regionList/{id} __**여기서 id 는 userId(integer)__   
Request Type: PATCH   

__**여기서 region_id는 areacode랑 똑같음__   
__color은 다음5가지 외에 것을 입력하면 에러남 (이건 나중에 색 같이 정하고 변경해야함)__   
```
   - NONE
   - RED
   - BLUE
   - GREEN
   - PINK
```
__Request Form:__
```json
{
  "data": {
    "region_id": 2,
    "color":"RED"
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
    "region_list": [
      {
        "id": 1,
        "areacode": 1,
        "areaname_kor": "서울",
        "areaname_eng": "Seoul",
        "color": "RED"
      }
    ]
  }
}
```
__Response Form(Bad Response- region_id 가 잘못됨 <-- 이런 지역 없음):__
```json
{
    "result_code": 500,
    "status": "INTERNAL_SERVER_ERROR",
    "description": "Cannot Find Region"
}
```

## PLACE LIST FOR REGION READ (특정 지역의 관광지 목록 보기)
__Request Form:__   
Path: api/place/region/{id} __**여기서 id 는 region_id(integer)__         
Request Type: GET   
__Response Form(Good Response):__
```json
{
  "result_code": 200,
  "status": "OK",
  "description": "OK",
  "data": {
    "user_id": 18,
    "region_id": 1,
    "region_color": "RED",
    "place_list": [
      {
        "id": 100,
        "content_id": 264337,
        "title": "Gyeongbokgung Palace (경복궁)",
        "address1": "161, Sajik-ro, Jongno-gu, Seoul",
        "address2": "null",
        "first_image": "http://tong.visitkorea.or.kr/cms/resource/23/2678623_image2_1.jpg",
        "first_image2": "http://tong.visitkorea.or.kr/cms/resource/23/2678623_image3_1.jpg",
        "map_x": "126.9769930325",
        "map_y": "37.5788222356",
        "overview": "Built in 1395, Gyeongbokgung Palace is also commonly referred to as the Northern Palace because its location is furthest north when compared to the neighboring palaces of Changdeokgung (Eastern Palace) and Gyeonghuigung (Western Palace) Palace. Gyeongbokgung Palace is arguably the most beautiful, and remains the largest of all five palaces.    The premises were once destroyed by fire during the Imjin War (1592-1598). However, all of the palace buildings were later restored under the leadership of Heungseondaewongun during the reign of King Gojong (1852-1919).       Remarkably, the most representative edifices of the Joseon dynasty, Gyeonghoeru Pavilion and the pond around Hyangwonjeong Pavillion have remained relatively intact. The raised dias and stone markers of Geunjeongjeon showcase the representative art style of their time.      The National Palace Museum of Korea is located south of Heungnyemun Gate, and the National Folk Museum is located on the eastern side of Hyangwonjeong Pavillion."
      },
      {
        "id": 101,
        "content_id": 264152,
        "title": "Lotte World (롯데월드)",
        "address1": "240, Olympic-ro, Songpa-gu, Seoul",
        "address2": "null",
        "first_image": "http://tong.visitkorea.or.kr/cms/resource/77/2553577_image2_1.jpg",
        "first_image2": "http://tong.visitkorea.or.kr/cms/resource/77/2553577_image3_1.jpg",
        "map_x": "127.0979006014",
        "map_y": "37.5113516917",
        "overview": "Operated by Lotte Group, Lotte World is the perfect spot for entertainment and sightseeing for Koreans and international tourists alike. The theme park is divided into the indoor Lotte World Adventure, and the outdoor lakeside Magic Island, with additional amenities including a shopping mall, folk museum, ice rink, hotel, and more.     Lotte World Adventure is the world's largest indoor amusement park, complete with top-of-the-line rides, fantastic parades and performances, and food from around the world. The Folk Museum displays miniature models of Korea throughout 5,000 years in history. Lotte World Garden Stage presents various themed musicals to match each season and Lotte World Star Avenue is the perfect place to experience Korean stars and the entertainment world."
      }
    ]
  }
}
```
__Response Form(Good Response- 해당 자역에 대한 관광지가 없을때):__
```json
{
  "result_code": 204,
  "status": "NO_CONTENT",
  "description": "Place is empty"
}
```
__Response Form(Bad Response- region_id 가 잘못됨 <-- 이런 지역 없음):__
```json
{
    "result_code": 500,
    "status": "INTERNAL_SERVER_ERROR",
    "description": "Cannot Find Region"
}
```

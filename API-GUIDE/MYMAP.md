# This is a document related to the Home services

## MyMap READ (내 지도에 추가된 장소 리스트 -all)
__Request Form:__   
Path: api/myMap/all/{id}  __**여기서 id는 user의 id (Integer)__   
Request Type: GET   

__Response Form(Good Response):__
```json
{
  "result_code": 200,
  "status": "OK",
  "description": "OK",
  "data": {
    "user_id": 18,
    "place_count": 2,
    "place_list": [
      {
        "my_map_id": 5,
        "place_id": 100,
        "place_status": "WISH_LIST",
        "title": "Gyeongbokgung Palace (경복궁)",
        "content_id": 264337,
        "address1": "161, Sajik-ro, Jongno-gu, Seoul",
        "first_image": "http://tong.visitkorea.or.kr/cms/resource/23/2678623_image2_1.jpg",
        "map_x": "126.9769930325",
        "map_y": "37.5788222356",
        "overview_english": "Built in 1395, Gyeongbokgung Palace is also commonly referred to as the Northern Palace because its location is furthest north when compared to the neighboring palaces of Changdeokgung (Eastern Palace) and Gyeonghuigung (Western Palace) Palace. Gyeongbokgung Palace is arguably the most beautiful, and remains the largest of all five palaces.    The premises were once destroyed by fire during the Imjin War (1592-1598). However, all of the palace buildings were later restored under the leadership of Heungseondaewongun during the reign of King Gojong (1852-1919).       Remarkably, the most representative edifices of the Joseon dynasty, Gyeonghoeru Pavilion and the pond around Hyangwonjeong Pavillion have remained relatively intact. The raised dias and stone markers of Geunjeongjeon showcase the representative art style of their time.      The National Palace Museum of Korea is located south of Heungnyemun Gate, and the National Folk Museum is located on the eastern side of Hyangwonjeong Pavillion."
      },
      {
        "my_map_id": 8,
        "place_id": 101,
        "place_status": "HAVE_BEEN_TO",
        "title": "Lotte World (롯데월드)",
        "content_id": 264152,
        "address1": "240, Olympic-ro, Songpa-gu, Seoul",
        "first_image": "http://tong.visitkorea.or.kr/cms/resource/77/2553577_image2_1.jpg",
        "map_x": "127.0979006014",
        "map_y": "37.5113516917",
        "overview_english": "Operated by Lotte Group, Lotte World is the perfect spot for entertainment and sightseeing for Koreans and international tourists alike. The theme park is divided into the indoor Lotte World Adventure, and the outdoor lakeside Magic Island, with additional amenities including a shopping mall, folk museum, ice rink, hotel, and more.     Lotte World Adventure is the world's largest indoor amusement park, complete with top-of-the-line rides, fantastic parades and performances, and food from around the world. The Folk Museum displays miniature models of Korea throughout 5,000 years in history. Lotte World Garden Stage presents various themed musicals to match each season and Lotte World Star Avenue is the perfect place to experience Korean stars and the entertainment world.",
        "diary": "안녕!!!"
      }
    ]
  }
}
```
  
__Response Form(Good Response- 내 지도에 추가된 장소 없음):__
```json
{
  "result_code": 204,
  "status": "NO_CONTENT",
  "description": "MyPlace is empty"
}
```

## MyMap READ (내 지도에 추가된 장소 리스트 -위시리스트만)
__Request Form:__   
Path: api/myMap/all/{id}  __**여기서 id는 user의 id (Integer)__   
Request Type: GET

__Response Form(Good Response):__
```json
```

## MyMap READ (내 지도에 추가된 장소 리스트 -다녀온 장소만)
__Request Form:__   
Path: api/myMap/all/{id}  __**여기서 id는 user의 id (Integer)__   
Request Type: GET

__Response Form(Good Response):__
```json
```
## MyMap CREATE (특정 장소를 내 지도에 추가하기)
__Request Form:__   
Path: api/myMap/all/{id}  __**여기서 id는 user의 id (Integer)__   
Request Type: GET   
__Request Form:__
```json
{
  "data": {
    "place_id": 100,
    "place_status": "WISH_LIST",
    "diary": ""
  }
}
```
__Response Form(Good Response- 위시 리스트 추가 한 경우 <-- diary 안써짐):__
```json
{
  "result_code": 200,
  "status": "OK",
  "description": "OK",
  "data": {
    "user_id": 18,
    "place_list": [
      {
        "my_map_id": 5,
        "place_id": 100,
        "place_status": "WISH_LIST",
        "title": "Gyeongbokgung Palace (경복궁)"
      }
    ]
  }
}
```
__Response Form(Good Response- 다녀온 곳 추가 한 경우 <-- diary 써짐):__
```json
{
  "result_code": 200,
  "status": "OK",
  "description": "OK",
  "data": {
    "user_id": 18,
    "place_list": [
      {
        "my_map_id": 8,
        "place_id": 101,
        "place_status": "HAVE_BEEN_TO",
        "title": "Lotte World (롯데월드)",
        "diary": "안녕!!!"
      }
    ]
  }
}
```
__Response Form(Bad Response- 추가하려는 장소가 이미 내 지도에 추가되어있음):__
```json
{
  "result_code": 409,
  "status": "CONFLICT",
  "description": "MyPlace already exists"
}
```
__Response Form(Bad Response- 추가하려는 장소를 찾을 수 없음):__
```json
{
  "result_code": 500,
  "status": "INTERNAL_SERVER_ERROR",
  "description": "Cannot Find Place"
}
```
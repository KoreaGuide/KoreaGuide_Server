# This is a document related to the Home services

## Home READ (홈화면에 띄울 정보 가져오기)
__Request Form:__   
Path: api/home/{level} __**여기서 level은 LOW , MID, HIGH 중 하나__   
Request Type: POST   
   
  
__Response Form(Good Response):__
```json
{
    "result_code": 200,
    "status": "OK",
    "description": "OK",
    "data": {
        "id": 7,
        "level": "HIGH",
        "word": "cat",
        "word_image": "http://tong.visitkorea.or.kr/cms/resource/23/2678623_image2_1.jpg",
        "place1_id": 103,
        "place1_title": "Jebudo Island (제부도)",
        "place1_image": "http://tong.visitkorea.or.kr/cms/resource/34/2482734_image2_1.jpg",
        "place2_id": 101,
        "place2_title": "Lotte World (롯데월드)",
        "place2_image": "http://tong.visitkorea.or.kr/cms/resource/77/2553577_image2_1.jpg",
        "place3_id": 102,
        "place3_title": "Korean Folk Village (한국민속촌)",
        "place3_image": "http://tong.visitkorea.or.kr/cms/resource/89/2612489_image2_1.jpg"
    }
}
```

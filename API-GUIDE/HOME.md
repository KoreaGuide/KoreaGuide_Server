# This is a document related to the Home services

## Home READ (홈화면에 띄울 정보 가져오기)
__Request Form:__   
Path: api/home/   
Request Type: GET   
   
  
__Response Form(Good Response):__
```json
{
    "result_code": 200,
    "status": "OK",
    "description": "OK",
    "data": {
        "id": 15,
        "word_id": 4,
        "word": "CAU",
        "word_image": "http://tong.visitkorea.or.kr/cms/resource/23/2678623_image2_1.jpg",
        "word_audio": "https://drive.google.com/file/d/1aRCA4FIMS2n1mPOUEi1WMMSKlxUOXfR9/view?usp=sharing",
        "place1_id": 103,
        "place1_title": "Jebudo Island (제부도)",
        "place1_image": "http://tong.visitkorea.or.kr/cms/resource/34/2482734_image2_1.jpg",
        "place2_id": 102,
        "place2_title": "Korean Folk Village (한국민속촌)",
        "place2_image": "http://tong.visitkorea.or.kr/cms/resource/89/2612489_image2_1.jpg",
        "place3_id": 104,
        "place3_title": "Jeju Gwandeokjeong Hall (관덕정(제주))",
        "place3_image": "http://tong.visitkorea.or.kr/cms/resource/75/2513575_image2_1.jpg"
    }
}
```

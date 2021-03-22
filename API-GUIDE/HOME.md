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
        "id": 16,
        "word_id": 2,
        "word": "cat",
        "word_image": "http://tong.visitkorea.or.kr/cms/resource/23/2678623_image2_1.jpg",
        "word_audio": "https://drive.google.com/file/d/1pHRW50oxel6UbOdlLNo6e-LUfpaAik43/view?usp=sharing",
        "place_list": [
            {
                "id": 101,
                "title": "Lotte World (롯데월드)",
                "first_image": "http://tong.visitkorea.or.kr/cms/resource/77/2553577_image2_1.jpg"
            },
            {
                "id": 102,
                "title": "Korean Folk Village (한국민속촌)",
                "first_image": "http://tong.visitkorea.or.kr/cms/resource/89/2612489_image2_1.jpg"
            },
            {
                "id": 103,
                "title": "Jebudo Island (제부도)",
                "first_image": "http://tong.visitkorea.or.kr/cms/resource/34/2482734_image2_1.jpg"
            }
        ]
    }
}
```

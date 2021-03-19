# This is a document related to the Home services

## REGION LIST READ (모든 지역 리스트 가져오기)
__Request Form:__   
Path: api/place/regionList   
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
        "areaname_eng": "Seoul"
      },
      {
        "id": 2,
        "areacode": 2,
        "areaname_kor": "인천",
        "areaname_eng": "Incheon"
      },
      {
        "id": 3,
        "areacode": 3,
        "areaname_kor": "대전",
        "areaname_eng": "Daejeon"
      },
      {
        "id": 4,
        "areacode": 4,
        "areaname_kor": "대구",
        "areaname_eng": "Daegu"
      },
      {
        "id": 5,
        "areacode": 5,
        "areaname_kor": "광주",
        "areaname_eng": "Gwangju"
      },
      {
        "id": 6,
        "areacode": 6,
        "areaname_kor": "부산",
        "areaname_eng": "Busan"
      },
      {
        "id": 7,
        "areacode": 7,
        "areaname_kor": "울산",
        "areaname_eng": "Ulsan"
      },
      {
        "id": 8,
        "areacode": 8,
        "areaname_kor": "세종",
        "areaname_eng": "Sejong"
      },
      {
        "id": 9,
        "areacode": 31,
        "areaname_kor": "경기도",
        "areaname_eng": "Gyeonggi-do"
      },
      {
        "id": 10,
        "areacode": 32,
        "areaname_kor": "강원도",
        "areaname_eng": "Gangwon-do"
      },
      {
        "id": 11,
        "areacode": 33,
        "areaname_kor": "충청북도",
        "areaname_eng": "Chungcheongbuk-do"
      },
      {
        "id": 12,
        "areacode": 34,
        "areaname_kor": "충청남도",
        "areaname_eng": "Chungcheongnam-do"
      },
      {
        "id": 13,
        "areacode": 35,
        "areaname_kor": "경상북도",
        "areaname_eng": "Gyeongsangbuk-do"
      },
      {
        "id": 14,
        "areacode": 36,
        "areaname_kor": "경상남도",
        "areaname_eng": "Gyeongsangnam-do"
      },
      {
        "id": 15,
        "areacode": 37,
        "areaname_kor": "전라북도",
        "areaname_eng": "Jeollabuk-do"
      },
      {
        "id": 16,
        "areacode": 38,
        "areaname_kor": "전라남도",
        "areaname_eng": "Jeollanam-do"
      },
      {
        "id": 17,
        "areacode": 39,
        "areaname_kor": "제주도",
        "areaname_eng": "Jeju-do"
      }
    ]
  }
}
```

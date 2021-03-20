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

# This is a document related to the PROFILE services

## PROFILE READ (홈화면에 띄울 정보 가져오기)
__Request Form:__   
Path: api/profile/{id}  ** 여기서 id = userId    
Request Type: GET   
   

__Response Form(Good Response):__
```json
{
  "result_code": 200,
  "status": "OK",
  "description": "OK",
  "data": {
    "attendance": 0,
    "week_quiz_result": [
      {
        "date": "2021-05-25",
        "day_of_week": "TUESDAY",
        "total": 0,
        "correct": 0,
        "wrong": 0
      },
      {
        "date": "2021-05-26",
        "day_of_week": "WEDNESDAY",
        "total": 0,
        "correct": 0,
        "wrong": 0
      },
      {
        "date": "2021-05-27",
        "day_of_week": "THURSDAY",
        "total": 0,
        "correct": 0,
        "wrong": 0
      },
      {
        "date": "2021-05-28",
        "day_of_week": "FRIDAY",
        "total": 0,
        "correct": 0,
        "wrong": 0
      },
      {
        "date": "2021-05-29",
        "day_of_week": "SATURDAY",
        "total": 0,
        "correct": 0,
        "wrong": 0
      },
      {
        "date": "2021-05-30",
        "day_of_week": "SUNDAY",
        "total": 0,
        "correct": 0,
        "wrong": 0
      },
      {
        "date": "2021-05-31",
        "day_of_week": "MONDAY",
        "total": 3,
        "correct": 1,
        "wrong": 2
      }
    ]
  }
}
```



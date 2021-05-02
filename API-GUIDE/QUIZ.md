# This is a document related to the QUIZ services

## quiz_type 종류:
```
 - "MATCH": 한국 단어 & 사진 --> 영어 단어 or 영어 설명 
 - "LISTEN": 한국어 발음  --> 영어 단어 or 영어 설명 
 - "SPELLING_E": 영어 단어 / 설명 --> 한국어 단어   
 - "SPELLING_H":영어 단어 / 설명 --> 한국어 단어
```
** 출제할 문제 받아 올때 공통 Response:
**공통 Response
__Response Form(Good Response - 해당 폴더가 비어있음 ):__
```json
{
  "result_code": 204,
  "status": "NO_CONTENT",
  "description": "MyWordFolder is empty"
}
```

__Response Form(Bad Response - 해당 폴더가 없음  ):__
```json
{
  "result_code": 500,
  "status": "INTERNAL_SERVER_ERROR",
  "description": "Cannot Find MyWordFolder"
}
```
__Response Form(Bad Response - 잘못된 QuizType을 보냄):__
```json
{
  "result_code": 500,
  "status": "INTERNAL_SERVER_ERROR",
  "description": "ERROR"
}
```

## Quiz "MATCH" READ (퀴즈타입 "MATCH"에 출제할 문제 및 multiple choice 받아오기)
__Request Form:__   
Path: api/quiz/{id} __**여기서 id 는 user_id(integer)__   
Request Type: GET

__Request Form(Good Response):__
```json
{
  "data": {
    "quiz_type": "MATCH",
    "folder_id": 3
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
        "quiz_type": "MATCH",
        "folder_id": 3,
        "quiz_list": [
            {
                "selected_word": {
                    "id": 17,
                    "word_kor": "궁전",
                    "word_eng": "Palace",
                    "image": "https://www.agoda.com/wp-content/uploads/2019/03/Palace-of-Versailles_Paris_France_marble-courtyard.jpg"
                },
                "word_choice_list": [
                    {
                        "id": 17,
                        "word_kor": "궁전",
                        "word_eng": "Palace",
                        "meaning_kor1": "한 나라의 임금이 사는 집.",
                        "meaning_eng1": "The house where the king of a country lives."
                    },
                    {
                        "id": 362,
                        "word_kor": "바탕",
                        "word_eng": "background",
                        "meaning_kor1": "사물이나 현상을 이루는 근본.",
                        "meaning_eng1": "The basis that constitutes an object or phenomenon."
                    },
                    {
                        "id": 588,
                        "word_kor": "땅",
                        "word_eng": "land",
                        "meaning_kor1": "지구에서 물로 된 부분이 아닌 흙이나 돌로 된 부분.",
                        "meaning_eng1": "The part of the earth made of soil or stone that is not made of water."
                    },
                    {
                        "id": 927,
                        "word_kor": "한반도",
                        "word_eng": "Korean Peninsula",
                        "meaning_kor1": "아시아 대륙의 동북쪽 끝에 있는 반도. 제주도를 포함한 한국 국토의 전역을 포함한다.",
                        "meaning_eng1": "A peninsula at the northeastern tip of the Asian continent. It covers the entire Korean territory, including Jeju Island."
                    }
                ]
            },
            {
                "selected_word": {
                    "id": 18,
                    "word_kor": "지도",
                    "word_eng": "map",
                    "image": "https://www.sphinfo.com/wp-content/uploads/2015/11/Google_maps_korea.jpg"
                },
                "word_choice_list": [
                    {
                        "id": 828,
                        "word_kor": "계속",
                        "word_eng": "continue",
                        "meaning_kor1": "끊이지 않고 이어 나감.",
                        "meaning_eng1": "Continuously continuing."
                    },
                    {
                        "id": 18,
                        "word_kor": "지도",
                        "word_eng": "map",
                        "meaning_kor1": "지구 표면의 전부나 일부를 일정한 비율로 줄여 약속된 기호를 사용하여 평면에 그린 그림.",
                        "meaning_eng1": "An illustration drawn on a plane using the promised symbols, reducing all or part of the Earth&#39;s surface to a certain proportion."
                    },
                    {
                        "id": 295,
                        "word_kor": "숲",
                        "word_eng": "forest",
                        "meaning_kor1": "나무나 풀이 빽빽하게 많이 나 있는 곳.",
                        "meaning_eng1": "A place with a lot of trees and grass."
                    },
                    {
                        "id": 810,
                        "word_kor": "젊음",
                        "word_eng": "youth",
                        "meaning_kor1": "몸과 마음이 젊은 상태.",
                        "meaning_eng1": "Young body and mind."
                    }
                ]
            }
        ]
    }
}
```

## Quiz "LISTEN" READ (퀴즈타입 "LISTEN"에 출제할 문제 및 multiple choice 받아오기)
__Request Form:__   
Path: api/quiz/{id} __**여기서 id 는 user_id(integer)__   
Request Type: GET

__Request Form(Good Response):__
```json
{
  "data": {
    "quiz_type": "LISTEN",
    "folder_id": 3
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
        "quiz_type": "LISTEN",
        "folder_id": 3,
        "quiz_list": [
            {
                "selected_word": {
                    "id": 17,
                    "word_kor": "궁전",
                    "word_eng": "Palace",
                    "image": "https://www.agoda.com/wp-content/uploads/2019/03/Palace-of-Versailles_Paris_France_marble-courtyard.jpg"
                },
                "word_choice_list": [
                    {
                        "id": 162,
                        "word_kor": "도시",
                        "word_eng": "city",
                        "meaning_kor1": "정치, 경제, 문화의 중심이 되고 사람이 많이 사는 지역.",
                        "meaning_eng1": "An area that is the center of politics, economy, and culture and is populated by many people."
                    },
                    {
                        "id": 745,
                        "word_kor": "절반",
                        "word_eng": "half",
                        "meaning_kor1": "하나를 반으로 나눔. 또는 그렇게 나눈 반.",
                        "meaning_eng1": "Dividing one in half. Or the class divided like that."
                    },
                    {
                        "id": 73,
                        "word_kor": "해발",
                        "word_eng": "Above sea level",
                        "meaning_kor1": "바닷물의 표면으로부터 잰 육지나 산의 높이.",
                        "meaning_eng1": "The height of the land or mountain, measured from the surface of the sea water."
                    },
                    {
                        "id": 17,
                        "word_kor": "궁전",
                        "word_eng": "Palace",
                        "meaning_kor1": "한 나라의 임금이 사는 집.",
                        "meaning_eng1": "The house where the king of a country lives."
                    }
                ]
            },
            {
                "selected_word": {
                    "id": 18,
                    "word_kor": "지도",
                    "word_eng": "map",
                    "image": "https://www.sphinfo.com/wp-content/uploads/2015/11/Google_maps_korea.jpg"
                },
                "word_choice_list": [
                    {
                        "id": 341,
                        "word_kor": "금지",
                        "word_eng": "prohibition",
                        "meaning_kor1": "법이나 규칙이나 명령으로 어떤 행위를 하지 못하게 함.",
                        "meaning_eng1": "Preventing you from doing something by law, rule, or order."
                    },
                    {
                        "id": 381,
                        "word_kor": "대왕",
                        "word_eng": "maharaja",
                        "meaning_kor1": "(높이는 말로) 이전의 임금.",
                        "meaning_eng1": "The previous wage (in high words)."
                    },
                    {
                        "id": 18,
                        "word_kor": "지도",
                        "word_eng": "map",
                        "meaning_kor1": "지구 표면의 전부나 일부를 일정한 비율로 줄여 약속된 기호를 사용하여 평면에 그린 그림.",
                        "meaning_eng1": "An illustration drawn on a plane using the promised symbols, reducing all or part of the Earth&#39;s surface to a certain proportion."
                    },
                    {
                        "id": 785,
                        "word_kor": "생태계",
                        "word_eng": "ecosystem",
                        "meaning_kor1": "일정한 지역이나 환경에서 여러 생물들이 서로 적응하고 관계를 맺으며 어우러진 자연의 세계.",
                        "meaning_eng1": "A natural world in which various creatures adapt and form relationships with each other in a certain area or environment."
                    }
                ]
            }
        ]
    }
}
```

## Quiz "SPELLING_E" READ (퀴즈타입 "SPELLING_E"에 출제할 문제 및 multiple choice 받아오기)
__Request Form:__   
Path: api/quiz/{id} __**여기서 id 는 user_id(integer)__   
Request Type: GET

__Request Form(Good Response):__
```json
{
  "data": {
    "quiz_type": "SPELLING_E",
    "folder_id": 3
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
        "quiz_type": "SPELLING_E",
        "folder_id": 3,
        "quiz_list": [
            {
                "selected_word": {
                    "id": 18,
                    "word_kor": "지도",
                    "word_eng": "map",
                    "image": "https://www.sphinfo.com/wp-content/uploads/2015/11/Google_maps_korea.jpg"
                },
                "alphabet_choice_list": [
                    "잡",
                    "전",
                    "도",
                    "닉",
                    "룰",
                    "벽",
                    "젖",
                    "오",
                    "릿",
                    "넘",
                    "지",
                    "팥"
                ]
            },
            {
                "selected_word": {
                    "id": 17,
                    "word_kor": "궁전",
                    "word_eng": "Palace",
                    "image": "https://www.agoda.com/wp-content/uploads/2019/03/Palace-of-Versailles_Paris_France_marble-courtyard.jpg"
                },
                "alphabet_choice_list": [
                    "찹",
                    "전",
                    "츠",
                    "콕",
                    "훅",
                    "헌",
                    "번",
                    "곳",
                    "는",
                    "궁",
                    "찰",
                    "홉"
                ]
            }
        ]
    }
}
```

## Quiz "SPELLING_H" READ (퀴즈타입 "SPELLING_H"에 출제할 문제 및 multiple choice 받아오기)
__Request Form:__   
Path: api/quiz/{id} __**여기서 id 는 user_id(integer)__   
Request Type: GET

__Request Form(Good Response):__
```json
{
  "data": {
    "quiz_type": "SPELLING_H",
    "folder_id": 3
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
    "quiz_type": "SPELLING_H",
    "folder_id": 3,
    "quiz_list": [
      {
        "selected_word": {
          "id": 18,
          "word_kor": "지도",
          "word_eng": "map",
          "image": "https://www.sphinfo.com/wp-content/uploads/2015/11/Google_maps_korea.jpg"
        }
      },
      {
        "selected_word": {
          "id": 17,
          "word_kor": "궁전",
          "word_eng": "Palace",
          "image": "https://www.agoda.com/wp-content/uploads/2019/03/Palace-of-Versailles_Paris_France_marble-courtyard.jpg"
        }
      }
    ]
  }
}
```

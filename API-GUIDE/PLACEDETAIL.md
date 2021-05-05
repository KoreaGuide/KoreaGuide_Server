# This is a document related to the Place Detail services

## place_status 종류: 
```
   - "NO_STATUS" : 내 지도에 추가되지 않은 상태 
   - "WISH_LIST" : 내 지도, 위시리스트에 추가됨 
   - "HAVE_BEEN_TO": 내 지도, 이미 가본 곳에 추가됨
```

## Place Detail ALL READ (특정 관광지 정보 조회 -- 영어 & 한국어)
__Request Form:__   
Path: api/place/detail/{userId}/{id} __**여기서 id 는 place_id(integer)__   
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
        "id": 1,
        "title": "Gyeongbokgung Palace (경복궁)",
        "area_code": 1,
        "address": "161, Sajik-ro, Jongno-gu, Seoul",
        "first_image": "http://tong.visitkorea.or.kr/cms/resource/23/2678623_image2_1.jpg",
        "first_image2": "http://tong.visitkorea.or.kr/cms/resource/23/2678623_image3_1.jpg",
        "map_x": "126.9769930325",
        "map_y": "37.5788222356",
        "overview_english": "Built in 1395, Gyeongbokgung Palace is also commonly referred to as the Northern Palace because its location is furthest north when compared to the neighboring palaces of Changdeokgung (Eastern Palace) and Gyeonghuigung (Western Palace) Palace. Gyeongbokgung Palace is arguably the most beautiful, and remains the largest of all five palaces. \n\n\nThe premises were once destroyed by fire during the Imjin War (1592-1598). However, all of the palace buildings were later restored under the leadership of Heungseondaewongun during the reign of King Gojong (1852-1919). \n\n \n\n Remarkably, the most representative edifices of the Joseon dynasty, Gyeonghoeru Pavilion and the pond around Hyangwonjeong Pavillion have remained relatively intact. The raised dias and stone markers of Geunjeongjeon showcase the representative art style of their time.\n\n \n\n The National Palace Museum of Korea is located south of Heungnyemun Gate, and the National Folk Museum is located on the eastern side of Hyangwonjeong Pavillion.",
        "overview_korean": "1395년에 지어진 경복궁은 인근 창덕궁 (동궁)과 경희궁 (서궁)과 비교할 때 가장 북쪽에 위치하기 때문에 북궁이라고도 불린다. 경복궁은 틀림없이 가장 아름답고 5 개의 궁전 중 가장 큰 궁전이다. 임진 전쟁 (1592 ~ 1598) 중 화재로 건물이 소실 된 적이 있다. 그러나 이후 고종 (1852 ~ 1919) 시대에 흥선 대원군의 지도 아래 모든 궁궐 건물이 복원되었다. 특히 조선 시대를 대표하는 건축물인 경회루, 향원정 주변의 연못은 비교적 온전하게 남아있다. 근정전의 월대와 조각상은 당대를 대표하는 예술 스타일을 보여준다. 국립 고궁 박물관은 흥례문 남쪽에, 국립 민속 박물관은 향원정 동쪽에 있다.",
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
Path: api/place/detail/eng/{userId}/{id} __**여기서 id 는 place_id(integer)__   
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
        "title": "Yongpyong Resort (용평리조트)",
        "area_code": 32,
        "address": "715, Olympic-ro, Pyeongchang-gun, Gangwon-do",
        "first_image": "http://tong.visitkorea.or.kr/cms/resource/17/680817_image2_1.jpg",
        "first_image2": "http://tong.visitkorea.or.kr/cms/resource/17/680817_image3_1.jpg",
        "map_x": "128.6855250323",
        "map_y": "37.6484376398",
        "overview_english": "Located in Pyeongchang-gun, Gangwon-do, Yongpyong Resort is a ski resort known to have a relatively long ski season in Korea. Nestled at the foot of Balwangsan Mountain (alt. 1,458 meters), Yongpyong Resort is equipped with the perfect environment for a great ski vacation with an annual average snowfall of 250 centimeters. It was Korea’s first ski resort, opened in 1975 to develop Korean winter sports and resort culture. Located approximately 215 kilometers away from Seoul, it takes around 2 hours to reach by car. Noteworthy attractions nearby include Odaesan Mountain, Gyeongpodae Pavilion, Sogeumgang River, and Daegwallyeong Sheep Ranch.",
        "category1": "A02",
        "category2": "A0202",
        "category3": "A02020200"
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
Path: api/place/detail/kor/{userId}/{id} __**여기서 id 는 place_id(integer)__   
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
        "title": "Yongpyong Resort (용평리조트)",
        "area_code": 32,
        "address": "715, Olympic-ro, Pyeongchang-gun, Gangwon-do",
        "first_image": "http://tong.visitkorea.or.kr/cms/resource/17/680817_image2_1.jpg",
        "first_image2": "http://tong.visitkorea.or.kr/cms/resource/17/680817_image3_1.jpg",
        "map_x": "128.6855250323",
        "map_y": "37.6484376398",
        "overview_korean": "강원도 평창군에 위치한 용평 리조트는 한국에서 스키 시즌이 비교적 긴 것으로 알려진 스키장이다. 발왕산 기슭 (1,458m)에 자리 잡은 용평 리조트는 연평균 강설량 250cm로 멋진 스키 휴가를 보내기에 완벽한 환경을 갖추고 있다. 한국의 겨울 스포츠와 리조트 문화를 발전시키기 위해 1975년에 개장한 국내 최초의 스키장입니다. 서울에서 약 215km 떨어져있어 차로 약 2시간이 소요된다. 인근 명소로는 오대산, 경포대, 소금강, 대관령 양 목장 등이 있다.",
        "category1": "A02",
        "category2": "A0202",
        "category3": "A02020200"
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

## Place Related Words(PAGINATION)  (특정 관광지와 관련된 단어 페이지네이션)
__Request Form:__   
Path: api/place/word/{userId}/{id}?page={pageNumber} __**여기서 id 는 place_id(integer)__  __**pageNumber = 1부터 시작!__    
__Path 예시:__
```
http://localhost:8080/api/place/word/18/100?page=1
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
            "total_pages": 21,
            "total_elements": 21,
            "current_page": 1,
            "current_elements": 1
        },
        "word_list": [
            {
                "word_status": "NO_STATUS",
                "word_id": 19,
                "word_kor": "위치",
                "word_eng": "location",
                "meaning_kor1": "일정한 곳에 자리를 차지함. 또는 그 자리.",
                "meaning_kor2": "사회적으로 담당하고 있는 지위나 역할.",
                "meaning_eng1": "Takes place in a certain place. Or the spot.",
                "meaning_eng2": "The position or role you are playing socially.",
                "pronunciation_eng": "Wi Chi",
                "pronunciation_kor": "위치",
                "word_image": "https://post-phinf.pstatic.net/MjAxOTA0MDFfMTg1/MDAxNTU0MDkwOTg3MTQw.c8nm0cxui3mQaMKas2RUN1iCagPd1mMKCTt8nJosOckg.4LqZ4Qz9RIhu8MgVul5-sPzE1RISxMEuJfPL-EooTJog.JPEG/GettyImages-454264109.jpg?type=w1200",
                "word_audio": ""
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
## Place Related Words(NO PAGINATION)  (특정 관광지와 관련된 단어 리스트)
__Request Form:__   
Path: api/place/wordList/{userId}/{id} __**여기서 id 는 place_id(integer)__   
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
        "word_list": [
            {
                "word_status": "NO_STATUS",
                "word_id": 19,
                "word_kor": "위치",
                "word_eng": "location",
                "meaning_kor1": "일정한 곳에 자리를 차지함. 또는 그 자리.",
                "meaning_kor2": "사회적으로 담당하고 있는 지위나 역할.",
                "meaning_eng1": "Takes place in a certain place. Or the spot.",
                "meaning_eng2": "The position or role you are playing socially.",
                "pronunciation_eng": "Wi Chi",
                "pronunciation_kor": "위치",
                "word_image": "https://post-phinf.pstatic.net/MjAxOTA0MDFfMTg1/MDAxNTU0MDkwOTg3MTQw.c8nm0cxui3mQaMKas2RUN1iCagPd1mMKCTt8nJosOckg.4LqZ4Qz9RIhu8MgVul5-sPzE1RISxMEuJfPL-EooTJog.JPEG/GettyImages-454264109.jpg?type=w1200",
                "word_audio": ""
            },
            {
                "word_status": "NO_STATUS",
                "word_id": 24,
                "word_kor": "인근",
                "word_eng": "vicinity",
                "meaning_kor1": "가까운 곳.",
                "meaning_kor2": "",
                "meaning_eng1": "Near you.",
                "meaning_eng2": "",
                "pronunciation_eng": "In Geun",
                "pronunciation_kor": "인근",
                "word_image": "https://www.google.com/url?sa=i&url=https%3A%2F%2Fwww.gettyimagesbank.com%2Fview%2F%25EC%25A7%2580%25EC%25B6%259C-%25EC%25BB%25A8%25EC%2585%2589-%25EC%2586%258C%25EB%25B9%2584-%25EB%259D%25BC%25EC%259D%25B4%25ED%2594%2584%25EC%258A%25A4%25ED%2583%2580%25EC%259D%25BC-%25EC%25A7%2580%25EC%2584%25B1-%25EC%25BB%25A8%25EC%2585%2589-%25EA%25B1%25B7%25EA%25B8%25B0-%25EC%25A0%2588%25EC%2595%25BD-%25EC%25BB%25A8%25EC%2585%2589-%25EB%25B2%2584%25EC%258A%25A4%25EC%25A0%2595%25EB%25A5%2598%25EC%259E%25A5-%25EC%259D%25B8%25EA%25B3%25B5%25EA%25B5%25AC%25EC%25A1%25B0%25EB%25AC%25BC%2Fjv11110650&psig=AOvVaw0qBOsmXfvPTHRi3rCWofFn&ust=1617132545865000&source=images&cd=vfe&ved=0CAIQjRxqFwoTCOClgrSe1u8CFQAAAAAdAAAAABAJ",
                "word_audio": ""
            },
            {
                "word_status": "NO_STATUS",
                "word_id": 51,
                "word_kor": "등",
                "word_eng": "Etc",
                "meaning_kor1": "사람이나 동물의 몸에서 가슴과 배의 반대쪽 부분.",
                "meaning_kor2": "물체의 위쪽이나 바깥쪽에 볼록하게 나온 부분.",
                "meaning_eng1": "The opposite part of the chest and stomach in a human or animal body.",
                "meaning_eng2": "A convex part on the top or outside of an object.",
                "pronunciation_eng": "deung",
                "pronunciation_kor": "등",
                "word_image": "https://src.hidoc.co.kr/image/lib/2019/10/29/1572330379107_0.jpg",
                "word_audio": ""
            },
            {
                "word_status": "NO_STATUS",
                "word_id": 58,
                "word_kor": "한국",
                "word_eng": "Korea",
                "meaning_kor1": "아시아 대륙의 동쪽에 있는 나라. 한반도와 그 부속 섬들로 이루어져 있으며, 대한민국이라고도 부른다. 1950년에 일어난 육이오 전쟁 이후 휴전선을 사이에 두고 국토가 둘로 나뉘었다. 언어는 한국어이고, 수도는 서울이다.",
                "meaning_kor2": "",
                "meaning_eng1": "A country to the east of the Asian continent. It consists of the Korean peninsula and its attached islands, and is also called Korea. After the Yuk-Io War in 1950, the country was divided into two with a truce between them. The language is Korean, and the capital is Seoul.",
                "meaning_eng2": "",
                "pronunciation_eng": "Han Guk",
                "pronunciation_kor": "한ː국",
                "word_image": "https://upload.wikimedia.org/wikipedia/commons/thumb/d/d0/Korean_Peninsula.jpg/250px-Korean_Peninsula.jpg",
                "word_audio": ""
            },
            {
                "word_status": "NO_STATUS",
                "word_id": 62,
                "word_kor": "완벽",
                "word_eng": "perfect",
                "meaning_kor1": "흠이나 부족함이 없이 완전함.",
                "meaning_kor2": "",
                "meaning_eng1": "Complete without flaws or shortcomings.",
                "meaning_eng2": "",
                "pronunciation_eng": "wanbyeok",
                "pronunciation_kor": "완벽",
                "word_image": "https://newsimg.sedaily.com/2017/12/20/1OOXHKVPOY_1.jpg",
                "word_audio": ""
            },
            {
                "word_status": "NO_STATUS",
                "word_id": 76,
                "word_kor": "최초",
                "word_eng": "first",
                "meaning_kor1": "맨 처음.",
                "meaning_kor2": "",
                "meaning_eng1": "The very first.",
                "meaning_eng2": "",
                "pronunciation_eng": "Choi Cho",
                "pronunciation_kor": "최ː초/췌ː초",
                "word_image": "https://images.joins.com/ui_mobile/native_ad/soongsil/v_soongsil_img01.jpg",
                "word_audio": ""
            },
            {
                "word_status": "NO_STATUS",
                "word_id": 77,
                "word_kor": "국내",
                "word_eng": "domestic",
                "meaning_kor1": "나라의 안.",
                "meaning_kor2": "",
                "meaning_eng1": "Inside the country.",
                "meaning_eng2": "",
                "pronunciation_eng": "Kook Nae",
                "pronunciation_kor": "궁내",
                "word_image": "https://t1.daumcdn.net/cfile/tistory/99E8E54A5C5E75A917",
                "word_audio": ""
            },
            {
                "word_status": "NO_STATUS",
                "word_id": 84,
                "word_kor": "서울",
                "word_eng": "Seoul",
                "meaning_kor1": "한 나라의 중앙 정부가 있는 곳.",
                "meaning_kor2": "한반도 중앙에 있는 특별시. 한국의 수도이자 정치, 경제, 산업, 사회, 문화, 교통의 중심지이다. 북한산, 관악산 등의 산에 둘러싸여 있고 가운데로는 한강이 흐른다.",
                "meaning_eng1": "Where the central government of a country is located.",
                "meaning_eng2": "A special city in the center of the Korean Peninsula. It is the capital of Korea and the center of politics, economy, industry, society, culture, and transportation. It is surrounded by mountains such as Bukhansan and Gwanaksan, and the Han River flows through the middle.",
                "pronunciation_eng": "Seo Ul",
                "pronunciation_kor": "서울",
                "word_image": "https://upload.wikimedia.org/wikipedia/commons/0/0a/Seoul_montage.PNG",
                "word_audio": ""
            },
            {
                "word_status": "NO_STATUS",
                "word_id": 86,
                "word_kor": "개장",
                "word_eng": "Open",
                "meaning_kor1": "어떤 장소를 이용할 수 있도록 엶.",
                "meaning_kor2": "",
                "meaning_eng1": "Open so that some places can be used.",
                "meaning_eng2": "",
                "pronunciation_eng": "Gae Jang",
                "pronunciation_kor": "개장",
                "word_image": "https://lh3.googleusercontent.com/proxy/HhURc7D9ERLAqZltBkK--8kHCndHO1oivyeW2_qujKUh65TC6aaCusUFjn9XZ27Kco32zSRJA2aJqE74dYaR4CTHGf--G76tJZoJuwkP_5zvKeyy_rmRuJainyG1_9aCR9Vb59z7RCMMqFzrfAuxFz-3oybbFjgfu3GPvLbDOIMfdQ-YUxVI7HN1FGRk1vBeyl6dmYQ_5E9NynvogHuHtJfvxH8LXCsKBZ1XXHml311XXgVN6A87J_uptRZSEm044TprtK-bv4ucbMnt0nq_ANn6LUv-cinG3mxRoTY1hWnJqxONDl2-l_mz1ui_u67TfrOcqj1c_a6NXXtxw0c5kT4WRXO45SoiyTQcqB9oN2FRS7lI_S3v5Fa7Gf323c1SVciHZgKg3duK37QevojuuhGn8rGKwVWdQ22jGMDOqQxQWgIJ-GKbNGEg5yppX3wl0S6xCkoFaMqKE2wreBqh0P9IPhLe1ncMqVurWJZ2ICpEyM6MmgENXDKIXFT4dqyftCIrBH7bpGRgFbgCdCtZkrDOdVh7ywG1Eo675l0LpVvzDrkeJYjCI8ltjPqVhVDNzUBPgfOFKxktTLRHpXhs3OQ_s1rwVJxBSI2dbKi2",
                "word_audio": ""
            },
            {
                "word_status": "NO_STATUS",
                "word_id": 89,
                "word_kor": "명소",
                "word_eng": "sights",
                "meaning_kor1": "아름다운 경치나 유적, 특산물 등으로 유명한 장소.",
                "meaning_kor2": "",
                "meaning_eng1": "A place famous for its beautiful scenery, ruins, and specialties.",
                "meaning_eng2": "",
                "pronunciation_eng": "Myoung So",
                "pronunciation_kor": "명소",
                "word_image": "https://www.agoda.com/wp-content/uploads/2019/03/Seoul-attractions-Changdeokgung.jpg",
                "word_audio": ""
            },
            {
                "word_status": "NO_STATUS",
                "word_id": 106,
                "word_kor": "문화",
                "word_eng": "culture",
                "meaning_kor1": "사회의 공동체가 일정한 목적 또는 생활 이상을 실현하기 위하여 만들고, 익히고, 공유하고, 전달하는 물질적, 정신적 활동.",
                "meaning_kor2": "",
                "meaning_eng1": "Material and mental activities created, learned, shared, and communicated by a community of society in order to realize a certain purpose or ideal of life.",
                "meaning_eng2": "",
                "pronunciation_eng": "Moon Hwa",
                "pronunciation_kor": "문화",
                "word_image": "https://upload.wikimedia.org/wikipedia/commons/thumb/0/0b/Korean_dance-Talchum-Mask_Dancer.jpg/250px-Korean_dance-Talchum-Mask_Dancer.jpg",
                "word_audio": ""
            },
            {
                "word_status": "NO_STATUS",
                "word_id": 298,
                "word_kor": "환경",
                "word_eng": "Environment",
                "meaning_kor1": "생물이 살아가는 데 영향을 주는 자연 상태나 조건.",
                "meaning_kor2": "인간에게 영향을 주는 주위의 조건이나 상황.",
                "meaning_eng1": "A natural state or condition that affects the way living things live.",
                "meaning_eng2": "A surrounding condition or situation that affects humans.",
                "pronunciation_eng": "Hwan Kyung",
                "pronunciation_kor": "환경",
                "word_image": "https://img.kr.news.samsung.com/kr/wp-content/uploads/2015/06/%EC%8A%A4%ED%8E%98%EC%85%9C%EB%A6%AC%ED%8F%AC%ED%8A%B8%ED%99%98%EA%B2%BD%EC%9D%B8%EC%A6%9D1.jpg",
                "word_audio": ""
            },
            {
                "word_status": "NO_STATUS",
                "word_id": 497,
                "word_kor": "발전",
                "word_eng": "Development",
                "meaning_kor1": "더 좋은 상태나 더 높은 단계로 나아감.",
                "meaning_kor2": "일이 어떤 방향으로 전개됨.",
                "meaning_eng1": "Going to a better state or a higher level.",
                "meaning_eng2": "Things unfold in some direction.",
                "pronunciation_eng": "Baljeon",
                "pronunciation_kor": "발쩐",
                "word_image": "https://img1.daumcdn.net/thumb/R800x0/?scode=mtistory2&fname=https%3A%2F%2Ft1.daumcdn.net%2Fcfile%2Ftistory%2F997A3A395BCE98C614",
                "word_audio": ""
            },
            {
                "word_status": "NO_STATUS",
                "word_id": 591,
                "word_kor": "시간",
                "word_eng": "time",
                "meaning_kor1": "어떤 때에서 다른 때까지의 동안.",
                "meaning_kor2": "어떤 일을 하도록 정해진 때. 또는 하루 중의 어느 한 때.",
                "meaning_eng1": "While from one time to another.",
                "meaning_eng2": "When it is set to do something. Or at any time of the day.",
                "pronunciation_eng": "Si Gan",
                "pronunciation_kor": "시간",
                "word_image": "https://live.staticflickr.com/65535/49279799728_18f5395e2c.jpg",
                "word_audio": ""
            },
            {
                "word_status": "NO_STATUS",
                "word_id": 705,
                "word_kor": "기슭",
                "word_eng": "Shore",
                "meaning_kor1": "비탈진 산이나 언덕의 아랫부분.",
                "meaning_kor2": "바다나 강, 호수의 물과 닿아 있는 땅의 부분.",
                "meaning_eng1": "The lower part of a sloped mountain or hill.",
                "meaning_eng2": "The part of the land that is in contact with the water of the sea, river or lake.",
                "pronunciation_eng": "Gi Seuk",
                "pronunciation_kor": "기슥",
                "word_image": "https://thx.sfo2.cdn.digitaloceanspaces.com/wr/coverimages/m_11/%EA%B8%B0%EC%8A%AD_11.jpg",
                "word_audio": ""
            },
            {
                "word_status": "NO_STATUS",
                "word_id": 800,
                "word_kor": "겨울",
                "word_eng": "winter",
                "meaning_kor1": "네 계절 중의 하나로 가을과 봄 사이의 추운 계절.",
                "meaning_kor2": "",
                "meaning_eng1": "One of the four seasons is the cold season between autumn and spring.",
                "meaning_eng2": "",
                "pronunciation_eng": "Gyeoul",
                "pronunciation_kor": "겨울",
                "word_image": "https://image.freepik.com/free-photo/winter-landscape_176873-5999.jpg",
                "word_audio": ""
            },
            {
                "word_status": "NO_STATUS",
                "word_id": 804,
                "word_kor": "강원도",
                "word_eng": "Gangwon-do",
                "meaning_kor1": "한국의 행정 구역 단위인 도의 하나. 중동부에 위치한 산악 지대로 동쪽으로 동해를 접하고 있으며 감자, 옥수수, 오징어, 명태 등이 많이 난다. 주요 도시로는 평창, 춘천, 강릉 등이 있다.",
                "meaning_kor2": "",
                "meaning_eng1": "One of the provinces, the unit of administrative divisions in Korea. It is a mountainous region located in the Middle East and faces the East Sea to the east, and many potatoes, corn, squid, and pollack are produced. Major cities include Pyeongchang, Chuncheon, and Gangneung.",
                "meaning_eng2": "",
                "pronunciation_eng": "Kang Wondo",
                "pronunciation_kor": "강원도",
                "word_image": "https://upload.wikimedia.org/wikipedia/commons/thumb/3/34/Gangwon-map_ko.png/500px-Gangwon-map_ko.png",
                "word_audio": ""
            },
            {
                "word_status": "NO_STATUS",
                "word_id": 1139,
                "word_kor": "용",
                "word_eng": "for",
                "meaning_kor1": "몸은 거대한 뱀과 비슷하고, 머리에 뿔이 있으며 네 발에 날카로운 발톱이 있는 상상 속의 동물.",
                "meaning_kor2": "",
                "meaning_eng1": "An imaginary animal whose body resembles a giant snake, has horns on its head and sharp claws on all fours.",
                "meaning_eng2": "",
                "pronunciation_eng": "Yong ",
                "pronunciation_kor": "용",
                "word_image": "https://lh3.googleusercontent.com/proxy/jjwHkhdyATBcLepA3OIRp36ssUdY1oqzmJm4Xl4UuIQzFGGsnAPOPkdhr_fTKyDrYz4RhiN29eYihw-rrcmTYkakegL5mqxpCWc2hGdNTi7h65HuuUAZxvBE5nVb_cwljet-0pKCw1Lt50VWqCsZwQbLq2f2goplYPMgr1V9ayc-ywcbBn6eSeWpfRVm8hLRJuz-uOztnWY2t9WtM7oXbqgHU953BrC2wT-ebd5cfbQTFbnwoSyg5VZtS52MqekNSkyQhQcuDWvYP7cvQDIFf-nv_ug",
                "word_audio": ""
            },
            {
                "word_status": "NO_STATUS",
                "word_id": 1288,
                "word_kor": "평창",
                "word_eng": "Pyeongchang",
                "meaning_kor1": "",
                "meaning_kor2": "",
                "meaning_eng1": "",
                "meaning_eng2": "",
                "pronunciation_eng": "Pyeong Chang",
                "pronunciation_kor": "",
                "word_image": "https://upload.wikimedia.org/wikipedia/commons/thumb/8/82/Alpensia_20170202_04_%2832506763362%29.jpg/1200px-Alpensia_20170202_04_%2832506763362%29.jpg",
                "word_audio": ""
            },
            {
                "word_status": "NO_STATUS",
                "word_id": 1294,
                "word_kor": "목장",
                "word_eng": "pasture",
                "meaning_kor1": "우리와 풀밭 등을 갖추어 소나 말이나 양 등을 놓아기르는 곳.",
                "meaning_kor2": "",
                "meaning_eng1": "A place where cattle, horses, sheep, etc. are kept with cages and meadows.",
                "meaning_eng2": "",
                "pronunciation_eng": "Mok Jang",
                "pronunciation_kor": "목짱",
                "word_image": "https://upload.wikimedia.org/wikipedia/commons/f/fd/%EB%8C%80%EA%B4%80%EB%A0%B95.jpg",
                "word_audio": ""
            },
            {
                "word_status": "NO_STATUS",
                "word_id": 1297,
                "word_kor": "휴가",
                "word_eng": "vacation",
                "meaning_kor1": "직장이나 군대 등의 단체에 속한 사람이 일정한 기간 동안 일터를 벗어나서 쉬는 일. 또는 그런 기간.",
                "meaning_kor2": "",
                "meaning_eng1": "A person who belongs to an organization such as a job or the military who leaves the workplace for a certain period of time and takes a break. Or such a period.",
                "meaning_eng2": "",
                "pronunciation_eng": "Hyuga",
                "pronunciation_kor": "휴가",
                "word_image": "https://lh3.googleusercontent.com/proxy/FzJDIoWogOp2CrYRRkQf9n49MRpsu7-3SvzNuOtrhFOnyyfS468ncbTgbLdllu5sXEoasgLfjdAzjyDsTTZg-P7Cm71qD-8eppTNqmkgxbc0uNqozq6a",
                "word_audio": ""
            }
        ]
    }
}
```

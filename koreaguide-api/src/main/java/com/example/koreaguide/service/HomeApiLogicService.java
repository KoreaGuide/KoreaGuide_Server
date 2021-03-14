package com.example.koreaguide.service;

import com.example.koreaguide.model.entity.Home;
import com.example.koreaguide.model.entity.Place;
import com.example.koreaguide.model.entity.TodaysPlace;
import com.example.koreaguide.model.entity.User;
import com.example.koreaguide.model.entity.Word;
import com.example.koreaguide.model.enumclass.UserLevel;
import com.example.koreaguide.model.exception.KoreaGuideError;
import com.example.koreaguide.model.exception.KoreaGuideException;
import com.example.koreaguide.model.network.response.HomeApiResponse;
import com.example.koreaguide.repository.HomeRepository;
import com.example.koreaguide.repository.PlaceRepository;
import com.example.koreaguide.repository.TodaysPlaceRepository;
import com.example.koreaguide.repository.UserRepository;
import com.example.koreaguide.repository.WordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

//TODO: 주의!!! -- DB에서 todays_place 삭제하면 해당 id갖는 home 도 삭제해줘야함!!!

@Service
public class HomeApiLogicService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private WordRepository wordRepository;

    @Autowired
    private PlaceRepository placeRepository;

    @Autowired
    private HomeRepository homeRepository;

    @Autowired
    private TodaysPlaceRepository todaysPlaceRepository;

    public HomeApiResponse getHomeInfo(UserLevel level) {
        Home homeOptional = homeRepository.findByLevelAndCreatedAt(level, LocalDate.now());
        if(homeOptional==null){
            return response(createHome(level));
        }else{
            // 이미 level 과 createdAt을 갖는 객체가 있음 --> 그냥 반환!
            return response(homeOptional);
        }
    }

    public Home createHome(UserLevel level){
        Integer wordCount = Math.toIntExact(wordRepository.count());
        Word word = wordRepository.getOne(getRandomIndex(wordCount));
        System.out.println("word is!!! "+word.getWordEng());

        TodaysPlace todaysPlaceOptional =todaysPlaceRepository.findByCreatedAt(LocalDate.now());

        if(todaysPlaceOptional==null){
            return createHomeAndPlace(level,word);
        }
        else{
            //이미 todayPlace객체가 있음
            Home home = Home.builder()
                    .createdAt(LocalDate.now())
                    .level(level)
                    .word(word)
                    .todaysPlace(todaysPlaceOptional)
                    .build();
            return homeRepository.save(home);
        }

    }

    private Home createHomeAndPlace(UserLevel level,Word word){
        System.out.println("home and place create!!!!");
        Integer homeCount = Math.toIntExact(homeRepository.count());
        Integer placeCount = Math.toIntExact(placeRepository.count());
        TodaysPlace todaysPlace= createTodaysPlace(placeCount,homeCount);
        Home home = Home.builder()
                .todaysPlace(todaysPlace)
                .word(word)
                .level(level)
                .createdAt(LocalDate.now())
                .build();
        Home newHome = homeRepository.save(home);
        todaysPlace.setHome(newHome);
        return newHome;
    }


    public TodaysPlace createTodaysPlace(Integer count,Integer homeCount){
        //TODO: minNum 어떻게 할건지!!
        int [] placeIndecies = getRandomIndex(100,count);
        Place place1 = placeRepository.getOne(placeIndecies[0]);
        Place place2 = placeRepository.getOne(placeIndecies[1]);
        Place place3 = placeRepository.getOne(placeIndecies[2]);
        System.out.println("Place1: "+place1.getTitle());
        System.out.println("Place2: "+place2.getTitle());
        System.out.println("Place3: "+place3.getTitle());
        TodaysPlace todaysPlace = TodaysPlace.builder()
                .place1Id(place1.getId())
                .place2Id(place2.getId())
                .place3Id(place3.getId())
                .createdAt(LocalDate.now())
                .home(null)
                .build();
        TodaysPlace newTodaysPlace = todaysPlaceRepository.save(todaysPlace);
        return newTodaysPlace;
    }

    private Integer getRandomIndex(Integer count){
        int min = 1;
        int max = count;
        int random = (int) ((Math.random() * (max - min)) + min);
        System.out.println(random);
        return random;
    }
    private int[] getRandomIndex(Integer minNum,Integer count){
        int indices[] = new int[count];
        int min = minNum;
        int max = minNum+count;
        for(int i=0;i<count;i++){
            int selectedNumber=(int) ((Math.random() * (max - min)) + min);
            while(placeRepository.findById(selectedNumber)==null){
                selectedNumber=(int) ((Math.random() * (max - min)) + min);
            }
            indices[i]=selectedNumber;

            for(int j=0;j<i;j++){
                if(indices[i]==indices[j]){
                    i--;
                }
            }
        }
        return indices;
    }

    private HomeApiResponse response(Home home){
        Place place1=placeRepository.getOne(home.getTodaysPlace().getPlace1Id());
        Place place2=placeRepository.getOne(home.getTodaysPlace().getPlace2Id());
        Place place3=placeRepository.getOne(home.getTodaysPlace().getPlace3Id());
        if(place1 ==null || place2 ==null || place3 ==null){
            new KoreaGuideException(KoreaGuideError.ENTITY_NOT_FOUND,"place");
        }
        HomeApiResponse homeApiResponse = HomeApiResponse.builder()
                .id(home.getId())
                .level(home.getLevel())
                .word(home.getWord().getWordEng())
                .wordImage(home.getWord().getImage())
                .place1_id(home.getTodaysPlace().getPlace1Id())
                .place1_title(place1.getTitle())
                .place1_image(place1.getFirstImage())
                .place2_id(home.getTodaysPlace().getPlace2Id())
                .place2_title(place2.getTitle())
                .place2_image(place2.getFirstImage())
                .place3_id(home.getTodaysPlace().getPlace3Id())
                .place3_title(place3.getTitle())
                .place3_image(place3.getFirstImage())
                .build();
        return homeApiResponse;
    }

}

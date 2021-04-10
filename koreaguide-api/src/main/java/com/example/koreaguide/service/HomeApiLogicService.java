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
import com.example.koreaguide.model.network.response.MyWordFolderApiResponse;
import com.example.koreaguide.model.network.response.PlaceApiResponse;
import com.example.koreaguide.repository.HomeRepository;
import com.example.koreaguide.repository.PlaceRepository;
import com.example.koreaguide.repository.TodaysPlaceRepository;
import com.example.koreaguide.repository.UserRepository;
import com.example.koreaguide.repository.WordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
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

    public HomeApiResponse getHomeInfo() {
        Home homeOptional = homeRepository.findByCreatedAt(LocalDate.now());
        if(homeOptional==null){
            return response(createHome());
        }else{
            // 이미 level 과 createdAt을 갖는 객체가 있음 --> 그냥 반환!
            return response(homeOptional);
        }
    }

    public Home createHome(){
//        System.out.println("LEVEL: "+level);
        Integer wordCount = Math.toIntExact(wordRepository.count())+1;
        System.out.println("WORD COUNT: "+wordCount.toString());
        Word word = wordRepository.getOne(getRandomIndex(wordCount));
        System.out.println("WORD is: "+word);
//        while(!word.getLevel().equals(level.name())){
//            System.out.println("word level issss : "+word.getLevel());
//            System.out.println("level is :"+level);
//            word = wordRepository.getOne(getRandomIndex(wordCount));
//        }
//        System.out.println("word is!!! "+word.getWordEng());

        TodaysPlace todaysPlaceOptional =todaysPlaceRepository.findByCreatedAt(LocalDate.now());
        if(todaysPlaceOptional==null){
            return createHomeAndPlace(word);
        }
        else{
            //이미 todayPlace객체가 있음
            Home home = Home.builder()
                    .createdAt(LocalDate.now())
                    .word(word)
                    .todaysPlace(todaysPlaceOptional)
                    .build();
            return homeRepository.save(home);
        }

    }

    private Home createHomeAndPlace(Word word){
        System.out.println("home and place create!!!!");
        Integer homeCount = Math.toIntExact(homeRepository.count());
        Integer placeCount = Math.toIntExact(placeRepository.count());
        TodaysPlace todaysPlace= createTodaysPlace(placeCount,homeCount);
        Home home = Home.builder()
                .todaysPlace(todaysPlace)
                .word(word)
                .createdAt(LocalDate.now())
                .build();
        Home newHome = homeRepository.save(home);
        todaysPlace.setHome(newHome);
        return newHome;
    }


    public TodaysPlace createTodaysPlace(Integer count,Integer homeCount){
        //TODO: minNum 어떻게 할건지!!
        int [] placeIndecies = getRandomIndex(1,count);
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
        System.out.println("______min: "+minNum+"  MAX: "+max);
        for(int i=0;i<3;i++){
            int selectedNumber=(int) ((Math.random() * (max - min)) + min);
            System.out.println("______SELECTED!!! : "+selectedNumber);
            while(placeRepository.findById(selectedNumber)==null){
                System.out.println("______SELECTED is NULL!!! : "+selectedNumber);
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
        List<Place> placeList = new ArrayList<Place>();
        Place place1=placeRepository.getOne(home.getTodaysPlace().getPlace1Id());
        Place place2=placeRepository.getOne(home.getTodaysPlace().getPlace2Id());
        Place place3=placeRepository.getOne(home.getTodaysPlace().getPlace3Id());
        placeList.add(place1);
        placeList.add(place2);
        placeList.add(place3);
        if(place1 ==null || place2 ==null || place3 ==null){
            new KoreaGuideException(KoreaGuideError.ENTITY_NOT_FOUND,"place");
        }
        List<PlaceApiResponse> placeApiResponses =new ArrayList<PlaceApiResponse>();
        for(int i=0;i<3;i++){
            PlaceApiResponse placeApiResponse = PlaceApiResponse.builder()
                    .id(placeList.get(i).getId())
                    .title(placeList.get(i).getTitle())
                    .firstImage(placeList.get(i).getFirstImage()).build();
            placeApiResponses.add(placeApiResponse);
        }
        HomeApiResponse homeApiResponse = HomeApiResponse.builder()
                .id(home.getId())
                .wordAudio(home.getWord().getAudio())
                .wordId(home.getWord().getId())
                .word(home.getWord().getWordEng())
                .wordImage(home.getWord().getImage())
                .placeList(placeApiResponses)
                .build();
        return homeApiResponse;
    }

}

package com.example.koreaguide.service;

import com.example.koreaguide.model.entity.MyPlace;
import com.example.koreaguide.model.entity.MyWord;
import com.example.koreaguide.model.entity.MyWordFolder;
import com.example.koreaguide.model.entity.Place;
import com.example.koreaguide.model.entity.PlaceKorean;
import com.example.koreaguide.model.entity.PlaceWithWord;
import com.example.koreaguide.model.entity.User;
import com.example.koreaguide.model.entity.Word;
import com.example.koreaguide.model.enumclass.MyWordStatus;
import com.example.koreaguide.model.enumclass.PlaceStatus;
import com.example.koreaguide.model.exception.KoreaGuideError;
import com.example.koreaguide.model.exception.KoreaGuideException;
import com.example.koreaguide.model.network.Pagination;
import com.example.koreaguide.model.network.response.PlaceDetailApiResponse;
import com.example.koreaguide.model.network.response.PlaceDetailHeadApiResponse;
import com.example.koreaguide.model.network.response.PlaceDetailWordApiResponse;
import com.example.koreaguide.repository.MyPlaceRepository;
import com.example.koreaguide.repository.MyWordFolderRepository;
import com.example.koreaguide.repository.MyWordRepository;
import com.example.koreaguide.repository.PlaceKoreanRepository;
import com.example.koreaguide.repository.PlaceRepository;
import com.example.koreaguide.repository.PlaceWithWordRepository;
import com.example.koreaguide.repository.UserRepository;
import com.example.koreaguide.repository.WordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/*
 * @author : Jisoo Kim
 * @date: 2021/03/22 1:30 오후
*/
@Service
public class PlaceApiLogicService {
    @Autowired
    PlaceRepository placeRepository;

    @Autowired
    MyPlaceRepository myPlaceRepository;

    @Autowired
    WordRepository wordRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    PlaceKoreanRepository placeKoreanRepository;

    @Autowired
    MyWordRepository myWordRepository;

    @Autowired
    MyWordFolderRepository myWordFolderRepository;

    @Autowired
    PlaceWithWordRepository placeWithWordRepository;

    public PlaceDetailApiResponse getPlaceDetailAll(Integer userId, Integer id) {
        Optional<Place> place = placeRepository.findById(id);
        Optional<User> user = userRepository.findById(userId);
        return place.map(selectedPlace->{
                System.out.println("id: "+selectedPlace.getId());
               MyPlace myPlace = myPlaceRepository.findByPlaceAndUserId(selectedPlace,userId);
                PlaceStatus placeStatus = PlaceStatus.NO_STATUS;
//                System.out.println("hi");
                if(myPlace!=null){
                    System.out.println("NOT NULL");
                    placeStatus=myPlace.getStatus();
                }
                System.out.println("selected Place: "+selectedPlace.getTitle()+"   place status:"+placeStatus+"  userId:"+userId);
                return responseAll(selectedPlace,placeStatus,userId);
        }).orElseThrow(()->new KoreaGuideException(KoreaGuideError.ENTITY_NOT_FOUND_PLACE));

    }


    public PlaceDetailApiResponse getPlaceDetailEng(Integer userId, Integer id) {
        Optional<Place> place = placeRepository.findById(id);
        return place.map(selectedPlace->{
            MyPlace myPlace = myPlaceRepository.findByPlaceAndUserId(selectedPlace,userId);
            PlaceStatus placeStatus = PlaceStatus.NO_STATUS;
            if(myPlace!=null){
                placeStatus=myPlace.getStatus();
            }
            return responseEng(selectedPlace,placeStatus,userId);
        }).orElseThrow(()->new KoreaGuideException(KoreaGuideError.ENTITY_NOT_FOUND_PLACE));
    }

    public PlaceDetailApiResponse getPlaceDetailKor(Integer userId, Integer id) {
        Optional<Place> place = placeRepository.findById(id);
        return place.map(selectedPlace->{
            MyPlace myPlace = myPlaceRepository.findByPlaceAndUserId(selectedPlace,userId);
            PlaceStatus placeStatus = PlaceStatus.NO_STATUS;
            if(myPlace!=null){
                placeStatus=myPlace.getStatus();
            }
            return responseKor(selectedPlace,placeStatus,userId);
        }).orElseThrow(()->new KoreaGuideException(KoreaGuideError.ENTITY_NOT_FOUND_PLACE));
    }

    private PlaceDetailApiResponse responseAll(Place selectedPlace,PlaceStatus status,Integer userId) {
        System.out.println("sel:"+selectedPlace.getTitle());
        Optional<PlaceKorean> placeKorean =placeKoreanRepository.findByPlaceId(selectedPlace.getId());
        PlaceDetailApiResponse response =placeKorean.map(selectedPlaceKorean->{
            PlaceDetailApiResponse placeDetailApiResponse = PlaceDetailApiResponse.builder()
                    .userId(userId)
                    .placeStatus(status)
                    .id(selectedPlace.getId())
                    .areaCode(selectedPlace.getAreaCode())
                    .title(selectedPlace.getTitle())
                    .address(selectedPlace.getAddress1())
                    .mapX(selectedPlace.getMapX())
                    .mapY(selectedPlace.getMapY())
                    .overview_english(selectedPlace.getOverview())
                    .overview_korean(selectedPlaceKorean.getKorOverview())
                    .firstImage(selectedPlace.getFirstImage())
                    .firstImage2(selectedPlace.getFirstImage2())
                    .category1(selectedPlace.getCat1())
                    .category2(selectedPlace.getCat2())
                    .category3(selectedPlace.getCat3())
                    .build();
            return placeDetailApiResponse;
        }).orElseThrow(()->new KoreaGuideException(KoreaGuideError.ENTITY_NOT_FOUND_PLACE_KOREAN));
        return response;
    }

    private PlaceDetailApiResponse responseEng(Place selectedPlace,PlaceStatus status,Integer userId) {
        PlaceDetailApiResponse placeDetailApiResponse = PlaceDetailApiResponse.builder()
                .userId(userId)
                .placeStatus(status)
                .id(selectedPlace.getId())
                .areaCode(selectedPlace.getAreaCode())
                .title(selectedPlace.getTitle())
                .address(selectedPlace.getAddress1())
                .mapX(selectedPlace.getMapX())
                .mapY(selectedPlace.getMapY())
                .overview_english(selectedPlace.getOverview())
                .firstImage(selectedPlace.getFirstImage())
                .firstImage2(selectedPlace.getFirstImage2())
                .category1(selectedPlace.getCat1())
                .category2(selectedPlace.getCat2())
                .category3(selectedPlace.getCat3())
                .build();
        return placeDetailApiResponse;
    }

    private PlaceDetailApiResponse responseKor(Place selectedPlace,PlaceStatus status,Integer userId) {
        Optional<PlaceKorean> placeKorean =placeKoreanRepository.findByPlaceId(selectedPlace.getId());
        PlaceDetailApiResponse response =placeKorean.map(selectedPlaceKorean->{
            PlaceDetailApiResponse placeDetailApiResponse = PlaceDetailApiResponse.builder()
                    .userId(userId)
                    .placeStatus(status)
                    .id(selectedPlace.getId())
                    .areaCode(selectedPlace.getAreaCode())
                    .title(selectedPlace.getTitle())
                    .address(selectedPlace.getAddress1())
                    .mapX(selectedPlace.getMapX())
                    .mapY(selectedPlace.getMapY())
                    .overview_korean(selectedPlaceKorean.getKorOverview())
                    .firstImage(selectedPlace.getFirstImage())
                    .firstImage2(selectedPlace.getFirstImage2())
                    .category1(selectedPlace.getCat1())
                    .category2(selectedPlace.getCat2())
                    .category3(selectedPlace.getCat3())
                    .build();
            return placeDetailApiResponse;
        }).orElseThrow(()->new KoreaGuideException(KoreaGuideError.ENTITY_NOT_FOUND_PLACE_KOREAN));
        return response;
    }

//    public PlaceDetailHeadApiResponse getWord(Integer userId, Integer id, Integer pageable) {
//        Integer pageSize=1;
//        // 해당 유저에게 이 단어가 있는지 알아내고, 단어들을 페이지네이션 해야함
//        Optional<Place> place = placeRepository.findById(id);
//        return place.map(selectedPlace->{
//            List<Word> words = getPlaceWordList(selectedPlace);
//            List<MyWordStatus> myWordStatusList = getWordStatusList(words,userId);
//            System.out.println("+++++++++++++");
//            System.out.println("MY WORD STATUS LIST"+myWordStatusList);
//            Integer totalPage;
//            if(words.size()%pageSize!=0){
//                totalPage=words.size()/pageSize+1;
//            }else{
//                totalPage=words.size()/pageSize;
//            }
//            return responseForWordPage(totalPage,pageable,words,userId,id,myWordStatusList);
//        }).orElseThrow(()->new KoreaGuideException(KoreaGuideError.ENTITY_NOT_FOUND_PLACE));
//
//    }
    // pageination for place detail word
    public PlaceDetailHeadApiResponse getWord(Integer userId, Integer id,Integer pageable){
        Integer pageSize=1;
        Optional<Place> place = placeRepository.findById(id);
        return place.map(selectedPlace->{
            List<PlaceWithWord> placeWithWords = placeWithWordRepository.findByPlaceId(selectedPlace.getId());
            List<Word> wordForPlace = wordRepository.findAllById(placeWithWords.stream().map(i->i.getWordId()).collect(Collectors.toList()));
            List<MyWordStatus> myWordStatusList = getWordStatusList(wordForPlace,userId);
            Integer totalPage;
            if(wordForPlace.size()%pageSize!=0){
                totalPage=wordForPlace.size()/pageSize+1;
            }else{
                totalPage=wordForPlace.size()/pageSize;
            }
            return responseForWordPage(totalPage,pageable,wordForPlace,userId,id,myWordStatusList);
        }).orElseThrow(()->new KoreaGuideException(KoreaGuideError.ENTITY_NOT_FOUND_PLACE));
    }

    public PlaceDetailHeadApiResponse getWordWithoutPagination(Integer userId, Integer id){
        Optional<Place> place = placeRepository.findById(id);
        return place.map(selectedPlace->{
            List<PlaceWithWord> placeWithWords = placeWithWordRepository.findByPlaceId(selectedPlace.getId());
//            Optional<Word> w = wordRepository.findById(51);
//            if(w.isPresent()){
//                System.out.println("W : "+w);
//            }else{
//                System.out.println("W not valid");
//            }
            List<Word> wordForPlace = new ArrayList<Word>();
            for(int i =0;i<placeWithWords.size();i++){
                Word word =wordRepository.getOne(placeWithWords.get(i).getWordId());
                wordForPlace.add(word);
            }
//            List<Word> wordForPlace = wordRepository.findAllById(placeWithWords.stream().map(i->{
//                System.out.println("IDDDD: "+i.getWordId());
//                return i.getWordId();
//            }).collect(Collectors.toList()));
            List<MyWordStatus> myWordStatusList = getWordStatusList(wordForPlace,userId);
            return responseForWord(wordForPlace,userId,id,myWordStatusList);
        }).orElseThrow(()->new KoreaGuideException(KoreaGuideError.ENTITY_NOT_FOUND_PLACE));
    }

    private List<MyWordStatus> getWordStatusList(List<Word> words,Integer userId) {
        List<MyWordStatus> myWordStatuses =new ArrayList<MyWordStatus>();
        List<MyWordFolder> myWordFolder =myWordFolderRepository.findAllByUserId(userId);
        if(myWordFolder.size()==0){
            for(int i=0;i<words.size();i++){
                myWordStatuses.add(MyWordStatus.NO_STATUS);
            }
        }else{
            System.out.println("WORD SIZE: "+words.size());
            for (int i = 0; i < words.size(); i++) {
                System.out.println("WORDDD : "+words.get(i).getId());
                for(int j=0;j <myWordFolder.size();j++) {
                    System.out.println("i: "+i+"   j:"+j);
                    System.out.println("WORD FOLDER: " + myWordFolder.get(j));
//                    System.out.println("WORD: " + words.get(i));
                    Optional<MyWord> myWord = myWordRepository.findByMyWordFolderAndWord(myWordFolder.get(j), words.get(i));
                    if(myWord.isEmpty()){
                        System.out.println("Empty: !!!!" );
                        myWordStatuses.add(MyWordStatus.NO_STATUS);
                    }else{
                        System.out.println("NOT Empty!!! ");
                        myWord.map(selectedWord ->
                            myWordStatuses.add(selectedWord.getWordStatus())
                        );
                    }
                }
            }
        }
        return myWordStatuses;
    }

    private PlaceDetailHeadApiResponse responseForWordPage(Integer totalPage, Integer currentPage,List<Word> words,Integer userId,Integer placeId,List<MyWordStatus> myWordStatusList) {
        if(currentPage>totalPage || currentPage<1){
            throw new KoreaGuideException(KoreaGuideError.PAGINATION_OUT_OF_INDEX);
        }
        Integer startItemindex,endItemindex;
        if(currentPage == 1){
            startItemindex=0;
        }else{
            // page 마다 1개씩으로 바껴서 currentPage-1
            startItemindex = currentPage-1;
        }
        endItemindex = startItemindex+1;
        if(endItemindex>=words.size()){
            endItemindex = startItemindex+1;
        }
        System.out.println("currentPage:"+currentPage+"   startItemindex:"+startItemindex);
        Pagination pagination = Pagination.builder()
                .currentPage(currentPage)
                .currentElements(endItemindex-startItemindex)
                .totalPages(totalPage)
                .totalElements(words.size()).build();
        List<PlaceDetailWordApiResponse> placeDetailWordApiResponseArrayList =new ArrayList<PlaceDetailWordApiResponse>();
        for(int i=startItemindex;i<endItemindex;i++){
            System.out.println("WORD!!! "+words.get(i));
//            MyWordStatus wordStatus=MyWordStatus.NO_STATUS;
//            List<MyWordFolder> myWordFolder =myWordFolderRepository.findAllByUserId(userId);
//            if(myWordFolder.size()==0){
//                wordStatus=MyWordStatus.NO_STATUS;
//            }else{
//                System.out.println("myWordFolder not null");
//
//                for(int j=0;j<myWordFolder.size();j++){
//                    System.out.println("j: "+j);
//                    Optional<MyWord> myWord = myWordRepository.findByMyWordFolderAndWord(myWordFolder.get(j),words.get(i));
//                    wordStatus=myWord.map(selectedWord-> selectedWord.getWordStatus()).orElse(MyWordStatus.NO_STATUS);
//                }
//            }
            PlaceDetailWordApiResponse placeDetailWordApiResponse = PlaceDetailWordApiResponse.builder()
                    .wordId(words.get(i).getId())
                    .wordStatus(myWordStatusList.get(i))
                    .wordEng(words.get(i).getWordEng())
                    .wordKor(words.get(i).getWordKor())
                    .meaningKor1(words.get(i).getMeaningKor1())
                    .meaningKor2(words.get(i).getMeaningKor2())
                    .meaningEng1(words.get(i).getMeaningEng1())
                    .meaningEng2(words.get(i).getMeaningEng2())
                    .pronunciationEng(words.get(i).getPronunciationEng())
                    .pronunciationKor(words.get(i).getPronunciationKor())
                    .wordImage(words.get(i).getImage())
                    .wordAudio(words.get(i).getAudio()).build();
            placeDetailWordApiResponseArrayList.add(placeDetailWordApiResponse);
        }
        PlaceDetailHeadApiResponse placeDetailHeadApiResponse = PlaceDetailHeadApiResponse.builder()
                .placeId(placeId)
                .pagination(pagination)
                .wordList(placeDetailWordApiResponseArrayList)
                .userId(userId).build();
        return placeDetailHeadApiResponse;
    }

    // place detail word list wo pagination
    private PlaceDetailHeadApiResponse responseForWord(List<Word> words,Integer userId,Integer placeId,List<MyWordStatus> myWordStatusList) {
        List<PlaceDetailWordApiResponse> placeDetailWordApiResponseArrayList =new ArrayList<PlaceDetailWordApiResponse>();
        for(int i=0;i<words.size();i++){
//            System.out.println("WORD!!! "+words.get(i));
            PlaceDetailWordApiResponse placeDetailWordApiResponse = PlaceDetailWordApiResponse.builder()
                    .wordId(words.get(i).getId())
                    .wordStatus(myWordStatusList.get(i))
                    .wordEng(words.get(i).getWordEng())
                    .wordKor(words.get(i).getWordKor())
                    .meaningKor1(words.get(i).getMeaningKor1())
                    .meaningKor2(words.get(i).getMeaningKor2())
                    .meaningEng1(words.get(i).getMeaningEng1())
                    .meaningEng2(words.get(i).getMeaningEng2())
                    .pronunciationEng(words.get(i).getPronunciationEng())
                    .pronunciationKor(words.get(i).getPronunciationKor())
                    .wordImage(words.get(i).getImage())
                    .wordAudio(words.get(i).getAudio()).build();
            placeDetailWordApiResponseArrayList.add(placeDetailWordApiResponse);
        }
        PlaceDetailHeadApiResponse placeDetailHeadApiResponse = PlaceDetailHeadApiResponse.builder()
                .placeId(placeId)
                .wordList(placeDetailWordApiResponseArrayList)
                .userId(userId).build();
        return placeDetailHeadApiResponse;
    }


//    private List<Word> getPlaceWordList(Place place){
//        Optional<PlaceWithWord> placeWithWord =placeWithWordRepository.findByContentId(place.getContentId());
//        String fieldName="word";
//        List<Word> wordList =new ArrayList<Word>();
//        placeWithWord.map(selected->{
//            Integer wordCount=selected.getWordCount();
//            if(wordCount==0){
//                throw new KoreaGuideException(KoreaGuideError.ENTITY_EMPTY_PLACEWITHWORD);
//            }
//            for(int i=0;i<wordCount;i++){
//                String newFieldName=fieldName+String.valueOf(i+1);
//                Optional<Word> wordFound=wordRepository.findById(selected.getWord(newFieldName));
//                wordFound.map(selectedWord->{
//                    wordList.add(selectedWord);
//                    return wordList;
//                }).orElseThrow(()->new KoreaGuideException(KoreaGuideError.ENTITY_NOT_FOUND_WORD));
//            }
//            return wordList;
//        }).orElseThrow(()->new KoreaGuideException(KoreaGuideError.ENTITY_NOT_FOUND_PLACEWITHWORD));
//        return wordList;
//    }


}

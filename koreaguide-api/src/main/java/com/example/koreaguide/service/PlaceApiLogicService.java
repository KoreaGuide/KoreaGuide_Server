package com.example.koreaguide.service;

import com.example.koreaguide.model.entity.MyPlace;
import com.example.koreaguide.model.entity.MyWord;
import com.example.koreaguide.model.entity.MyWordFolder;
import com.example.koreaguide.model.entity.Place;
import com.example.koreaguide.model.entity.PlaceKorean;
import com.example.koreaguide.model.entity.PlaceWithWord;
import com.example.koreaguide.model.entity.Word;
import com.example.koreaguide.model.enumclass.MyWordStatus;
import com.example.koreaguide.model.enumclass.PlaceStatus;
import com.example.koreaguide.model.exception.KoreaGuideError;
import com.example.koreaguide.model.exception.KoreaGuideException;
import com.example.koreaguide.model.network.Pagination;
import com.example.koreaguide.model.network.response.MyWordListApiResponse;
import com.example.koreaguide.model.network.response.PlaceApiResponse;
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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
        return place.map(selectedPlace->{
            MyPlace myPlace = myPlaceRepository.findByPlace(selectedPlace);
            PlaceStatus placeStatus = PlaceStatus.NO_STATUS;
            if(myPlace!=null){
                placeStatus=myPlace.getStatus();
            }
            return responseAll(selectedPlace,placeStatus,userId);
        }).orElseThrow(()->new KoreaGuideException(KoreaGuideError.ENTITY_NOT_FOUND_PLACE));

    }


    public PlaceDetailApiResponse getPlaceDetailEng(Integer userId, Integer id) {
        Optional<Place> place = placeRepository.findById(id);
        return place.map(selectedPlace->{
            MyPlace myPlace = myPlaceRepository.findByPlace(selectedPlace);
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
            MyPlace myPlace = myPlaceRepository.findByPlace(selectedPlace);
            PlaceStatus placeStatus = PlaceStatus.NO_STATUS;
            if(myPlace!=null){
                placeStatus=myPlace.getStatus();
            }
            return responseKor(selectedPlace,placeStatus,userId);
        }).orElseThrow(()->new KoreaGuideException(KoreaGuideError.ENTITY_NOT_FOUND_PLACE));
    }

    private PlaceDetailApiResponse responseAll(Place selectedPlace,PlaceStatus status,Integer userId) {
        Optional<PlaceKorean> placeKorean =placeKoreanRepository.findByContentId(selectedPlace.getContentId());
        PlaceDetailApiResponse response =placeKorean.map(selectedPlaceKorean->{
            PlaceDetailApiResponse placeDetailApiResponse = PlaceDetailApiResponse.builder()
                    .userId(userId)
                    .placeStatus(status)
                    .id(selectedPlace.getId())
                    .contentId(selectedPlace.getContentId())
                    .areaCode(selectedPlace.getAreaCode())
                    .title(selectedPlace.getTitle())
                    .address1(selectedPlace.getAddress1())
                    .address2(selectedPlace.getAddress2())
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
                .contentId(selectedPlace.getContentId())
                .areaCode(selectedPlace.getAreaCode())
                .title(selectedPlace.getTitle())
                .address1(selectedPlace.getAddress1())
                .address2(selectedPlace.getAddress2())
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
        Optional<PlaceKorean> placeKorean =placeKoreanRepository.findByContentId(selectedPlace.getContentId());
        PlaceDetailApiResponse response =placeKorean.map(selectedPlaceKorean->{
            PlaceDetailApiResponse placeDetailApiResponse = PlaceDetailApiResponse.builder()
                    .userId(userId)
                    .placeStatus(status)
                    .id(selectedPlace.getId())
                    .contentId(selectedPlace.getContentId())
                    .areaCode(selectedPlace.getAreaCode())
                    .title(selectedPlace.getTitle())
                    .address1(selectedPlace.getAddress1())
                    .address2(selectedPlace.getAddress2())
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

    public PlaceDetailHeadApiResponse getWord(Integer userId, Integer id, Integer pageable) {
        Integer pageSize=2;
        // 해당 유저에게 이 단어가 있는지 알아내고, 단어들을 페이지네이션 해야함
        Optional<Place> place = placeRepository.findById(id);
        return place.map(selectedPlace->{
            List<Word> words = getPlaceWordList(selectedPlace);
            Integer totalPage;
            if(words.size()%pageSize!=0){
                totalPage=words.size()/pageSize+1;
            }else{
                totalPage=words.size()/pageSize;
            }
            return responseForWordPage(totalPage,pageable,words,userId,id);
        }).orElseThrow(()->new KoreaGuideException(KoreaGuideError.ENTITY_NOT_FOUND_PLACE));

    }

    private PlaceDetailHeadApiResponse responseForWordPage(Integer totalPage, Integer currentPage,List<Word> words,Integer userId,Integer placeId) {
        if(currentPage>totalPage || currentPage<1){
            throw new KoreaGuideException(KoreaGuideError.PAGINATION_OUT_OF_INDEX);
        }
        Integer startItemindex,endItemindex;
        if(currentPage == 1){
            startItemindex=0;
        }else{
            startItemindex = currentPage;
        }
        endItemindex = startItemindex+2;
        if(endItemindex>=words.size()){
            endItemindex = startItemindex+1;
        }
        Pagination pagination = Pagination.builder()
                .currentPage(currentPage)
                .currentElements(endItemindex-startItemindex)
                .totalPages(totalPage)
                .totalElements(words.size()).build();
        List<PlaceDetailWordApiResponse> placeDetailWordApiResponseArrayList =new ArrayList<PlaceDetailWordApiResponse>();
        for(int i=startItemindex;i<endItemindex;i++){
            MyWordStatus wordStatus=MyWordStatus.NO_STATUS;
            List<MyWordFolder> myWordFolder =myWordFolderRepository.findAllByUserId(userId);
            if(myWordFolder.size()==0){
                wordStatus=MyWordStatus.NO_STATUS;
            }else{
                Optional<MyWord> myWord;
                for(int j=0;j<myWordFolder.size();j++){
                    myWord = myWordRepository.findByMyWordFolderAndWord(myWordFolder.get(i),words.get(i));
                    if(myWord.isPresent()){
                        wordStatus = myWord.get().getWordStatus();
                        break;
                    }
                }
            }
            PlaceDetailWordApiResponse placeDetailWordApiResponse = PlaceDetailWordApiResponse.builder()
                    .wordId(words.get(i).getId())
                    .wordStatus(wordStatus)
                    .wordEng(words.get(i).getWordEng())
                    .wordKor(words.get(i).getWordKor())
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

    private List<Word> getPlaceWordList(Place place){
        Optional<PlaceWithWord> placeWithWord =placeWithWordRepository.findByContentId(place.getContentId());
        String fieldName="word";
        List<Word> wordList =new ArrayList<Word>();
        placeWithWord.map(selected->{
            Integer wordCount=selected.getWordCount();
            if(wordCount==0){
                throw new KoreaGuideException(KoreaGuideError.ENTITY_EMPTY_PLACEWITHWORD);
            }
            for(int i=0;i<wordCount;i++){
                String newFieldName=fieldName+String.valueOf(i+1);
                Optional<Word> wordFound=wordRepository.findById(selected.getWord(newFieldName));
                wordFound.map(selectedWord->{
                    wordList.add(selectedWord);
                    return wordList;
                }).orElseThrow(()->new KoreaGuideException(KoreaGuideError.ENTITY_NOT_FOUND_WORD));
            }
            return wordList;
        }).orElseThrow(()->new KoreaGuideException(KoreaGuideError.ENTITY_NOT_FOUND_PLACEWITHWORD));
        return wordList;
    }


}

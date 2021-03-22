package com.example.koreaguide.service;

import com.example.koreaguide.model.entity.MyPlace;
import com.example.koreaguide.model.entity.Place;
import com.example.koreaguide.model.entity.PlaceKorean;
import com.example.koreaguide.model.enumclass.PlaceStatus;
import com.example.koreaguide.model.exception.KoreaGuideError;
import com.example.koreaguide.model.exception.KoreaGuideException;
import com.example.koreaguide.model.network.response.PlaceApiResponse;
import com.example.koreaguide.model.network.response.PlaceDetailApiResponse;
import com.example.koreaguide.model.network.response.PlaceDetailHeadApiResponse;
import com.example.koreaguide.repository.MyPlaceRepository;
import com.example.koreaguide.repository.MyWordRepository;
import com.example.koreaguide.repository.PlaceKoreanRepository;
import com.example.koreaguide.repository.PlaceRepository;
import com.example.koreaguide.repository.UserRepository;
import com.example.koreaguide.repository.WordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

//    public PlaceDetailHeadApiResponse getWord(Integer userId, Integer id) {
//
//    }
}

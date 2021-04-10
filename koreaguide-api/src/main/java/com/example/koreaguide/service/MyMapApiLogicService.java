package com.example.koreaguide.service;

import com.example.koreaguide.model.entity.MyPlace;
import com.example.koreaguide.model.entity.Place;
import com.example.koreaguide.model.entity.User;
import com.example.koreaguide.model.enumclass.PlaceStatus;
import com.example.koreaguide.model.exception.KoreaGuideError;
import com.example.koreaguide.model.exception.KoreaGuideException;
import com.example.koreaguide.model.network.Header;
import com.example.koreaguide.model.network.request.MyMapApiRequest;
import com.example.koreaguide.model.network.response.MyMapApiResponse;
import com.example.koreaguide.model.network.response.MyMapPlaceApiResponse;
import com.example.koreaguide.model.network.response.MyWordListApiResponse;
import com.example.koreaguide.model.network.response.PlaceDetailApiResponse;
import com.example.koreaguide.repository.MyPlaceRepository;
import com.example.koreaguide.repository.PlaceRepository;
import com.example.koreaguide.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MyMapApiLogicService {
    @Autowired
    PlaceRepository placeRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    MyPlaceRepository myPlaceRepository;

    public MyMapApiResponse addToMyMap(Integer id, Header<MyMapApiRequest> request) {
        MyMapApiRequest body = request.getData();
        Optional<Place> place = placeRepository.findById(body.getPlaceId());
        Optional<User> user = userRepository.findById(id);
        return place.map(selectedPlace->{
            MyPlace addedMyPlace = user.map(selectedUser->{
                Optional<MyPlace> myPlace = myPlaceRepository.findByPlaceAndUser(selectedPlace,selectedUser);
                if(myPlace.isPresent()){
                    throw new KoreaGuideException(KoreaGuideError.DUPLICATE_ERROR_MYPLACE);
                }
                else {
                    System.out.println("BODY!!!!"+body.getPlaceStatus());
                    System.out.println("NAME!!!!"+PlaceStatus.HAVE_BEEN_TO);
                    System.out.println("RESULT!!!!"+body.getPlaceStatus().equals(PlaceStatus.HAVE_BEEN_TO));
                    if(body.getPlaceStatus().equals(PlaceStatus.HAVE_BEEN_TO)) {
                        MyPlace newMyPlace = newMyPlace = MyPlace.builder()
                                .place(selectedPlace)
                                .diary(body.getDiary())
                                .status(PlaceStatus.HAVE_BEEN_TO)
                                .user(selectedUser).build();
                        return myPlaceRepository.save(newMyPlace);
                    } else {
                        MyPlace newMyPlace = newMyPlace = MyPlace.builder()
                                .place(selectedPlace)
                                .status(PlaceStatus.WISH_LIST)
                                .user(selectedUser).build();
                        return myPlaceRepository.save(newMyPlace);
                    }
                }

            }).orElseThrow(()->new KoreaGuideException(KoreaGuideError.ENTITY_NOT_FOUND_USER));
            return responseAdd(id,addedMyPlace,selectedPlace);
        }).orElseThrow(()->new KoreaGuideException(KoreaGuideError.ENTITY_NOT_FOUND_PLACE));
    }


    public MyMapApiResponse getMyMapAll(Integer id) {
        List<MyPlace> myPlaceList = myPlaceRepository.findAllByUserId(id);
        if(myPlaceList.size()==0){
            throw new KoreaGuideException(KoreaGuideError.ENTITY_EMPTY_MYPLACE);
        }else{
            List<Place> placeList=new ArrayList<Place>();
            for(int i=0;i<myPlaceList.size();i++){
                Optional<Place> place = placeRepository.findById(myPlaceList.get(i).getPlace().getId());
                place.map(selectedPlace->{
                    return placeList.add(selectedPlace);
                }).orElseThrow(()->new KoreaGuideException(KoreaGuideError.ENTITY_NOT_FOUND_PLACE));
            }
            return responseGet(id,myPlaceList,placeList);
        }
    }


    public MyMapApiResponse getMyMapWish(Integer id) {
        List<MyPlace> myPlaceList = myPlaceRepository.findAllByUserIdAndStatus(id,PlaceStatus.WISH_LIST);
        if(myPlaceList.size()==0){
            throw new KoreaGuideException(KoreaGuideError.ENTITY_EMPTY_MYPLACE);
        }else{
            List<Place> placeList=new ArrayList<Place>();
            for(int i=0;i<myPlaceList.size();i++){
                Optional<Place> place = placeRepository.findById(myPlaceList.get(i).getPlace().getId());
                place.map(selectedPlace->{
                    return placeList.add(selectedPlace);
                }).orElseThrow(()->new KoreaGuideException(KoreaGuideError.ENTITY_NOT_FOUND_PLACE));
            }
            return responseGet(id,myPlaceList,placeList);
        }
    }
    public MyMapApiResponse getMyMapHaveBeenTo(Integer id) {
        List<MyPlace> myPlaceList = myPlaceRepository.findAllByUserIdAndStatus(id,PlaceStatus.HAVE_BEEN_TO);
        if(myPlaceList.size()==0){
            throw new KoreaGuideException(KoreaGuideError.ENTITY_EMPTY_MYPLACE);
        }else{
            List<Place> placeList=new ArrayList<Place>();
            for(int i=0;i<myPlaceList.size();i++){
                Optional<Place> place = placeRepository.findById(myPlaceList.get(i).getPlace().getId());
                place.map(selectedPlace->{
                    return placeList.add(selectedPlace);
                }).orElseThrow(()->new KoreaGuideException(KoreaGuideError.ENTITY_NOT_FOUND_PLACE));
            }
            return responseGet(id,myPlaceList,placeList);
        }
    }

    public MyMapApiResponse deleteMyMap(Integer id, Header<MyMapApiRequest> request) {
        MyMapApiRequest body = request.getData();
        Optional<Place> place = placeRepository.findById(body.getPlaceId());
        Optional<User> user = userRepository.findById(id);
        return place.map(selectedPlace -> {
            MyMapApiResponse finalResponse =user.map(selectedUser->{
                Optional<MyPlace> myPlace = myPlaceRepository.findByPlaceAndUser(selectedPlace,selectedUser);
                MyMapApiResponse response =myPlace.map(selectedMyPlace->{
                    myPlaceRepository.delete(selectedMyPlace);
                    List<MyPlace> myPlaceList = myPlaceRepository.findAllByUserId(id);
                    return responseDelete(selectedUser.getId(),myPlaceList.size());
                }).orElseThrow(()->new KoreaGuideException(KoreaGuideError.ENTITY_NOT_FOUND_MYPLACE));
                return response;
            }).orElseThrow(()->new KoreaGuideException(KoreaGuideError.ENTITY_NOT_FOUND_USER));
            return finalResponse;
        }).orElseThrow(()->new KoreaGuideException(KoreaGuideError.ENTITY_NOT_FOUND_PLACE));
    }

    public MyMapApiResponse changeMyMap(Integer id, Header<MyMapApiRequest> request) {
        MyMapApiRequest body = request.getData();
        Optional<Place> place = placeRepository.findById(body.getPlaceId());
        Optional<User> user = userRepository.findById(id);
        return place.map(selectedPlace->{
            MyMapApiResponse finalResponse = user.map(selectedUser->{
                Optional<MyPlace> myPlace = myPlaceRepository.findByPlaceAndUser(selectedPlace,selectedUser);
                MyMapApiResponse response =myPlace.map(selectedMyPlace->{
                    if(body.getPlaceStatus() == PlaceStatus.WISH_LIST){
                        selectedMyPlace.setStatus(PlaceStatus.WISH_LIST);
                        selectedMyPlace.setDiary(null);
                        myPlaceRepository.save(selectedMyPlace);
                    }else{
                        selectedMyPlace.setStatus(PlaceStatus.HAVE_BEEN_TO);
                        if(body.getDiary()!=null) {
                            selectedMyPlace.setDiary(body.getDiary());
                        }
                        myPlaceRepository.save(selectedMyPlace);
                    }
                    List<MyPlace> myPlaceList = myPlaceRepository.findAllByUserId(id);
                    return responseAdd(selectedUser.getId(),selectedMyPlace,selectedPlace);
                }).orElseThrow(()->new KoreaGuideException(KoreaGuideError.ENTITY_NOT_FOUND_MYPLACE));
                return response;
            }).orElseThrow(()->new KoreaGuideException(KoreaGuideError.ENTITY_NOT_FOUND_USER));
            return finalResponse;
        }).orElseThrow(()->new KoreaGuideException(KoreaGuideError.ENTITY_NOT_FOUND_PLACE));
    }


    //RESPONSES
    private MyMapApiResponse responseAdd(Integer id, MyPlace resultPlace,Place place) {
        List<MyMapPlaceApiResponse> myMapPlaceApiResponseList=new ArrayList<MyMapPlaceApiResponse>();
        MyMapPlaceApiResponse myMapPlaceApiResponse = MyMapPlaceApiResponse.builder()
                .myMapId(resultPlace.getId())
                .placeId(place.getId())
                .placeStatus(resultPlace.getStatus())
                .title(place.getTitle())
                .diary(resultPlace.getDiary()).build();
        myMapPlaceApiResponseList.add(myMapPlaceApiResponse);
        MyMapApiResponse myMapApiResponse = MyMapApiResponse.builder()
                .userId(id)
                .placeList(myMapPlaceApiResponseList).build();
        return myMapApiResponse;
    }

    private MyMapApiResponse responseGet(Integer id, List<MyPlace> myPlaceList,List<Place> placeList) {
        List<MyMapPlaceApiResponse> myMapPlaceApiResponseList=new ArrayList<MyMapPlaceApiResponse>();
        for(int i=0;i<placeList.size();i++){
            MyMapPlaceApiResponse myMapPlaceApiResponse = MyMapPlaceApiResponse.builder()
                    .myMapId(myPlaceList.get(i).getId())
                    .placeId(placeList.get(i).getId())
                    .placeStatus(myPlaceList.get(i).getStatus())
                    .title(placeList.get(i).getTitle())
                    .address1(placeList.get(i).getAddress1())
                    .mapX(placeList.get(i).getMapX())
                    .mapY(placeList.get(i).getMapY())
                    .firstImage(placeList.get(i).getFirstImage())
                    .overview_english(placeList.get(i).getOverview())
                    .diary(myPlaceList.get(i).getDiary()).build();
            myMapPlaceApiResponseList.add(myMapPlaceApiResponse);
        }
        MyMapApiResponse myMapApiResponse = MyMapApiResponse.builder()
                .userId(id)
                .placeCount(placeList.size())
                .placeList(myMapPlaceApiResponseList).build();
        return myMapApiResponse;
    }

    private MyMapApiResponse responseDelete(Integer id, Integer currentSize){
        MyMapApiResponse myMapApiResponse = MyMapApiResponse.builder()
                .userId(id)
                .placeCount(currentSize)
                .build();
        return myMapApiResponse;
    }

}

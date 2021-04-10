package com.example.koreaguide.service;

import com.example.koreaguide.model.entity.Code;
import com.example.koreaguide.model.entity.MyWord;
import com.example.koreaguide.model.entity.Place;
import com.example.koreaguide.model.entity.RegionColor;
import com.example.koreaguide.model.entity.User;
import com.example.koreaguide.model.enumclass.RegionColorStatus;
import com.example.koreaguide.model.exception.KoreaGuideError;
import com.example.koreaguide.model.exception.KoreaGuideException;
import com.example.koreaguide.model.network.Header;
import com.example.koreaguide.model.network.request.RegionApiRequest;
import com.example.koreaguide.model.network.response.CodeApiResponse;
import com.example.koreaguide.model.network.response.MyWordApiResponse;
import com.example.koreaguide.model.network.response.MyWordListApiResponse;
import com.example.koreaguide.model.network.response.PlaceApiResponse;
import com.example.koreaguide.model.network.response.RegionApiResponse;
import com.example.koreaguide.model.network.response.RegionPlaceApiResponse;
import com.example.koreaguide.repository.CodeRepository;
import com.example.koreaguide.repository.PlaceRepository;
import com.example.koreaguide.repository.RegionColorRepository;
import com.example.koreaguide.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/*
 * @author : Jisoo Kim
 * @date: 2021/03/19 9:26 오후
*/

@Service
public class CodeApiLogicService {
    @Autowired
    CodeRepository codeRepository;

    @Autowired
    RegionColorRepository regionColorRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    PlaceRepository placeRepository;

    public RegionApiResponse getRegionList(Integer id) {
        Optional<User> user = userRepository.findById(id);
        return user.map(selectedUser->response(selectedUser))
                .orElseThrow(()->
                        new KoreaGuideException(KoreaGuideError.ENTITY_NOT_FOUND_USER,"user")
                );
    }

    public RegionApiResponse changeRegionColor(Integer id, Header<RegionApiRequest> request) {
        RegionApiRequest body = request.getData();
        Optional<User> user = userRepository.findById(id);
        // 지금 색을 바꾸려는 지역 받아오기
        User selected=user.map(selectedUser->{
            Code selectedCode = codeRepository.getOne(body.getRegionId());
            if(selectedCode==null){
                throw new KoreaGuideException(KoreaGuideError.ENTITY_NOT_FOUND_REGION);
            }else {
                RegionColor selectedRegion = regionColorRepository.findByAreacodeAndUser(body.getRegionId(), selectedUser);
                // 해당 지역 못찾으면 새로 만들기
                if (selectedRegion == null) {
                    RegionColor newRegionColor = RegionColor.builder()
                            .regionColor(body.getColor())
                            .areacode(body.getRegionId())
                            .user(selectedUser)
                            .build();
                    RegionColor newColor = regionColorRepository.save(newRegionColor);
                } else {
                    selectedRegion.setRegionColor(body.getColor());
                    regionColorRepository.save(selectedRegion);
                }
                return selectedUser;
            }
        }).orElseThrow(()->
                new KoreaGuideException(KoreaGuideError.ENTITY_NOT_FOUND_USER)
        );
        return responseForOneRegion(selected, body.getRegionId());
    }

    private RegionApiResponse responseForOneRegion(User selectedUser, Integer id) {
        //여기서 id = areacode id
        RegionColor selectedRegion =regionColorRepository.findByAreacodeAndUser(id,selectedUser);
        List<CodeApiResponse> codeApiResponseList=new ArrayList<CodeApiResponse>();
        if(selectedRegion==null){
            throw new KoreaGuideException(KoreaGuideError.ENTITY_NOT_FOUND_REGION);
        }else{
            Optional<Code> code = codeRepository.findById(id);
            CodeApiResponse newCode=code.map(selectedCode->{
                CodeApiResponse codeApiResponse = CodeApiResponse.builder()
                        .id(selectedCode.getId())
                        .areacode(selectedCode.getAreacode())
                        .areanameEng(selectedCode.getAreanameEng())
                        .areanameKor(selectedCode.getAreanameKor())
                        .color(selectedRegion.getRegionColor())
                        .build();
                return codeApiResponse;
            }).orElseThrow(()->new KoreaGuideException(KoreaGuideError.ENTITY_NOT_FOUND_REGION));
            codeApiResponseList.add(newCode);
            RegionApiResponse regionApiResponse = RegionApiResponse.builder()
                    .regionList(codeApiResponseList)
                    .build();
            return regionApiResponse;
        }
    }
    public RegionPlaceApiResponse getPlaceListForRegion(Integer userId, Integer id) {
        Optional<User> user = userRepository.findById(userId);
        User userSelected=user.map(selectedUser->{
            Optional<Code> selectedCode = codeRepository.findById(id);
            selectedCode.map(selected->{
                System.out.println("INSIDE!!!");
                RegionColor selectedRegion = regionColorRepository.findByAreacodeAndUser(id, selectedUser);
                // 해당 지역 못찾으면 새로 만들기
                if (selectedRegion == null) {
                    RegionColor newRegionColor = RegionColor.builder()
                            .regionColor(RegionColorStatus.NONE)
                            .user(selectedUser)
                            .areacode(id)
                            .build();
                    RegionColor newColor = regionColorRepository.save(newRegionColor);
                }
                return selectedUser;
            }).orElseThrow(()-> new KoreaGuideException(KoreaGuideError.ENTITY_NOT_FOUND_REGION));
            return selectedUser;
        }).orElseThrow(()->
                new KoreaGuideException(KoreaGuideError.ENTITY_NOT_FOUND_USER)
        );
        return responseForRegionPlaceList(userSelected,id);
    }

    private RegionPlaceApiResponse responseForRegionPlaceList(User userSelected, Integer id) {
        List<Place> placeList = placeRepository.findAllByAreaCode(id);
        RegionColor regionColor = regionColorRepository.findByAreacodeAndUser(id, userSelected);
        RegionColorStatus color;
        if(regionColor==null){
            color = RegionColorStatus.NONE;
        }else{
            color = regionColor.getRegionColor();
        }
        List<PlaceApiResponse> placeApiResponseList=new ArrayList<PlaceApiResponse>();
        if(placeList.size()==0){
            throw new KoreaGuideException(KoreaGuideError.ENTITY_EMPTY_PLACE);
        }else {
            for (int i = 0; i < placeList.size(); i++) {
                PlaceApiResponse placeApiResponse = PlaceApiResponse.builder()
                        .id(placeList.get(i).getId())
                        .contentId(placeList.get(i).getContentId())
                        .title(placeList.get(i).getTitle())
                        .address1(placeList.get(i).getAddress1())
                        .mapX(placeList.get(i).getMapX())
                        .mapY(placeList.get(i).getMapY())
                        .firstImage(placeList.get(i).getFirstImage())
                        .firstImage2(placeList.get(i).getFirstImage2())
                        .overview(placeList.get(i).getOverview())
                        .build();
                placeApiResponseList.add(placeApiResponse);
            }
        }
        RegionPlaceApiResponse regionPlaceApiResponse=RegionPlaceApiResponse.builder()
                .userId(userSelected.getId())
                .regionId(id)
                .regionColor(color)
                .placeList(placeApiResponseList).build();
        return regionPlaceApiResponse;
    }


    private RegionApiResponse response(User user){
        // 사용자에 대한 지역리스트 받아오기 -- 특정 지역없으면 객체 만들기
        List<Code> codeList = codeRepository.findAll();
        System.out.println("codeList: "+codeList);
        List<CodeApiResponse> codeApiResponseList=new ArrayList<CodeApiResponse>();
        if(codeList.isEmpty()){
            throw new KoreaGuideException(KoreaGuideError.ENTITY_EMPTY_CODE);
        }else{
            System.out.println("NOT EMPTY!!");
            for(int i=0;i<codeList.size();i++){
                System.out.println("i is: "+i);
                RegionColor color = regionColorRepository.findByAreacodeAndUser(codeList.get(i).getAreacode(),user);
                if(color==null){
                    RegionColor newRegionColor = RegionColor.builder()
                            .regionColor(RegionColorStatus.NONE)
                            .areacode(codeList.get(i).getAreacode())
                            .user(user)
                            .build();
                    RegionColor newColor = regionColorRepository.save(newRegionColor);
                    CodeApiResponse codeApiResponse = CodeApiResponse.builder()
                            .id(codeList.get(i).getId())
                            .areacode(codeList.get(i).getAreacode())
                            .areanameEng(codeList.get(i).getAreanameEng())
                            .areanameKor(codeList.get(i).getAreanameKor())
                            .color(newColor.getRegionColor())
                            .build();
                    codeApiResponseList.add(codeApiResponse);
                }
                else {
                    CodeApiResponse codeApiResponse = CodeApiResponse.builder()
                            .id(codeList.get(i).getId())
                            .areacode(codeList.get(i).getAreacode())
                            .areanameEng(codeList.get(i).getAreanameEng())
                            .areanameKor(codeList.get(i).getAreanameKor())
                            .color(color.getRegionColor())
                            .build();
                    codeApiResponseList.add(codeApiResponse);
                }
            }
        }
        RegionApiResponse regionApiResponse = RegionApiResponse.builder()
                .regionList(codeApiResponseList)
                .build();
        return regionApiResponse;
    }

}

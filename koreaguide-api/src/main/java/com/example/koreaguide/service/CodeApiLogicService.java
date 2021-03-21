package com.example.koreaguide.service;

import com.example.koreaguide.model.entity.Code;
import com.example.koreaguide.model.entity.MyWord;
import com.example.koreaguide.model.entity.RegionColor;
import com.example.koreaguide.model.entity.User;
import com.example.koreaguide.model.enumclass.RegionColorStatus;
import com.example.koreaguide.model.exception.KoreaGuideError;
import com.example.koreaguide.model.exception.KoreaGuideException;
import com.example.koreaguide.model.network.response.CodeApiResponse;
import com.example.koreaguide.model.network.response.MyWordApiResponse;
import com.example.koreaguide.model.network.response.MyWordListApiResponse;
import com.example.koreaguide.model.network.response.RegionApiResponse;
import com.example.koreaguide.repository.CodeRepository;
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

    public RegionApiResponse getRegionList(Integer id) {
        Optional<User> user = userRepository.findById(id);
        return user.map(selectedUser->response(selectedUser))
                .orElseThrow(()->
                        new KoreaGuideException(KoreaGuideError.ENTITY_NOT_FOUND_USER,"user")
                );
    }

    private RegionApiResponse response(User user){
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

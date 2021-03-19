package com.example.koreaguide.service;

import com.example.koreaguide.model.entity.Code;
import com.example.koreaguide.model.entity.MyWord;
import com.example.koreaguide.model.exception.KoreaGuideError;
import com.example.koreaguide.model.exception.KoreaGuideException;
import com.example.koreaguide.model.network.response.CodeApiResponse;
import com.example.koreaguide.model.network.response.MyWordApiResponse;
import com.example.koreaguide.model.network.response.MyWordListApiResponse;
import com.example.koreaguide.model.network.response.RegionApiResponse;
import com.example.koreaguide.repository.CodeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/*
 * @author : Jisoo Kim
 * @date: 2021/03/19 9:26 오후
*/

@Service
public class CodeApiLogicService {
    @Autowired
    CodeRepository codeRepository;

    public RegionApiResponse getRegionList() {
        return response();
//        List<Code> codeList = codeRepository.findAll();
//        System.out.println("codeList"+codeList);
//        List<CodeApiResponse> codeApiResponseList=new ArrayList<CodeApiResponse>();
//        if(codeApiResponseList.isEmpty()){
//            throw new KoreaGuideException(KoreaGuideError.ENTITY_EMPTY_CODE);
//        }else{
//            for(int i=0;i<codeList.size();i++){
//                codeApiResponseList.add(makeToCodeApiResponse(codeList.get(i)));
//            }
//        }
//        System.out.println("CODE API RESPONSE LIST: "+codeApiResponseList);
//        return makeToRegionApiResponse(codeApiResponseList);
    }

    private RegionApiResponse response(){
        List<Code> codeList = codeRepository.findAll();
        System.out.println("codeList: "+codeList);
        List<CodeApiResponse> codeApiResponseList=new ArrayList<CodeApiResponse>();
        if(codeList.isEmpty()){
            throw new KoreaGuideException(KoreaGuideError.ENTITY_EMPTY_CODE);
        }else{
            System.out.println("NOT EMPTY!!");
            for(int i=0;i<codeList.size();i++){
                System.out.println("i is: "+i);
                CodeApiResponse codeApiResponse = CodeApiResponse.builder()
                        .id(codeList.get(i).getId())
                        .areacode(codeList.get(i).getAreacode())
                        .areanameEng(codeList.get(i).getAreanameEng())
                        .areanameKor(codeList.get(i).getAreanameKor())
                        .build();
                codeApiResponseList.add(codeApiResponse);
            }
        }
        RegionApiResponse regionApiResponse = RegionApiResponse.builder()
                .regionList(codeApiResponseList)
                .build();
        return regionApiResponse;
    }



}

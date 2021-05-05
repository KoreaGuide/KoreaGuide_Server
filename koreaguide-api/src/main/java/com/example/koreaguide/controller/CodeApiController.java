package com.example.koreaguide.controller;

import com.example.koreaguide.model.enumclass.UserLevel;
import com.example.koreaguide.model.exception.GlobalExceptionHandler;
import com.example.koreaguide.model.exception.KoreaGuideError;
import com.example.koreaguide.model.exception.KoreaGuideException;
import com.example.koreaguide.model.network.Header;
import com.example.koreaguide.model.network.request.RegionApiRequest;
import com.example.koreaguide.model.network.response.CodeApiResponse;
import com.example.koreaguide.model.network.response.HomeApiResponse;
import com.example.koreaguide.model.network.response.RegionApiResponse;
import com.example.koreaguide.model.network.response.RegionPlaceApiResponse;
import com.example.koreaguide.repository.UserRepository;
import com.example.koreaguide.service.CodeApiLogicService;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/*
 * @author : Jisoo Kim
 * @date: 2021/03/19 9:31 오후
*/
@Slf4j
@RestController
@RequestMapping("/api/place/")
public class CodeApiController extends GlobalExceptionHandler {
    @Autowired
    CodeApiLogicService codeApiLogicService;

    @Autowired
    private UserRepository userRepository;

    // id = userId
    @GetMapping("/regionList/{id}")
    public Header<RegionApiResponse> getRegionList(
            @PathVariable(name = "id") Integer id) {
//        try {
//            Claims claims = (Claims) authentication.getPrincipal();
//            Integer userId = claims.get("userId", Integer.class);
//            System.out.println("USER ID: "+userId);
//            if(id!=userId){
//                throw new KoreaGuideException(KoreaGuideError.NOT_LOGIN,"Invalid Authentication");
//            }
//        }catch (Exception e){
//            throw new KoreaGuideException(KoreaGuideError.NOT_LOGIN,"Invalid Authentication");
//        }

        RegionApiResponse regionApiResponse =codeApiLogicService.getRegionList(id);
        return new Header<>(regionApiResponse);
    }

    @PatchMapping("/regionList/{id}")
    public Header<RegionApiResponse> changeRegionColor(
            @PathVariable(name = "id") Integer id,
            @RequestBody Header<RegionApiRequest> request) {
//        try {
//            Claims claims = (Claims) authentication.getPrincipal();
//            Integer userId = claims.get("userId", Integer.class);
//            System.out.println("USER ID: "+userId);
//            if(id!=userId){
//                throw new KoreaGuideException(KoreaGuideError.NOT_LOGIN,"Invalid Authentication");
//            }
//        }catch (Exception e){
//            throw new KoreaGuideException(KoreaGuideError.NOT_LOGIN,"Invalid Authentication");
//        }

        RegionApiResponse regionApiResponse =codeApiLogicService.changeRegionColor(id,request);
        return new Header<>(regionApiResponse);
    }

    //id=regionid
    @GetMapping("/region/{userId}/{id}")
    public Header<RegionPlaceApiResponse> getPlaceListForRegion(
            @PathVariable(name = "userId") Integer userId,
            @PathVariable(name = "id") Integer id) {
//        Integer userId;
//        try {
//            Claims claims = (Claims) authentication.getPrincipal();
//            userId = claims.get("userId", Integer.class);
//            System.out.println("USER ID: "+userId);
//            if(userRepository.findById(userId).isEmpty()){
//                throw new KoreaGuideException(KoreaGuideError.NOT_LOGIN,"Invalid Authentication");
//            }
//        }catch (Exception e){
//            throw new KoreaGuideException(KoreaGuideError.NOT_LOGIN,"Invalid Authentication");
//        }

        RegionPlaceApiResponse regionPlaceApiResponse =codeApiLogicService.getPlaceListForRegion(userId,id);
        return new Header<>(regionPlaceApiResponse);
    }
}

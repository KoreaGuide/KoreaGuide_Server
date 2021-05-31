package com.example.koreaguide.controller;

import com.example.koreaguide.model.exception.GlobalExceptionHandler;
import com.example.koreaguide.model.exception.KoreaGuideError;
import com.example.koreaguide.model.exception.KoreaGuideException;
import com.example.koreaguide.model.network.Header;
import com.example.koreaguide.model.network.response.PlaceApiResponse;
import com.example.koreaguide.model.network.response.PlaceDetailApiResponse;
import com.example.koreaguide.model.network.response.PlaceDetailHeadApiResponse;
import com.example.koreaguide.model.network.response.RegionApiResponse;
import com.example.koreaguide.repository.UserRepository;
import com.example.koreaguide.service.PlaceApiLogicService;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/*
 * @author : Jisoo Kim
 * @date: 2021/03/22 1:00 오후
*/
@Slf4j
@RestController
@RequestMapping("/api/place/")
public class PlaceApiController extends GlobalExceptionHandler {
    @Autowired
    PlaceApiLogicService placeApiLogicService;

    @Autowired
    UserRepository userRepository;
    //id= place id
    @GetMapping("/detail/{userId}/{id}")
    public Header<PlaceDetailApiResponse> getPlaceDetailAll(
            Authentication authentication,
            @PathVariable(name = "userId") Integer userId,
            @PathVariable(name = "id") Integer id) {
        SessionController.checkJWT(authentication,userRepository);

        PlaceDetailApiResponse placeDetailApiResponse =placeApiLogicService.getPlaceDetailAll(userId,id);
        return new Header<>(placeDetailApiResponse);
    }

    @GetMapping("/detail/eng/{userId}/{id}")
    public Header<PlaceDetailApiResponse> getPlaceDetailEng(
            Authentication authentication,
            @PathVariable(name = "userId") Integer userId,
            @PathVariable(name = "id") Integer id) {
        SessionController.checkJWT(authentication,userRepository);

        PlaceDetailApiResponse placeDetailApiResponse =placeApiLogicService.getPlaceDetailEng(userId,id);
        return new Header<>(placeDetailApiResponse);
    }

    @GetMapping("/detail/kor/{userId}/{id}")
    public Header<PlaceDetailApiResponse> getPlaceDetailKor(
            Authentication authentication,
            @PathVariable(name = "userId") Integer userId,
            @PathVariable(name = "id") Integer id) {
        SessionController.checkJWT(authentication,userRepository);

        PlaceDetailApiResponse placeDetailApiResponse =placeApiLogicService.getPlaceDetailKor(userId,id);
        return new Header<>(placeDetailApiResponse);
    }

    //id = place_id
    @GetMapping("/word/{userId}/{id}")
    public Header<PlaceDetailHeadApiResponse> getPlaceWord(
            Authentication authentication,
            @PathVariable(name = "userId") Integer userId,
            @PathVariable(name = "id") Integer id,
            @RequestParam(value = "page",defaultValue = "1")Integer pageNumber) {
        SessionController.checkJWT(authentication,userRepository);

        PlaceDetailHeadApiResponse placeDetailHeadApiResponse =placeApiLogicService.getWord(userId,id,pageNumber);
        return new Header<>(placeDetailHeadApiResponse);
    }

    @GetMapping("/wordList/{userId}/{id}")
    public Header<PlaceDetailHeadApiResponse> getPlaceWordList(
            Authentication authentication,
            @PathVariable(name = "userId") Integer userId,
            @PathVariable(name = "id") Integer id) {
//        Integer userId;
        SessionController.checkJWT(authentication,userRepository);


        PlaceDetailHeadApiResponse placeDetailHeadApiResponse =placeApiLogicService.getWordWithoutPagination(userId,id);
        return new Header<>(placeDetailHeadApiResponse);
    }

}

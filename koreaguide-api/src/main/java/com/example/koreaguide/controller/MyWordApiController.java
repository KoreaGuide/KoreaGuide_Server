package com.example.koreaguide.controller;

import com.example.koreaguide.model.enumclass.UserLevel;
import com.example.koreaguide.model.exception.GlobalExceptionHandler;
import com.example.koreaguide.model.exception.KoreaGuideError;
import com.example.koreaguide.model.exception.KoreaGuideException;
import com.example.koreaguide.model.network.Header;
import com.example.koreaguide.model.network.request.MyWordApiRequest;
import com.example.koreaguide.model.network.request.UserApiRequest;
import com.example.koreaguide.model.network.response.HomeApiResponse;
import com.example.koreaguide.model.network.response.MyWordApiResponse;
import com.example.koreaguide.model.network.response.UserApiResponse;
import com.example.koreaguide.model.network.response.WordApiResponse;
import com.example.koreaguide.repository.UserRepository;
import com.example.koreaguide.service.MyWordApiLogicService;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/*
 * @author : Jisoo Kim
 * @date: 2021/03/15 8:27 오후
*/
@Slf4j
@RestController
@RequestMapping("/api/myWord")
public class MyWordApiController extends GlobalExceptionHandler {
    @Autowired
    private MyWordApiLogicService myWordApiLogicService;

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/{id}")
    public Header<MyWordApiResponse> addWordToMyWordlist(
//            Authentication authentication,
            @PathVariable(name = "id") Integer id,
            @RequestBody Header<MyWordApiRequest> request) {
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

        MyWordApiResponse myWordApiResponse =myWordApiLogicService.addWordToMyWordList(id,request);
        return new Header<>(myWordApiResponse,HttpStatus.OK,"Successfully added");
    }

    @GetMapping("/{id}")
    public Header<MyWordApiResponse> getMyWordList(
//            Authentication authentication,
            @PathVariable(name = "id") Integer id,
            @RequestBody Header<MyWordApiRequest> request) {
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
        System.out.println("USER ID: "+id);
        MyWordApiResponse myWordApiResponse =myWordApiLogicService.getMyWordList(id,request);
        return new Header<>(myWordApiResponse);
    }


    @DeleteMapping("/{id}")
    public Header<MyWordApiResponse> deleteMyWord(
//            Authentication authentication,
            @RequestBody Header<MyWordApiRequest> request) {
//        try {
//            Claims claims = (Claims) authentication.getPrincipal();
//            Integer userId = claims.get("userId", Integer.class);
//            System.out.println("USER ID: "+userId);
//            if(userRepository.findById(userId).isEmpty()){
//                throw new KoreaGuideException(KoreaGuideError.NOT_LOGIN,"Invalid Authentication");
//            }
//        }catch (Exception e){
//            throw new KoreaGuideException(KoreaGuideError.NOT_LOGIN,"Invalid Authentication");
//        }

        MyWordApiResponse myWordApiResponse =myWordApiLogicService.deleteMyWord(request);
        return new Header<>(myWordApiResponse);
    }


}

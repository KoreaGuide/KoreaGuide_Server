package com.example.koreaguide.controller;

import com.example.koreaguide.model.exception.GlobalExceptionHandler;
import com.example.koreaguide.model.exception.KoreaGuideError;
import com.example.koreaguide.model.exception.KoreaGuideException;
import com.example.koreaguide.model.network.Header;
import com.example.koreaguide.model.network.request.MyWordApiRequest;
import com.example.koreaguide.model.network.request.MyWordFolderApiRequest;
import com.example.koreaguide.model.network.request.UserApiRequest;
import com.example.koreaguide.model.network.response.LearnWordApiResponse;
import com.example.koreaguide.model.network.response.MyWordApiResponse;
import com.example.koreaguide.model.network.response.MyWordFolderApiResponse;
import com.example.koreaguide.model.network.response.PlaceDetailHeadApiResponse;
import com.example.koreaguide.model.network.response.UserApiResponse;
import com.example.koreaguide.repository.UserRepository;
import com.example.koreaguide.service.MyWordApiLogicService;
import com.example.koreaguide.service.MyWordFolderApiLogicService;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/*
 * @author : Jisoo Kim
 * @date: 2021/03/21 7:49 오전
*/
@Slf4j
@RestController
@RequestMapping("/api/myWordFolder")
public class MyWordFolderApiController extends GlobalExceptionHandler {
    @Autowired
    private MyWordFolderApiLogicService myWordFolderApiLogicService;

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/")
    public Header<MyWordFolderApiResponse> createWordFolder(
//            Authentication authentication,
            @RequestBody Header<MyWordFolderApiResponse> request) {
//        try {
//            Claims claims = (Claims) authentication.getPrincipal();
//            Integer userId = claims.get("userId", Integer.class);
//            System.out.println("USER ID: "+userId);
//            System.out.println("USER ID: "+request.getData().getUserId());
//            if(request.getData().getUserId()!=userId){
//                throw new KoreaGuideException(KoreaGuideError.NOT_LOGIN,"Invalid Authentication");
//            }
//        }catch (Exception e){
//            throw new KoreaGuideException(KoreaGuideError.NOT_LOGIN,"Invalid Authentication");
//        }

        MyWordFolderApiResponse myWordFolderApiResponse =myWordFolderApiLogicService.create(request);
        return new Header<>(myWordFolderApiResponse, HttpStatus.CREATED,"Successfully created");
    }

    // 유저 아이디
    @GetMapping("/{id}")
    public Header<List<MyWordFolderApiResponse>> getAllFolderList(
//            Authentication authentication,
            @PathVariable(name = "id") Integer id) {
//        try{
//            Claims claims = (Claims) authentication.getPrincipal();
//            Integer userId = claims.get("userId",Integer.class);
//            System.out.println("USER ID: "+userId);
//            if(id!=userId){
//                throw new KoreaGuideException(KoreaGuideError.NOT_LOGIN,"Invalid Authentication");
//            }
//        }catch (Exception e){
//            throw new KoreaGuideException(KoreaGuideError.NOT_LOGIN,"Invalid Authentication");
//        }

        List<MyWordFolderApiResponse> myWordFolderApiResponseList =myWordFolderApiLogicService.getAllFolderList(id);
        return new Header<>(myWordFolderApiResponseList);
    }

    // 유저 아이디
    @DeleteMapping("/{id}")
    public Header<MyWordFolderApiResponse> deleteOneFolder(
//            Authentication authentication,
            @PathVariable(name = "id") Integer id,
            @RequestBody Header<MyWordFolderApiRequest> request) {
//        try{
//            Claims claims = (Claims) authentication.getPrincipal();
//            Integer userId = claims.get("userId",Integer.class);
//            System.out.println("USER ID: "+userId);
//            if(id!=userId){
//                throw new KoreaGuideException(KoreaGuideError.NOT_LOGIN,"Invalid Authentication");
//            }
//        }catch (Exception e){
//            throw new KoreaGuideException(KoreaGuideError.NOT_LOGIN,"Invalid Authentication");
//        }

        MyWordFolderApiResponse myWordFolderApiResponse =myWordFolderApiLogicService.deleteOneFolder(id,request);
        return new Header<>(myWordFolderApiResponse);
    }

    @PatchMapping("/{id}")
    public Header<MyWordFolderApiResponse> updateFolderName(
//            Authentication authentication,
            @PathVariable(name = "id") Integer id,
            @RequestBody Header<MyWordFolderApiRequest> request) {
//        try{
//            Claims claims = (Claims) authentication.getPrincipal();
//            Integer userId = claims.get("userId",Integer.class);
//            System.out.println("USER ID: "+userId);
//            if(id!=userId){
//                throw new KoreaGuideException(KoreaGuideError.NOT_LOGIN,"Invalid Authentication");
//            }
//        }catch (Exception e){
//            throw new KoreaGuideException(KoreaGuideError.NOT_LOGIN,"Invalid Authentication");
//        }
        MyWordFolderApiResponse myWordFolderApiResponse =myWordFolderApiLogicService.updateFolderName(id,request);
        return new Header<>(myWordFolderApiResponse);
    }

    //유저의 아이디
    @GetMapping("/learn/{id}")
    public Header<LearnWordApiResponse> getWordforLearning(
//            Authentication authentication,
            @PathVariable(name = "id") Integer id,
            @RequestBody Header<MyWordFolderApiRequest> request,
            @RequestParam(value = "page",defaultValue = "1")Integer pageNumber) {
        Integer userId;
//        try {
//            Claims claims = (Claims) authentication.getPrincipal();
//            userId = claims.get("userId", Integer.class);
//            System.out.println("USER ID: "+userId);
//            if(id!=userId){
//                throw new KoreaGuideException(KoreaGuideError.NOT_LOGIN,"Invalid Authentication");
//            }
//        }catch (Exception e){
//            throw new KoreaGuideException(KoreaGuideError.NOT_LOGIN,"Invalid Authentication");
//        }

        LearnWordApiResponse learnWordApiResponse =myWordFolderApiLogicService.getWordforLearning(id,request,pageNumber);
        return new Header<>(learnWordApiResponse);
    }

    @GetMapping("/learnWord/{id}/{folderId}")
    public Header<LearnWordApiResponse> getWordListforLearning(
//            Authentication authentication,
            @PathVariable(name = "id") Integer id,
    @PathVariable(name = "folderId") Integer folderId){
//            @RequestBody Header<MyWordFolderApiRequest> request) {
        Integer userId;
//        try {
//            Claims claims = (Claims) authentication.getPrincipal();
//            userId = claims.get("userId", Integer.class);
//            System.out.println("USER ID: "+userId);
//            if(id!=userId){
//                throw new KoreaGuideException(KoreaGuideError.NOT_LOGIN,"Invalid Authentication");
//            }
//        }catch (Exception e){
//            throw new KoreaGuideException(KoreaGuideError.NOT_LOGIN,"Invalid Authentication");
//        }

        LearnWordApiResponse learnWordApiResponse =myWordFolderApiLogicService.getWordListforLearning(id,folderId);
        return new Header<>(learnWordApiResponse);
    }
}

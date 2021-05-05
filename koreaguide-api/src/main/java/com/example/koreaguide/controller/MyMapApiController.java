package com.example.koreaguide.controller;

import com.example.koreaguide.model.exception.GlobalExceptionHandler;
import com.example.koreaguide.model.exception.KoreaGuideError;
import com.example.koreaguide.model.exception.KoreaGuideException;
import com.example.koreaguide.model.network.Header;
import com.example.koreaguide.model.network.request.MyMapApiRequest;
import com.example.koreaguide.model.network.response.HomeApiResponse;
import com.example.koreaguide.model.network.response.MyMapApiResponse;
import com.example.koreaguide.repository.UserRepository;
import com.example.koreaguide.service.MyMapApiLogicService;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/myMap")
public class MyMapApiController extends GlobalExceptionHandler {
    @Autowired
    MyMapApiLogicService myMapApiLogicService;

    @Autowired
    UserRepository userRepository;

    @PostMapping("/{id}")
    public Header<MyMapApiResponse> addToMyMap(
            @PathVariable(name = "id") Integer id,
            @RequestBody Header<MyMapApiRequest> request) {
//        try {
//            Claims claims = (Claims) authentication.getPrincipal();
//            Integer userId = claims.get("userId", Integer.class);
//            System.out.println("USER ID: "+userId);
//            if(userId!=id){
//                throw new KoreaGuideException(KoreaGuideError.NOT_LOGIN,"Invalid Authentication");
//            }
//        }catch (Exception e){
//            throw new KoreaGuideException(KoreaGuideError.NOT_LOGIN,"Invalid Authentication");
//        }

        MyMapApiResponse myMapApiResponse =myMapApiLogicService.addToMyMap(id,request);
        return new Header<>(myMapApiResponse);
    }

    @GetMapping("/all/{id}")
    public Header<MyMapApiResponse> getMyMapAll(
//            Authentication authentication,
            @PathVariable(name = "id") Integer id) {
//        try {
//            Claims claims = (Claims) authentication.getPrincipal();
//            Integer userId = claims.get("userId", Integer.class);
//            System.out.println("USER ID: "+userId);
//            if(userId!=id){
//                throw new KoreaGuideException(KoreaGuideError.NOT_LOGIN,"Invalid Authentication");
//            }
//        }catch (Exception e){
//            throw new KoreaGuideException(KoreaGuideError.NOT_LOGIN,"Invalid Authentication");
//        }

        MyMapApiResponse myMapApiResponse =myMapApiLogicService.getMyMapAll(id);
        return new Header<>(myMapApiResponse);
    }

    @GetMapping("/wish/{id}")
    public Header<MyMapApiResponse> getMyMapWish(
//            Authentication authentication,
            @PathVariable(name = "id") Integer id) {
//        try {
//            Claims claims = (Claims) authentication.getPrincipal();
//            Integer userId = claims.get("userId", Integer.class);
//            System.out.println("USER ID: "+userId);
//            if(userId!=id){
//                throw new KoreaGuideException(KoreaGuideError.NOT_LOGIN,"Invalid Authentication");
//            }
//        }catch (Exception e){
//            throw new KoreaGuideException(KoreaGuideError.NOT_LOGIN,"Invalid Authentication");
//        }

        MyMapApiResponse myMapApiResponse =myMapApiLogicService.getMyMapWish(id);
        return new Header<>(myMapApiResponse);
    }

    @GetMapping("/haveBeen/{id}")
    public Header<MyMapApiResponse> getMyMapHaveBeenTo(
//            Authentication authentication,
            @PathVariable(name = "id") Integer id) {
//        try {
//            Claims claims = (Claims) authentication.getPrincipal();
//            Integer userId = claims.get("userId", Integer.class);
//            System.out.println("USER ID: "+userId);
//            if(userId!=id){
//                throw new KoreaGuideException(KoreaGuideError.NOT_LOGIN,"Invalid Authentication");
//            }
//        }catch (Exception e){
//            throw new KoreaGuideException(KoreaGuideError.NOT_LOGIN,"Invalid Authentication");
//        }

        MyMapApiResponse myMapApiResponse =myMapApiLogicService.getMyMapHaveBeenTo(id);
        return new Header<>(myMapApiResponse);
    }

    @DeleteMapping("/{id}")
    public Header<MyMapApiResponse> DeleteMyMap(
//            Authentication authentication,
            @PathVariable(name = "id") Integer id,
            @RequestBody Header<MyMapApiRequest> request) {
//        try {
//            Claims claims = (Claims) authentication.getPrincipal();
//            Integer userId = claims.get("userId", Integer.class);
//            System.out.println("USER ID: "+userId);
//            if(userId!=id){
//                throw new KoreaGuideException(KoreaGuideError.NOT_LOGIN,"Invalid Authentication");
//            }
//        }catch (Exception e){
//            throw new KoreaGuideException(KoreaGuideError.NOT_LOGIN,"Invalid Authentication");
//        }

        MyMapApiResponse myMapApiResponse =myMapApiLogicService.deleteMyMap(id,request);
        return new Header<>(myMapApiResponse);
    }

    @PatchMapping("/{id}")
    public Header<MyMapApiResponse> changeMyMap(
//            Authentication authentication,
            @PathVariable(name = "id") Integer id,
            @RequestBody Header<MyMapApiRequest> request) {
//        try {
//            Claims claims = (Claims) authentication.getPrincipal();
//            Integer userId = claims.get("userId", Integer.class);
//            System.out.println("USER ID: "+userId);
//            if(userId!=id){
//                throw new KoreaGuideException(KoreaGuideError.NOT_LOGIN,"Invalid Authentication");
//            }
//        }catch (Exception e){
//            throw new KoreaGuideException(KoreaGuideError.NOT_LOGIN,"Invalid Authentication");
//        }

        MyMapApiResponse myMapApiResponse =myMapApiLogicService.changeMyMap(id,request);
        return new Header<>(myMapApiResponse);
    }
}

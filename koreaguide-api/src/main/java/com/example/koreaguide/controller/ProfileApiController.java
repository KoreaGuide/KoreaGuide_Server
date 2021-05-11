//package com.example.koreaguide.controller;
//
//import com.example.koreaguide.model.exception.GlobalExceptionHandler;
//import com.example.koreaguide.model.exception.KoreaGuideError;
//import com.example.koreaguide.model.network.Header;
//import com.example.koreaguide.model.network.response.HomeApiResponse;
//import com.example.koreaguide.model.network.response.ProfileApiResponse;
//import com.example.koreaguide.service.ProfileApiLogicService;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
///*
// * @author : Jisoo Kim
// * @date: 2021/05/10 4:10 오후
//*/
//@Slf4j
//@RestController
//@RequestMapping("/api/profile/")
//public class ProfileApiController extends GlobalExceptionHandler {
//    @Autowired
//    ProfileApiLogicService profileApiLogicService;
//
//    //user_id
//    @GetMapping("id")
//    public Header<ProfileApiResponse> getHomeInfo(
//            @PathVariable(name = "id") Integer id) {
////        try {
////            Claims claims = (Claims) authentication.getPrincipal();
////            Integer userId = claims.get("userId", Integer.class);
////            System.out.println("USER ID: "+userId);
////            if(userRepository.findById(userId).isEmpty()){
////                throw new KoreaGuideException(KoreaGuideError.NOT_LOGIN,"Invalid Authentication");
////            }
////        }catch (Exception e){
////            throw new KoreaGuideException(KoreaGuideError.NOT_LOGIN,"Invalid Authentication");
////        }
//
//        ProfileApiResponse profileApiResponse =profileApiLogicService.getProfileWord(id);
//        return new Header<>(profileApiResponse);
//    }
//
//}

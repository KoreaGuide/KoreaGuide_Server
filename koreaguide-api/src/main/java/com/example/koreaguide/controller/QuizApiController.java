package com.example.koreaguide.controller;

import com.example.koreaguide.model.exception.GlobalExceptionHandler;
import com.example.koreaguide.model.exception.KoreaGuideError;
import com.example.koreaguide.model.exception.KoreaGuideException;
import com.example.koreaguide.model.network.Header;
import com.example.koreaguide.model.network.request.MyWordApiRequest;
import com.example.koreaguide.model.network.request.QuizListApiRequest;
import com.example.koreaguide.model.network.request.QuizResultApiRequest;
import com.example.koreaguide.model.network.response.MyWordApiResponse;
import com.example.koreaguide.model.network.response.QuizListApiResponse;
import com.example.koreaguide.repository.UserRepository;
import com.example.koreaguide.service.QuizApiLogicService;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/*
 * @author : Jisoo Kim
 * @date: 2021/04/28 8:11 오후
*/
@Slf4j
@RestController
@RequestMapping("/api/quiz/")
public class QuizApiController extends GlobalExceptionHandler {
    @Autowired
    private QuizApiLogicService quizApiLogicService;
    @Autowired
    private UserRepository userRepository;
    //id = userid
    @PostMapping("/{id}")
    public Header<QuizListApiResponse> getMyWordList(
//            Authentication authentication,
            @PathVariable(name = "id") Integer id,
            @RequestBody Header<QuizListApiRequest> request) {
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
        QuizListApiResponse quizListApiResponse =quizApiLogicService.getQuizWordList(id,request);
        return new Header<>(quizListApiResponse);
    }

    @PostMapping("/result/{id}")
    public Header postTestResult(
//            Authentication authentication,
            @PathVariable(name = "id") Integer id,
            @RequestBody Header<QuizResultApiRequest> request
    ){
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
        Header header =quizApiLogicService.postTestResult(id,request);
        return header;
    }

    @PostMapping("/oneResult/{id}")
    public Header postOneTestResult(
//            Authentication authentication,
            @PathVariable(name = "id") Integer id,
            @RequestBody Header<QuizResultApiRequest> request
    ){
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
        Header header =quizApiLogicService.postOneTestResult(id,request);
        return header;
    }
}

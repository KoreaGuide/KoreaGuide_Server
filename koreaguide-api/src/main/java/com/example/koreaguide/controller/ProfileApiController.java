package com.example.koreaguide.controller;

import com.example.koreaguide.model.exception.GlobalExceptionHandler;
import com.example.koreaguide.model.exception.KoreaGuideError;
import com.example.koreaguide.model.exception.KoreaGuideException;
import com.example.koreaguide.model.network.Header;
import com.example.koreaguide.model.network.response.HomeApiResponse;
import com.example.koreaguide.model.network.response.ProfileApiResponse;
import com.example.koreaguide.repository.UserRepository;
import com.example.koreaguide.service.ProfileApiLogicService;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/*
 * @author : Jisoo Kim
 * @date: 2021/05/10 4:10 오후
*/
@Slf4j
@RestController
@RequestMapping("/api/profile/")
public class ProfileApiController extends GlobalExceptionHandler {
    @Autowired
    ProfileApiLogicService profileApiLogicService;

    @Autowired
    UserRepository userRepository;

    //user_id
    @GetMapping("{id}")
    public Header<ProfileApiResponse> getHomeInfo(
            Authentication authentication,
            @PathVariable(name = "id") Integer id) {
        SessionController.checkJWT(authentication,userRepository);

        ProfileApiResponse profileApiResponse =profileApiLogicService.getProfileWord(id);
        return new Header<>(profileApiResponse);
    }

}

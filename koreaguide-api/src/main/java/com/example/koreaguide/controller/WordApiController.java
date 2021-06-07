package com.example.koreaguide.controller;

import com.example.koreaguide.model.exception.GlobalExceptionHandler;
import com.example.koreaguide.model.exception.KoreaGuideError;
import com.example.koreaguide.model.exception.KoreaGuideException;
import com.example.koreaguide.model.network.Header;
import com.example.koreaguide.model.network.request.UserApiRequest;
import com.example.koreaguide.model.network.request.WordApiRequest;
import com.example.koreaguide.model.network.response.HomeApiResponse;
import com.example.koreaguide.model.network.response.WordApiResponse;
import com.example.koreaguide.repository.UserRepository;
import com.example.koreaguide.service.WordApiLogicService;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/word")
public class WordApiController extends GlobalExceptionHandler {
    @Autowired
    WordApiLogicService wordApiLogicService;

    @Autowired
    UserRepository userRepository;

    @GetMapping("/{wordId}")
    public Header<WordApiResponse> getWordInfo(
            Authentication authentication,
            @PathVariable(name = "wordId") Integer wordId) {
        SessionController.checkJWT(authentication,userRepository);

        WordApiResponse wordApiResponse =wordApiLogicService.getWordInfo(wordId);
        return new Header<>(wordApiResponse);
    }
}

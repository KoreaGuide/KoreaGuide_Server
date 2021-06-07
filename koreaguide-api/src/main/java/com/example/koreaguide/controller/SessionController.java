package com.example.koreaguide.controller;

import com.example.koreaguide.model.exception.KoreaGuideError;
import com.example.koreaguide.model.exception.KoreaGuideException;
import com.example.koreaguide.model.network.Header;
import com.example.koreaguide.model.network.request.SessionRequestDto;
import com.example.koreaguide.model.network.response.SessionResponseDto;
import com.example.koreaguide.repository.UserRepository;
import com.example.koreaguide.service.UserApiLogicService;
import com.example.koreaguide.utils.JwtUtil;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SessionController {
    @Autowired
    private UserApiLogicService userApiLogicService;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private static UserRepository userRepository;

    @PostMapping("/session")
    public Header<SessionResponseDto> create(@RequestBody  Header<SessionRequestDto> request){
        String accessToken = "accessToken";
        SessionResponseDto sessionDto = SessionResponseDto.builder()
                .accessToken(accessToken)
                .build();
        return userApiLogicService.authenticate(request);
    }
    public static void checkJWT(
            Authentication authentication,
            Integer id,
            UserRepository userRepository){
        try{
            Claims claims = (Claims) authentication.getPrincipal();
            Integer userId = claims.get("userId",Integer.class);
            System.out.println("USER ID: "+userId);
            if(id!=userId){
                throw new KoreaGuideException(KoreaGuideError.NOT_LOGIN,"Invalid Authentication");
            }
        }catch (Exception e){
            throw new KoreaGuideException(KoreaGuideError.NOT_LOGIN,"Invalid Authentication");
        }
    }

    public static void checkJWT(
            Authentication authentication,
            UserRepository userRepository){
        try {
            Claims claims = (Claims) authentication.getPrincipal();
            Integer userId = claims.get("userId", Integer.class);
            System.out.println("USER ID: "+userId);
            if(userRepository.findById(userId).isEmpty()){
                throw new KoreaGuideException(KoreaGuideError.NOT_LOGIN,"Invalid Authentication");
            }
        }catch (Exception e){
            throw new KoreaGuideException(KoreaGuideError.NOT_LOGIN,"Invalid Authentication");
        }
    }
}

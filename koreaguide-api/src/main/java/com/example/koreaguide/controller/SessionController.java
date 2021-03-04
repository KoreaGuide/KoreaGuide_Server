package com.example.koreaguide.controller;

import com.example.koreaguide.model.network.Header;
import com.example.koreaguide.model.network.request.SessionRequestDto;
import com.example.koreaguide.model.network.response.SessionResponseDto;
import com.example.koreaguide.service.UserApiLogicService;
import com.example.koreaguide.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SessionController {
    @Autowired
    private UserApiLogicService userApiLogicService;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/session")
    public Header<SessionResponseDto> create(@RequestBody  Header<SessionRequestDto> request){
        String accessToken = "accessToken";
        SessionResponseDto sessionDto = SessionResponseDto.builder()
                .accessToken(accessToken)
                .build();
        return userApiLogicService.authenticate(request);
    }
}

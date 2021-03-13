package com.example.koreaguide.controller;

import com.example.koreaguide.model.enumclass.UserLevel;
import com.example.koreaguide.model.exception.GlobalExceptionHandler;
import com.example.koreaguide.model.network.Header;
import com.example.koreaguide.model.network.response.HomeApiResponse;
import com.example.koreaguide.model.network.response.UserApiResponse;
import com.example.koreaguide.service.HomeApiLogicService;
import com.example.koreaguide.service.UserApiLogicService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/home")
public class HomeApiController extends GlobalExceptionHandler {
    @Autowired
    HomeApiLogicService homeApiLogicService;

    @GetMapping("/{level}")
    public Header<HomeApiResponse> getHomeInfo(@PathVariable(name = "level") UserLevel level) {
        HomeApiResponse homeApiResponse =homeApiLogicService.getHomeInfo(level);
        return new Header<>(homeApiResponse);
    }

}

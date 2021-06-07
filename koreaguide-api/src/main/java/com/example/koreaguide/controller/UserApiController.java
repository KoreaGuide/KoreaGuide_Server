package com.example.koreaguide.controller;

import com.example.koreaguide.ifs.CrudInterface;
import com.example.koreaguide.model.entity.User;
import com.example.koreaguide.model.exception.GlobalExceptionHandler;
import com.example.koreaguide.model.exception.KoreaGuideError;
import com.example.koreaguide.model.exception.KoreaGuideException;
import com.example.koreaguide.model.network.Header;
import com.example.koreaguide.model.network.request.SessionRequestDto;
import com.example.koreaguide.model.network.request.UserApiRequest;
import com.example.koreaguide.model.network.response.UserApiResponse;
import com.example.koreaguide.repository.UserRepository;
import com.example.koreaguide.service.UserApiLogicService;
import com.mysql.cj.log.Log;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/api/user")
public class UserApiController extends GlobalExceptionHandler{
    // generic으로 만듬!!
    @Autowired
    UserApiLogicService userApiLogicService;

    @Autowired
    UserRepository userRepository;

    @PostMapping("")
    public Header<UserApiResponse> create(@Valid @RequestBody Header<UserApiRequest> request) {
        UserApiResponse userApiResponse = userApiLogicService.create(request);
        HttpStatus http = HttpStatus.CREATED;
        return new Header<>(userApiResponse,http,"OK");
    }

    @PostMapping("/login")
    public Header<UserApiResponse> login(@RequestBody Header<SessionRequestDto> request){

        UserApiResponse userApiResponse = userApiLogicService.login(request);
        return new Header<>(userApiResponse);
    }
    @PostMapping("/checkDuplicate")
    public Header<UserApiResponse> checkDuplicateEmail(@RequestBody Header<UserApiRequest> request) {
        return userApiLogicService.checkDuplicateEmail(request);
    }

    @GetMapping("/{id}")
    public Header<UserApiResponse> read(
            Authentication authentication,
            @PathVariable(name = "id") Integer id) {
        SessionController.checkJWT(authentication,id,userRepository);

        UserApiResponse userApiResponse =userApiLogicService.read(id);
        return new Header<>(userApiResponse);
    }

    @PatchMapping("/{id}")
    public Header<UserApiResponse> update(
            Authentication authentication,
            @PathVariable(name = "id") Integer id,
            @RequestBody Header<UserApiRequest> request) {
        SessionController.checkJWT(authentication,id,userRepository);

        return userApiLogicService.update(id,request);
    }

    @DeleteMapping("/{id}")
    public Header delete(
            Authentication authentication,
            @PathVariable(name = "id") Integer id) {
        SessionController.checkJWT(authentication,id,userRepository);

        return userApiLogicService.delete(id);
    }



}

package com.example.koreaguide.controller;

import com.example.koreaguide.ifs.CrudInterface;
import com.example.koreaguide.model.network.Header;
import com.example.koreaguide.model.network.request.UserApiRequest;
import com.example.koreaguide.model.network.response.UserApiResponse;
import com.example.koreaguide.service.UserApiLogicService;
import com.mysql.cj.log.Log;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/user")
public class UserApiController implements CrudInterface<UserApiRequest,UserApiResponse> {
    // generic으로 만듬!!
    @Autowired
    UserApiLogicService userApiLogicService;

    @Override
    @PostMapping("")
    public Header<UserApiResponse> create(@RequestBody Header<UserApiRequest> request) {
        return userApiLogicService.create(request);
    }

    @PostMapping("/checkDuplicate")
    public Header<UserApiResponse> checkDuplicateEmail(@RequestBody Header<UserApiRequest> request) {
        return userApiLogicService.checkDuplicateEmail(request);
    }

    @Override
    @GetMapping("/{id}")
    public Header<UserApiResponse> read(@PathVariable(name = "id") Long id) {
        return userApiLogicService.read(id);
    }

    @Override
    @PatchMapping("/{id}")
    public Header<UserApiResponse> update(@PathVariable(name = "id") Long id,@RequestBody Header<UserApiRequest> request) {
        return userApiLogicService.update(id,request);
    }

    @Override
    @DeleteMapping("/{id}")
    public Header delete(@PathVariable(name = "id") Long id) {
        return userApiLogicService.delete(id);
    }
}

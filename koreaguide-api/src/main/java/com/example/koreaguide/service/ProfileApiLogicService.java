//package com.example.koreaguide.service;
//
//import com.example.koreaguide.model.entity.User;
//import com.example.koreaguide.model.exception.KoreaGuideError;
//import com.example.koreaguide.model.exception.KoreaGuideException;
//import com.example.koreaguide.model.network.response.ProfileApiResponse;
//import com.example.koreaguide.repository.MyTestResultRepository;
//import com.example.koreaguide.repository.UserRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.time.LocalDate;
//import java.util.List;
//import java.util.Optional;
//
///*
// * @author : Jisoo Kim
// * @date: 2021/05/10 4:11 오후
//*/
//@Service
//public class ProfileApiLogicService {
//    @Autowired
//    UserRepository userRepository;
//
//    @Autowired
//    MyTestResultRepository myTestResultRepository;
//
//    public ProfileApiResponse getProfileWord(Integer id) {
//
//        Optional<User> user = userRepository.findById(id);
//
//        return user.map(selectedUser->{
//            LocalDate today = LocalDate.now();
//            List<LocalDate>
//        }).orElseThrow(()->new KoreaGuideException(KoreaGuideError.ENTITY_NOT_FOUND_USER));
//
//    }
//
//
//}

package com.example.koreaguide.service;

import com.example.koreaguide.model.entity.MyTestResult;
import com.example.koreaguide.model.entity.User;
import com.example.koreaguide.model.exception.KoreaGuideError;
import com.example.koreaguide.model.exception.KoreaGuideException;
import com.example.koreaguide.model.network.response.ProfileApiResponse;
import com.example.koreaguide.model.network.response.QuizMultipleChoiceApiResponse;
import com.example.koreaguide.model.network.response.QuizResultApiResponse;
import com.example.koreaguide.repository.MyTestResultRepository;
import com.example.koreaguide.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/*
 * @author : Jisoo Kim
 * @date: 2021/05/10 4:11 오후
*/
@Service
public class ProfileApiLogicService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    MyTestResultRepository myTestResultRepository;

    public ProfileApiResponse getProfileWord(Integer id) {

        Optional<User> user = userRepository.findById(id);

        return user.map(selectedUser->{
            LocalDate today = LocalDate.now();
            Optional<MyTestResult> testResult = myTestResultRepository.findByDateAndUserId(today,id);
            List<QuizResultApiResponse> response = new ArrayList<QuizResultApiResponse>();
            if(testResult.isPresent()){
                QuizResultApiResponse res = testResult.map(selectedTestResult->{
                    QuizResultApiResponse quizResultApiResponse = QuizResultApiResponse.builder()
                            .date(today)
                            .correct(selectedTestResult.getCorrectNumber())
                            .wrong(selectedTestResult.getWrongNumber())
                            .total(selectedTestResult.getTotalNumber()).build();
                    return quizResultApiResponse;
                }).orElseThrow(()-> new KoreaGuideException(KoreaGuideError.ENTITY_NOT_FOUND));
                response.add(res);
            }else{
                QuizResultApiResponse res =QuizResultApiResponse.builder()
                        .date(today)
                        .correct(0)
                        .wrong(0)
                        .total(0).build();
                response.add(res);
            }
           return responseForProfileWord(response,selectedUser);
        }).orElseThrow(()->new KoreaGuideException(KoreaGuideError.ENTITY_NOT_FOUND_USER));

    }

    private ProfileApiResponse responseForProfileWord(List<QuizResultApiResponse> response,User user) {
        Integer attendance = user.getWeekAttendance();
        ProfileApiResponse profileApiResponse = ProfileApiResponse.builder()
                .attendance(attendance)
                .weekQuizResult(response).build();
        return profileApiResponse;
    }


}

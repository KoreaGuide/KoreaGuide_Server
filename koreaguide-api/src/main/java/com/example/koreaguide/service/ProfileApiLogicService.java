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

import java.time.DayOfWeek;
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
            List<LocalDate> dates = new ArrayList<LocalDate>();
            List<String> days = new ArrayList<String>();
            for(int i=6;i>=0;i--){
                LocalDate d = LocalDate.now().minusDays(i);
                String day = d.getDayOfWeek().toString();
                System.out.println("date: "+d+"  day:"+day);
                dates.add(d);
                days.add(day);
            }
            List<QuizResultApiResponse> response = new ArrayList<QuizResultApiResponse>();
            for(int d=0;d<dates.size();d++) {
                Optional<MyTestResult> testResult = myTestResultRepository.findByDateAndUserId(dates.get(d), id);
                if (testResult.isPresent()) {
                    int finalD = d;
                    QuizResultApiResponse res = testResult.map(selectedTestResult -> {
                        QuizResultApiResponse quizResultApiResponse = QuizResultApiResponse.builder()
                                .date(dates.get(finalD))
                                .dayOfWeek(days.get(finalD))
                                .correct(selectedTestResult.getCorrectNumber())
                                .wrong(selectedTestResult.getWrongNumber())
                                .total(selectedTestResult.getTotalNumber()).build();
                        return quizResultApiResponse;
                    }).orElseThrow(() -> new KoreaGuideException(KoreaGuideError.ENTITY_NOT_FOUND));
                    response.add(res);
                } else {
                    QuizResultApiResponse res = QuizResultApiResponse.builder()
                            .date(dates.get(d))
                            .dayOfWeek(days.get(d))
                            .correct(0)
                            .wrong(0)
                            .total(0).build();
                    response.add(res);
                }
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

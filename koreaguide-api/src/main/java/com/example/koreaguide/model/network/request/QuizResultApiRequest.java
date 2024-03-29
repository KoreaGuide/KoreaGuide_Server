package com.example.koreaguide.model.network.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/*
 * @author : Jisoo Kim
 * @date: 2021/05/02 1:09 오후
*/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class QuizResultApiRequest {
    private List<QuizResultDetailApiRequest> quizResults;
    private QuizResultDetailApiRequest quizResult;

}

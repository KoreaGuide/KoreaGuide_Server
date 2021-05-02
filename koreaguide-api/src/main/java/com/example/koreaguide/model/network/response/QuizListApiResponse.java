package com.example.koreaguide.model.network.response;
/*
 * @author : Jisoo Kim
 * @date: 2021/04/28 9:19 오후
*/
import com.example.koreaguide.model.enumclass.QuizType;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class QuizListApiResponse {
    private QuizType quizType;
    private Integer folderId;
    private List<QuizMultipleChoiceApiResponse> quizList;

}

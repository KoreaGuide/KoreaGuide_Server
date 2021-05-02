package com.example.koreaguide.model.network.request;

import com.example.koreaguide.model.enumclass.QuizType;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
/*
 * @author : Jisoo Kim
 * @date: 2021/04/28 8:30 오후
*/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class QuizListApiRequest {
    private QuizType quizType;
    private Integer folderId;
}

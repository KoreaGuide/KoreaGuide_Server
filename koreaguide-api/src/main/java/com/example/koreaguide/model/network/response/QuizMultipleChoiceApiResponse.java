package com.example.koreaguide.model.network.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
/*
 * @author : Jisoo Kim
 * @date: 2021/04/28 9:19 오후
*/
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class QuizMultipleChoiceApiResponse {
    private WordApiResponse selectedWord;
    private List<WordApiResponse> wordChoiceList;
}

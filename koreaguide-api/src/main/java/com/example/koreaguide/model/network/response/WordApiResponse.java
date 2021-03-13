package com.example.koreaguide.model.network.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/*
 * @author : Jisoo Kim
 * @date: 2021/03/14 1:50 오전
*/
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class WordApiResponse {
    private Integer id;

    private String word_eng;

    private String word_kor;

    private String meaning_eng;

    private String meaning_kor;

    private String image;

    private String audi0;
}

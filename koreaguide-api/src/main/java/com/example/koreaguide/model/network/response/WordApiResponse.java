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

    private String wordKor;

    private String wordEng;

    private String meaningKor1;

    private String meaningKor2;

    private String meaningEng1;

    private String meaningEng2;

    private String pronunciationEng;

    private String pronunciationKor;

    private String audio;

    private String image;
//    private String level;
}

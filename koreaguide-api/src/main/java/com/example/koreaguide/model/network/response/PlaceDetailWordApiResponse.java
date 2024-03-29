package com.example.koreaguide.model.network.response;

import com.example.koreaguide.model.enumclass.MyWordStatus;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/*
 * @author : Jisoo Kim
 * @date: 2021/03/22 2:19 오후
*/
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PlaceDetailWordApiResponse {
    private MyWordStatus wordStatus;

    private Integer wordId;

    private String wordKor;

    private String wordEng;

    private String meaningKor1;

    private String meaningKor2;

    private String meaningEng1;

    private String meaningEng2;

    private String pronunciationEng;

    private String pronunciationKor;

    private String wordImage;

    private String wordAudio;

}

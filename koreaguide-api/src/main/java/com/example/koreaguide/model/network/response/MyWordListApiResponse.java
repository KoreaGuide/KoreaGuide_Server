package com.example.koreaguide.model.network.response;

import com.example.koreaguide.model.enumclass.MyWordStatus;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MyWordListApiResponse {
    private Integer id;

    private String wordEng;

    private String wordKor;

    private String meaningEng1;

    private String meaningEng2;

    private String meaningKor1;

    private String meaningKor2;

    private String image;

    private String audio;

    private String pronunciationEng;

    private String pronunciationKor;

    private String partOfSpeech;

    private MyWordStatus myWordStatus;
}

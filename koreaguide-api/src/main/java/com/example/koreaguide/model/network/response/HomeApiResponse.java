package com.example.koreaguide.model.network.response;

import com.example.koreaguide.model.entity.TodaysPlace;
import com.example.koreaguide.model.entity.Word;
import com.example.koreaguide.model.enumclass.UserLevel;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/*
 * @author : Jisoo Kim
 * @date: 2021/03/14 1:45 오전
*/
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class HomeApiResponse {
    private Integer id;

    private Integer wordId;

    private String word;

    private String wordImage;

    private String wordAudio;

    private List<PlaceApiResponse> placeList;

}

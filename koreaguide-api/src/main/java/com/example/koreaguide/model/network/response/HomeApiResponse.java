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

    private UserLevel level;

    private String word;

    private String wordImage;

    private Integer place1_id;

    private String place1_title;

    private String place1_image;

    private Integer place2_id;

    private String place2_title;

    private String place2_image;

    private Integer place3_id;

    private String place3_title;

    private String place3_image;


}

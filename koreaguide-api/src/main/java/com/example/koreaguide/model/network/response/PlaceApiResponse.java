package com.example.koreaguide.model.network.response;

import com.example.koreaguide.model.enumclass.PlaceStatus;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;

/*
 * @author : Jisoo Kim
 * @date: 2021/03/22 12:15 오후
*/
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PlaceApiResponse {
    private Integer id;

    private Integer areaCode;

    private String title;

    private String address1;

    private Integer sigunguCode;

    private String firstImage;

    private String firstImage2;

    private String mapX;

    private String mapY;

    private String overview;

    private String category1;

    private String category2;

    private String category3;
}

package com.example.koreaguide.model.network.response;

import com.example.koreaguide.model.enumclass.PlaceStatus;
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
public class PlaceDetailApiResponse {
    private Integer userId;

    private PlaceStatus status;

    private Integer id;

    private String title;

    private Integer contentId;

    private Integer areaCode;

    private String address1;

    private String address2;

    private Integer sigunguCode;

    private String firstImage;

    private String firstImage2;

    private String mapX;

    private String mapY;

    private String overview_english;

    private String overview_korean;

    private String category1;

    private String category2;

    private String category3;
}

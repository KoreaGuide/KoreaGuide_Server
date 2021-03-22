package com.example.koreaguide.model.network.response;

import com.example.koreaguide.model.enumclass.RegionColorStatus;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/*
 * @author : Jisoo Kim
 * @date: 2021/03/22 12:19 오후
*/
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RegionPlaceApiResponse {
    private Integer userId;
    private Integer regionId;
    private RegionColorStatus regionColor;
    private List<PlaceApiResponse> placeList;

}


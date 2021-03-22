package com.example.koreaguide.model.network.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/*
 * @author : Jisoo Kim
 * @date: 2021/03/22 2:24 오후
*/
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PlaceDetailHeadApiResponse {
    private Integer userId;
    private Integer placeId;
    private List<PlaceDetailWordApiResponse> wordList;
}

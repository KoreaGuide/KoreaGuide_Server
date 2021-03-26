package com.example.koreaguide.model.network.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MyMapApiResponse {
    private Integer userId;
    private Integer placeCount;
    private List<MyMapPlaceApiResponse> placeList;
}

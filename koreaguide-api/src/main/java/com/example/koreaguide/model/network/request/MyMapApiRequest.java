package com.example.koreaguide.model.network.request;

import com.example.koreaguide.model.enumclass.PlaceStatus;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MyMapApiRequest {
    private Integer placeId;
    private String diary;
    private PlaceStatus placeStatus;
}

package com.example.koreaguide.model.network.request;

import com.example.koreaguide.model.enumclass.RegionColorStatus;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
/*
 * @author : Jisoo Kim
 * @date: 2021/03/22 11:35 오전
*/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RegionApiRequest {
    private Integer regionId;

    private RegionColorStatus color;
}

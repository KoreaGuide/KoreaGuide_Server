package com.example.koreaguide.model.network.response;

import com.example.koreaguide.model.enumclass.RegionColorStatus;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/*
 * @author : Jisoo Kim
 * @date: 2021/03/19 9:26 오후
*/
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CodeApiResponse {
    private Integer id;

    private Integer areacode;

    private String areanameKor;

    private String areanameEng;

    private RegionColorStatus color;

    private Integer listcount;
}

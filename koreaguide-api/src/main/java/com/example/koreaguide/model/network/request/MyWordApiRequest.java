package com.example.koreaguide.model.network.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.Entity;

/*
 * @author : Jisoo Kim
 * @date: 2021/03/15 8:33 오후
*/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MyWordApiRequest {
    private Integer wordId;
}

package com.example.koreaguide.model.network.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

/*
 * @author : Jisoo Kim
 * @date: 2021/05/10 5:56 오후
*/
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class QuizResultApiResponse {
    private LocalDate date;

    private Integer total;

    private Integer correct;

    private Integer wrong;
}

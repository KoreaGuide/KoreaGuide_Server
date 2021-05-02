package com.example.koreaguide.model.network.request;

import com.example.koreaguide.model.enumclass.TestResultStatus;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/*
 * @author : Jisoo Kim
 * @date: 2021/05/02 1:11 오후
*/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class QuizResultDetailApiRequest {
    private Integer wordId;
    private Integer originalFolderId;
    private Integer finalFolderId;
    private TestResultStatus resultStatus;
}

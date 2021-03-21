package com.example.koreaguide.model.network.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/*
 * @author : Jisoo Kim
 * @date: 2021/03/21 7:53 오전
*/
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MyWordFolderApiResponse {
    private Integer userId;

    private Integer wordFolderId;

    private String folderName;

    private Integer wordCount;
}

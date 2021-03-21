package com.example.koreaguide.model.network.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/*
 * @author : Jisoo Kim
 * @date: 2021/03/21 7:50 오전
*/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MyWordFolderApiRequest {
    private Integer userId;

    private Integer wordFolderId;

    private String folderName;
}

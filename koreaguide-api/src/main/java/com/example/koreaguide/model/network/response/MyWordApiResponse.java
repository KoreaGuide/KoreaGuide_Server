package com.example.koreaguide.model.network.response;

import com.example.koreaguide.model.enumclass.MyWordStatus;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/*
 * @author : Jisoo Kim
 * @date: 2021/03/15 8:35 오후
*/

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MyWordApiResponse {
    private Integer userId;

    private Integer myWordFolderId;

    private String myWordFolderName;

    private Integer previousWordCount;

    private Integer nowWordCount;

    private List<MyWordListApiResponse> myWordList;


}

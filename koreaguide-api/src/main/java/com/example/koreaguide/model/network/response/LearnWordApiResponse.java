package com.example.koreaguide.model.network.response;

import com.example.koreaguide.model.network.Pagination;
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
public class LearnWordApiResponse {
    private Integer userId;
    private Integer folderId;
    private String folderName;
    private Pagination pagination;
    private List<MyWordListApiResponse> wordList;
}

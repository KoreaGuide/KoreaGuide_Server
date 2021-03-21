package com.example.koreaguide.model.network.response;

import com.example.koreaguide.model.enumclass.MyWordStatus;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MyWordListApiResponse {
    private Integer id;

    private String word_eng;

    private String word_kor;

    private String meaning_eng;

    private String meaning_kor;

    private String image;

    private String audio;

    private String pronunciation;

    private MyWordStatus myWordStatus;
}

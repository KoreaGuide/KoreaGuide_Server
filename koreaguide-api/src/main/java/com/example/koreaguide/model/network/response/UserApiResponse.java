package com.example.koreaguide.model.network.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserApiResponse {

    private Long id;

    private String email;

    private String password;            // 암호화 해야함 -- 했음!

    private String nickname;

    private String level;

    private LocalDateTime createdAt;

    private String createdBy;

    private String token;
}

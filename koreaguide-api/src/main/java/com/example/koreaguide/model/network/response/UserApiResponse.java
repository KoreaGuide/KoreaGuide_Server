package com.example.koreaguide.model.network.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserApiResponse {

    private Long id;

    private String email;

    private String password;            // 암호화 해야함

    private String nickname;

    private String level;

    private LocalDateTime createdAt;

    private String createdBy;
}

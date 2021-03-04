package com.example.koreaguide.model.network.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SessionRequestDto {

    private String email;

    private String password;
}

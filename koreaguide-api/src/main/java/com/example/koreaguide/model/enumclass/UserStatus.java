package com.example.koreaguide.model.enumclass;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
/*
 * @author : Jisoo Kim
 * @date: 2021/03/21 6:53 오전
*/
@Getter
@AllArgsConstructor
@ToString
public enum UserStatus {
    ACTIVE(0,"이메일 인증 완료","User status is ACTIVE"),
    INACTIVE(1,"이메일 인증 필요","User status is INACTIVE"),
    ;

    private Integer id;
    private String title;
    private String description;
}

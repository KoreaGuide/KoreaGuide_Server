package com.example.koreaguide.model.enumclass;

import lombok.AllArgsConstructor;
import lombok.Getter;
/*
 * @author : Jisoo Kim
 * @date: 2021/03/14 12:01 오전
*/
@Getter
@AllArgsConstructor
public enum UserLevel {
    LOW(0,"하급","User level is LOW"),
    MID(1,"중급","User level is MID"),
    HIGH(2,"상급", "User level is HIGH")
    ;

    private Integer id;
    private String title;
    private String description;
}

package com.example.koreaguide.model.enumclass;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

/*
 * @author : Jisoo Kim
 * @date: 2021/03/14 12:01 오전
*/
@Getter
@AllArgsConstructor
@ToString
public enum RegionColorStatus {
    NONE(0,"없음","Region color is NONE"),
    RED(1,"빨강","Region color is RED"),
    BLUE(2,"파랑", "Region color is BLUE"),
    GREEN(2,"초록", "Region color is GREEN"),
    PINK(2,"분홍", "Region color is PINK")
    ;

    private Integer id;
    private String title;
    private String description;
}

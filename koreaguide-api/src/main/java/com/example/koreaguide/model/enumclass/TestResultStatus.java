package com.example.koreaguide.model.enumclass;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

/*
 * @author : Jisoo Kim
 * @date: 2021/05/02 1:15 오후
*/
@Getter
@AllArgsConstructor
@ToString
public enum TestResultStatus {
    CORRECT(0,"정답","Test result : CORRECT"),
    WRONG(1,"오답","Test result : WRONG")
    ;

    private Integer id;
    private String title;
    private String description;
}
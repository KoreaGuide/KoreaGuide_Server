package com.example.koreaguide.model.enumclass;
/*
 * @author : Jisoo Kim
 * @date: 2021/03/15 8:49 오후
*/

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@ToString
public enum MyWordStatus {
    NO_STATUS(0,"상태정보 없음","No status for word"),
    CORRECT(1,"맞은 단어","Word status is CORRECT"),
    WRONG(2,"틀린 단어", "Word status is WRONG"),
    IN_MY_LIST(3,"내 단어장에 있음","In my word list")
    ;

    private Integer id;
    private String title;
    private String description;
}

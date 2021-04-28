package com.example.koreaguide.model.enumclass;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

/*
 * @author : Jisoo Kim
 * @date: 2021/04/28 8:40 오후
*/
@Getter
@AllArgsConstructor
@ToString
public enum QuizType {
    MATCH(0,"매칭","Quiz type = matching"),
    LISTEN(1,"듣기","Quiz type = listening"),
    SPELLING_E(2,"스펠링 - 초급", "Quiz type = spelling(EASY)"),
    SPELLING_H(3,"스펠링 - 상급","Quiz type = spelling(HARD)")
    ;

    private Integer id;
    private String title;
    private String description;
}

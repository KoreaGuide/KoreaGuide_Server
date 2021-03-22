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
public enum PlaceStatus {
    NO_STATUS(0,"내 지도에 추가되지 않음","Not in my map"),
    WISH_LIST(1,"내 지도 위시리스트에 추가됨","In my wish list"),
    HAVE_BEEN_TO(2,"내 지도 이미가본 곳에 추가됨", "In my have been to list")
    ;

    private Integer id;
    private String title;
    private String description;
}

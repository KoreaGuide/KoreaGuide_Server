package com.example.koreaguide.model.network;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
/*
 * @author : Jisoo Kim
 * @date: 2021/03/02 2:25 오전
*/

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Header<T> {
    // 공통적으로 들어가는 부분!

    // api 통신 시간

    // api 응답 코드
    private String resultCode;

    // api 부가 설명
    private String description;

    private T data;

    //OK
    public static <T> Header<T> OK(){
        return (Header<T>)Header.builder()
                .resultCode("OK")
                .description("OK")
                .build();
    }
    public static <T> Header<T> OK(String description){
        return (Header<T>)Header.builder()
                .resultCode("OK")
                .description(description)
                .build();
    }

    //OK data
    public static <T> Header<T> OK(T data){
        return (Header<T>)Header.builder()
                .resultCode("OK")
                .description("OK")
                .data(data)
                .build();
    }

    // ERROR
    public static <T> Header<T> ERROR(String description){
        return (Header<T>)Header.builder()
                .resultCode("ERROR")
                .description(description)
                .build();
    }
}

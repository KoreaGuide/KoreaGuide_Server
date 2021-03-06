package com.example.koreaguide.model.network;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
/*
 * @author : Jisoo Kim
 * @date: 2021/03/02 2:25 오전
*/

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Header<T> {
    // 공통적으로 들어가는 부분!

    // api 통신 시간

    // api 응답 코드
    private String resultCode;
    private HttpStatus status;

    // api 부가 설명
    private String description;

    private T data;

    //OK
    public static <T> Header<T> OK(){
        return (Header<T>)Header.builder()
                .resultCode("OK")
                .status(HttpStatus.OK)
                .description("OK")
                .build();
    }
    public static <T> Header<T> OK(String description){
        return (Header<T>)Header.builder()
                .resultCode("OK")
                .status(HttpStatus.OK)
                .description(description)
                .build();
    }

    //OK data
    public static <T> Header<T> OK(T data,HttpStatus status){
        return (Header<T>)Header.builder()
                .resultCode("OK")
                .status(status)
                .description("OK")
                .data(data)
                .build();
    }

    // ERROR
    public static <T> Header<T> ERROR(String description){
        return (Header<T>)Header.builder()
                .resultCode("ERROR")
                .status(HttpStatus.BAD_REQUEST)
                .description(description)
                .build();
    }
    public static <T> Header<T> NOTFOUNDERROR(String description){
        return (Header<T>)Header.builder()
                .resultCode("ERROR")
                .status(HttpStatus.NOT_FOUND)
                .description(description)
                .build();
    }

    public static <T> Header<T> CONFLICTERROR(String description){
        return (Header<T>)Header.builder()
                .resultCode("ERROR")
                .status(HttpStatus.CONFLICT)
                .description(description)
                .build();
    }

}

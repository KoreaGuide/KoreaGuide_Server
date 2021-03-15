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
    private int resultCode;
    private HttpStatus status;

    // api 부가 설명
    private String description;

    private T data;

    //OK
    public static <T> Header<T> OK(){
        return (Header<T>)Header.builder()
                .resultCode(200)
                .status(HttpStatus.OK)
                .description("OK")
                .build();
    }
    public static <T> Header<T> OK(String description){
        return (Header<T>)Header.builder()
                .resultCode(200)
                .status(HttpStatus.OK)
                .description(description)
                .build();
    }

    //OK data
    public static <T> Header<T> OK(T data,HttpStatus status){
        int codeNum = status.value();
        return (Header<T>)Header.builder()
                .resultCode(codeNum)
                .status(status)
                .description("OK")
                .data(data)
                .build();
    }

    // ERROR
    public static <T> Header<T> ERROR(String description){
        int codeNum = HttpStatus.BAD_REQUEST.value();
        return (Header<T>)Header.builder()
                .resultCode(codeNum)
                .status(HttpStatus.BAD_REQUEST)
                .description(description)
                .build();
    }
    public static <T> Header<T> NOTFOUNDERROR(String description){
        return (Header<T>)Header.builder()
                .resultCode(HttpStatus.NOT_FOUND.value())
                .status(HttpStatus.NOT_FOUND)
                .description(description)
                .build();
    }

    public static <T> Header<T> CONFLICTERROR(String description){
        return (Header<T>)Header.builder()
                .resultCode(HttpStatus.CONFLICT.value())
                .status(HttpStatus.CONFLICT)
                .description(description)
                .build();
    }

    public static<T> Header<T> RESPONSE(HttpStatus status,String description){
        return (Header<T>)Header.builder()
                .resultCode(status.value())
                .status(status)
                .description(description)
                .build();
    }

    public Header(HttpStatus status,String description){
                this.resultCode = status.value();
                this.status= status;
                this.description=description;
    }
    public Header(T data){
        this.resultCode = HttpStatus.OK.value();
        this.status= HttpStatus.OK;
        this.description="OK";
        this.data = data;
    }
    public Header(T data,String description){
        this.resultCode = HttpStatus.OK.value();
        this.status= HttpStatus.OK;
        this.description="OK";
        this.data = data;
    }
    public Header(T data,HttpStatus status,String description){
        this.resultCode = status.value();
        this.status= status;
        this.description=description;
        this.data = data;
    }
}

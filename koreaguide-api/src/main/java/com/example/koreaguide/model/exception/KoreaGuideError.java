package com.example.koreaguide.model.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum KoreaGuideError {
    DUPLICATE_ERROR("Unique Field Error",HttpStatus.CONFLICT),
    DUPLICATE_ERROR_USER("User already exists",HttpStatus.CONFLICT),
    DUPLICATE_ERROR_WORD("Word already exists",HttpStatus.CONFLICT),
    DUPLICATE_ERROR_MYWORD("MyWord already exists",HttpStatus.CONFLICT),
    DUPLICATE_ERROR_HOME("Home already exists",HttpStatus.CONFLICT),
    DUPLICATE_ERROR_MYPLACE("MyPlace already exists",HttpStatus.CONFLICT),
    UNKNOWN_ERROR("Unknown Error",HttpStatus.INTERNAL_SERVER_ERROR),
    NOT_LOGIN("Not Logged in", HttpStatus.UNAUTHORIZED),
    ENTITY_NOT_FOUND("Cannot Find Entity", HttpStatus.INTERNAL_SERVER_ERROR),
    ENTITY_NOT_FOUND_USER("Cannot Find User", HttpStatus.INTERNAL_SERVER_ERROR),
    ENTITY_NOT_FOUND_WORD("Cannot Find Word", HttpStatus.INTERNAL_SERVER_ERROR),
    ENTITY_NOT_FOUND_PLACE("Cannot Find Place", HttpStatus.INTERNAL_SERVER_ERROR),
    ENTITY_NOT_FOUND_MYWORD("Cannot Find MyWord", HttpStatus.INTERNAL_SERVER_ERROR),
    ENTITY_NOT_FOUND_HOME("Cannot Find Home", HttpStatus.INTERNAL_SERVER_ERROR),
    ENTITY_EMPTY_MYWORD("MyWord is empty",HttpStatus.NO_CONTENT),
    ENTITY_EMPTY_MYPLACE("MyPlace is empty",HttpStatus.NO_CONTENT),
    WRONG_PASSWORD("Wrong Password", HttpStatus.CONFLICT),
    WRONG_FORMAT("Wrong Format",HttpStatus.BAD_REQUEST);
    private String desc;
    private HttpStatus status;

    KoreaGuideError(String desc, HttpStatus status) {
        this.desc = desc;
        this.status = status;
//        System.out.println("========= ERORR: "+desc+"   status: "+status);
    }
}

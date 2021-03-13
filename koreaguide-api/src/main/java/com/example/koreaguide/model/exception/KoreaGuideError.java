package com.example.koreaguide.model.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum KoreaGuideError {
    DUPLICATE_ERROR("Unique Field Error",HttpStatus.CONFLICT),
    UNKNOWN_ERROR("Unknown Error",HttpStatus.INTERNAL_SERVER_ERROR),
    NOT_LOGIN("Not Logged in", HttpStatus.UNAUTHORIZED),
    ENTITY_NOT_FOUND("Cannot Find Entity", HttpStatus.INTERNAL_SERVER_ERROR),
    WRONG_PASSWORD("Wrong Password", HttpStatus.CONFLICT),
    WRONG_FORMAT("Wrong Format",HttpStatus.BAD_REQUEST);
    private String desc;
    private HttpStatus status;

    KoreaGuideError(String desc, HttpStatus status) {
        this.desc = desc;
        this.status = status;
        System.out.println("========= ERORR: "+desc+"   status: "+status);
    }
}

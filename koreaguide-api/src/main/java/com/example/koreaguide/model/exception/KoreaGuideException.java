package com.example.koreaguide.model.exception;

import lombok.Getter;

@Getter
public class KoreaGuideException extends RuntimeException {
    private String message;

    public KoreaGuideException(String message) {
        this.message = message;
    }
}
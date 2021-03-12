package com.example.koreaguide.model.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class KoreaGuideException extends RuntimeException {

    private KoreaGuideError error;

    public KoreaGuideException(KoreaGuideError error) {
        super(error.getDesc());
        this.error = error;

    }

    public KoreaGuideException(KoreaGuideError error, String message) {
        super(error.getDesc() + " : " + message);
        this.error = error;
        System.out.println("========= EXCEPT: "+error.getDesc()+"   status: "+error.getStatus());

    }

    public KoreaGuideException(KoreaGuideError error, Throwable cause) {
        super(error.getDesc(), cause);
        this.error = error;
    }
}
package com.example.koreaguide.model.exception;

import com.example.koreaguide.model.network.Header;
import org.hibernate.service.spi.ServiceException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GlobalExceptionHandler{
    @org.springframework.web.bind.annotation.ExceptionHandler(KoreaGuideException.class)
    public Header<?> koreaGuideException(KoreaGuideException e) {
        KoreaGuideError error = e.getError();
        System.out.println("ERROR!!!! is: "+error.getDesc()+"+++++++ status: "+error.getStatus());
        return new Header<>(error.getStatus(), error.getDesc());
    }

    @ExceptionHandler(RuntimeException.class)
    public Header<?> unknownException(Exception e) {
        return new Header<>(KoreaGuideError.UNKNOWN_ERROR);
    }
}

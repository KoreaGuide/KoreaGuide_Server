package com.example.koreaguide.model.exception;

public class PasswordWrongException extends RuntimeException{
    PasswordWrongException(){
        super("Password is wrong");
    }
}

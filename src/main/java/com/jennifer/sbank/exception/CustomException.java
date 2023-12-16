package com.jennifer.sbank.exception;

import org.springframework.http.HttpStatus;

public class CustomException extends RuntimeException{
    private String message;
    private HttpStatus httpStatus;

    public CustomException(String message, String message1) {
        super(message);
        this.message = message1;
    }
    public CustomException(String message){
        super(message);
    }
}

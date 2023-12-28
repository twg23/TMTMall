package com.example.TMTMall.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
public class OutOfStockException extends RuntimeException {
    public OutOfStockException (String message){
        super(message);
    }
}

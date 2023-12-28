package com.example.TMTMall.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ControllerAdvise {


     @ExceptionHandler(OutOfStockException.class)
        public ResponseEntity<Object> outOfStockException(OutOfStockException ex) {
         throw new OutOfStockException(ex.getMessage());
     }
}

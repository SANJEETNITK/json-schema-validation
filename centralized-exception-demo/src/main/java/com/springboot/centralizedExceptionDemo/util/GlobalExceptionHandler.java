package com.springboot.centralizedExceptionDemo.util;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = NullPointerException.class)
    public ResponseEntity<String> handleNullPointerException() {
        String bodyOfResponse = "Null Pointer Exception Occurred!!";
        return new ResponseEntity<>(bodyOfResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = ArithmeticException.class)
    public ResponseEntity<String> handleArithmeticException() {
        String bodyOfResponse = "Arithmetic Exception Occurred!!";
        return new ResponseEntity<>(bodyOfResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = NumberFormatException.class)
    public ResponseEntity<String> handleNumberFormatException() {
        String bodyOfResponse = "Number format Exception Occurred!!";
        return new ResponseEntity<>(bodyOfResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<String> handleGenericException() {
        String bodyOfResponse = "Exception Occurred!!";
        return new ResponseEntity<>(bodyOfResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}

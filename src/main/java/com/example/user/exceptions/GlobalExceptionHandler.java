package com.example.user.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UserNotFoundException.class)

    public ResponseEntity<String> handlerUserNotFoundException(UserNotFoundException exception){
        return new ResponseEntity<>(exception.getMessage (), HttpStatus.NOT_FOUND);
    }
}

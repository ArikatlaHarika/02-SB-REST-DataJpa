package com.javaexpress.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

//AOP Annotation
//controllerAdvice+ResponseBody
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(code= HttpStatus.BAD_REQUEST)
    public ErrorAPI handleException(ResourceNotFoundException ex){

        var errorAPI =new ErrorAPI();
        errorAPI.setStatus(HttpStatus.BAD_REQUEST.getReasonPhrase());
        errorAPI.setStatusCode(HttpStatus.BAD_REQUEST.value());
        errorAPI.setLocalDateTime(LocalDateTime.now());
        errorAPI.setDetails(ex.getMessage());
        return errorAPI;
    }
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorAPI> handleException(Exception ex){

        var errorAPI =new ErrorAPI();
        errorAPI.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
        errorAPI.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
        errorAPI.setLocalDateTime(LocalDateTime.now());
        errorAPI.setDetails(ex.getMessage());
        return new ResponseEntity<>(errorAPI,HttpStatus.INTERNAL_SERVER_ERROR);
    }
}

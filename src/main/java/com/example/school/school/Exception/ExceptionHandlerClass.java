package com.example.school.school.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

@ControllerAdvice
public class ExceptionHandlerClass extends Exception{



    @ExceptionHandler(Exception.class)
public ResponseEntity<ErrorDetails> allExceptions(Exception e, WebRequest webRequest){
        ErrorDetails erorDetails1 = new ErrorDetails(e.getMessage(),webRequest.getDescription(true),new Date() );
        return new ResponseEntity<>(erorDetails1, HttpStatus.INTERNAL_SERVER_ERROR);
    }
System.out.println("Hi");


}

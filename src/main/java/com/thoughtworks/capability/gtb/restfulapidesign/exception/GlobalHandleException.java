package com.thoughtworks.capability.gtb.restfulapidesign.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalHandleException {

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity handleRuntimeException(RuntimeException exception) {
        ErrorResult errorResult = ErrorResult.builder().code(HttpStatus.INTERNAL_SERVER_ERROR.value()).message(exception.getMessage()).build();
        return ResponseEntity.badRequest().body(errorResult);
    }

    @ExceptionHandler(StudentNotExistException.class)
    public ResponseEntity handleStudentNotExist(StudentNotExistException exception) {
        ErrorResult errorResult = ErrorResult.builder().code(HttpStatus.BAD_REQUEST.value()).message(exception.getMessage()).build();
        return ResponseEntity.badRequest().body(errorResult);
    }

    @ExceptionHandler({GroupNameRepeatException.class, GroupNotExistException.class})
    public ResponseEntity handleGroupException(Exception exception){
        ErrorResult errorResult = ErrorResult.builder().code(HttpStatus.BAD_REQUEST.value()).message(exception.getMessage()).build();
        return ResponseEntity.badRequest().body(errorResult);
    }
}

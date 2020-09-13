package com.thoughtworks.capability.gtb.restfulapidesign.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalHandleException {

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity handleRuntimeException(RuntimeException exception) {
        ErrorResult errorResult = ErrorResult.builder().httpStatus(HttpStatus.INTERNAL_SERVER_ERROR).message(exception.getMessage()).build();
        return ResponseEntity.badRequest().body(errorResult);
    }
}

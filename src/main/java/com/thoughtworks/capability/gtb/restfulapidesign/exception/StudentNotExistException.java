package com.thoughtworks.capability.gtb.restfulapidesign.exception;

public class StudentNotExistException extends Exception{
    public String message = "student not exist";

    @Override
    public String getMessage() {
        return message;
    }
}

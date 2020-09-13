package com.thoughtworks.capability.gtb.restfulapidesign.exception;

public class GroupNotExistException extends Exception{
    public String message = "group not exist";

    @Override
    public String getMessage() {
        return message;
    }
}

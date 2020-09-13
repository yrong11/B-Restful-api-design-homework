package com.thoughtworks.capability.gtb.restfulapidesign.exception;

public class GroupNameRepeatException extends Exception{
    String message = "group name repeat";

    @Override
    public String getMessage() {
        return message;
    }
}

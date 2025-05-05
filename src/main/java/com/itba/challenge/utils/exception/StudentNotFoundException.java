package com.itba.challenge.utils.exception;

public class StudentNotFoundException extends NotFoundException {
    public StudentNotFoundException(String userMessage, String backendMessage) {
        super(userMessage, backendMessage);
    }
}

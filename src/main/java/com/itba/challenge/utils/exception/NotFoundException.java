package com.itba.challenge.utils.exception;

import lombok.Getter;

@Getter
public class NotFoundException extends RuntimeException {

    private final String userMessage;

    public NotFoundException(String userMessage, String backendMessage) {
        super(backendMessage);
        this.userMessage = userMessage;
    }
}


package com.itba.challenge.utils;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record ApiError(String backendMessage,
                       @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
                       LocalDateTime date,
                       String url,
                       String method,
                       String message) {
}
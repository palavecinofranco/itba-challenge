package com.itba.challenge.utils;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;

import java.time.LocalDateTime;
import java.util.Map;

@Builder
public record ApiError(
                       @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
                       LocalDateTime date,
                       String url,
                       String method,
                       String message,
                       Map<String, String> validationErrors) {
}
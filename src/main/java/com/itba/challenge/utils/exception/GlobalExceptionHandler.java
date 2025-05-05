package com.itba.challenge.utils.exception;

import com.itba.challenge.utils.ApiError;
import com.itba.challenge.utils.MessageUtil;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.Locale;

@RestControllerAdvice
@Slf4j
@RequiredArgsConstructor
public class GlobalExceptionHandler {

    private final MessageUtil messageUtil;

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handlerGenericException(HttpServletRequest req, Exception e) {
        String message = messageUtil.getMessage("error.generic", Locale.forLanguageTag("es"));
        ApiError apiError = ApiError.builder()
                .backendMessage(e.getLocalizedMessage())
                .message(message)
                .url(req.getRequestURL().toString())
                .date(LocalDateTime.now())
                .method(req.getMethod())
                .build();

        log.error("{}: {}", message, e.getMessage(), e);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(apiError);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<?> handleNotFoundException(HttpServletRequest req, NotFoundException e) {
        ApiError apiError = ApiError.builder()
                .backendMessage(e.getMessage())
                .message(e.getUserMessage())
                .url(req.getRequestURL().toString())
                .date(LocalDateTime.now())
                .method(req.getMethod())
                .build();

        log.error("{}: {}", e.getUserMessage(), e.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiError);
    }
}

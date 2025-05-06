package com.itba.challenge.utils.exception;

import com.itba.challenge.utils.ApiError;
import com.itba.challenge.utils.MessageUtil;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.View;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

@RestControllerAdvice
@Slf4j
@RequiredArgsConstructor
public class GlobalExceptionHandler {

    private final MessageUtil messageUtil;
    private final View error;

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handlerGenericException(HttpServletRequest req, Exception e) {
        String message = messageUtil.getMessage("error.generic", Locale.forLanguageTag("es"));
        ApiError apiError = ApiError.builder()
                .message(message)
                .url(req.getRequestURL().toString())
                .date(LocalDateTime.now())
                .method(req.getMethod())
                .build();

        log.error("{}:", e.getMessage(), e);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(apiError);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<?> handleNotFoundException(HttpServletRequest req, NotFoundException e) {
        ApiError apiError = ApiError.builder()
                .message(e.getUserMessage())
                .url(req.getRequestURL().toString())
                .date(LocalDateTime.now())
                .method(req.getMethod())
                .build();

        log.error("{}:", e.getMessage(), e);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiError);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleValidationExceptions(HttpServletRequest req, MethodArgumentNotValidException e) {
        Map<String, String> errors = new HashMap<>();

        for (FieldError error : e.getBindingResult().getFieldErrors()) {
            errors.put(error.getField(), error.getDefaultMessage());
        }

        log.error("Validation error: {}", errors);
        log.error("{}:", e.getMessage(), e);

        ApiError apiError = ApiError.builder()
                .message(messageUtil.getMessage("error.validation", Locale.forLanguageTag("es")))
                .url(req.getRequestURL().toString())
                .date(LocalDateTime.now())
                .method(req.getMethod())
                .validationErrors(errors)
                .build();

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiError);
    }
}

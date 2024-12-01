package org.example.lab_2_be.handlers;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<Map<String, Object>> handleResponseStatusException(ResponseStatusException ex) {
        Map<String, Object> errorDetails = createErrorDetails(
                ex.getStatusCode(),
                ex.getReason() != null ? ex.getReason() : "Unexpected error",
                ex.getMessage(),
                null
        );

        return new ResponseEntity<>(errorDetails, new HttpHeaders(), ex.getStatusCode());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> fieldErrors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error ->
                fieldErrors.put(error.getField(), error.getDefaultMessage())
        );

        Map<String, Object> errorDetails = createErrorDetails(
                HttpStatus.BAD_REQUEST,
                "Validation failed",
                "One or more fields have invalid values",
                fieldErrors
        );

        return new ResponseEntity<>(errorDetails, new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }

    private Map<String, Object> createErrorDetails(HttpStatusCode status, String error, String message, Map<String, String> fieldErrors) {
        Map<String, Object> errorDetails = new HashMap<>();
        errorDetails.put("timestamp", LocalDateTime.now());
        errorDetails.put("status", status.value());
        errorDetails.put("error", error);
        errorDetails.put("message", message);

        if (fieldErrors != null && !fieldErrors.isEmpty()) {
            errorDetails.put("fieldErrors", fieldErrors);
        }

        return errorDetails;
    }
}
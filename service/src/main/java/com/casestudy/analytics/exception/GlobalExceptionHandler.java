package com.casestudy.analytics.exception;

import com.casestudy.analytics.dto.response.ErrorResponse;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

/**
 * Global exception handler for the analytics service.
 * Handles specific exceptions related to insights and provides a structured error response.
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(InsightNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleInsightNotFoundException(
            InsightNotFoundException ex, HttpServletRequest request) {
        log.error("Insight not found: {}", ex.getMessage());
        return createErrorResponse(
                HttpStatus.NOT_FOUND,
                ex.getMessage(),
                "Insight Not Found",
                request.getRequestURI()
        );
    }

    @ExceptionHandler(InvalidInsightTypeException.class)
    public ResponseEntity<ErrorResponse> handleInvalidInsightTypeException(
            InvalidInsightTypeException ex, HttpServletRequest request) {
        log.error("Invalid insight type: {}", ex.getMessage());
        return createErrorResponse(
                HttpStatus.BAD_REQUEST,
                ex.getMessage(),
                "Invalid Insight Type",
                request.getRequestURI()
        );
    }

    @ExceptionHandler(InsightException.class)
    public ResponseEntity<ErrorResponse> handleInsightException(
            InsightException ex, HttpServletRequest request) {
        log.error("Insight error: {}", ex.getMessage());
        return createErrorResponse(
                HttpStatus.INTERNAL_SERVER_ERROR,
                ex.getMessage(),
                "Internal Server Error",
                request.getRequestURI()
        );
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGenericException(
            Exception ex, HttpServletRequest request) {
        log.error("Unexpected error: ", ex);
        return createErrorResponse(
                HttpStatus.INTERNAL_SERVER_ERROR,
                "An unexpected error occurred",
                "Internal Server Error",
                request.getRequestURI()
        );
    }

    private ResponseEntity<ErrorResponse> createErrorResponse(
            HttpStatus status, String message, String error, String path) {
        ErrorResponse errorResponse = ErrorResponse.builder()
                .timestamp(LocalDateTime.now())
                .message(message)
                .error(error)
                .path(path)
                .build();
        return new ResponseEntity<>(errorResponse, status);
    }
}
package io.github.apopovicc.taskmanager.exception.handler;

import io.github.apopovicc.taskmanager.exception.custom.ApiException;
import io.github.apopovicc.taskmanager.exception.response.ApiErrorResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ApiException.class)
    public ResponseEntity<ApiErrorResponse> handleApiException(
            ApiException ex,
            HttpServletRequest request) {

        ApiErrorResponse response =
                new ApiErrorResponse(
                        ex.getStatus().value(),
                        ex.getStatus().name(),
                        ex.getMessage(),
                        LocalDateTime.now(),
                        request.getRequestURI()
                );

        return ResponseEntity
                .status(ex.getStatus())
                .body(response);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiErrorResponse> handleValidationException(
            MethodArgumentNotValidException ex,
            HttpServletRequest request) {

        String message = ex.getBindingResult()
                .getFieldError()
                .getDefaultMessage();

        ApiErrorResponse response =
                new ApiErrorResponse(
                        400,
                        "BAD_REQUEST",
                        message,
                        LocalDateTime.now(),
                        request.getRequestURI()
                );

        return ResponseEntity.badRequest().body(response);
    }
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiErrorResponse> handleUnknownException(
            Exception ex,
            HttpServletRequest request) {

        ApiErrorResponse response =
                new ApiErrorResponse(
                        500,
                        "INTERNAL_SERVER_ERROR",
                        "Something went wrong",
                        LocalDateTime.now(),
                        request.getRequestURI()
                );

        return ResponseEntity.internalServerError().body(response);
    }
}

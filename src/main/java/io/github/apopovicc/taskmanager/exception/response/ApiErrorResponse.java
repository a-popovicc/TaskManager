package io.github.apopovicc.taskmanager.exception.response;

import java.time.LocalDateTime;

public record ApiErrorResponse(
        
    int status,
    String error,
    String message,
    LocalDateTime timestamp,
    String path
){}

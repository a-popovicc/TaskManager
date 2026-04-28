package io.github.apopovicc.taskmanager.exception.custom;

import org.springframework.http.HttpStatus;

public class ForbidenException extends ApiException {
    public ForbidenException(String message) {
        super(message, HttpStatus.FORBIDDEN);
    }
}

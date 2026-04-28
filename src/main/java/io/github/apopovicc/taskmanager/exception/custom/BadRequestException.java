package io.github.apopovicc.taskmanager.exception.custom;

import org.springframework.http.HttpStatus;

public class BadRequestException extends ApiException{

    public BadRequestException(String message) {
        super(message, HttpStatus.BAD_REQUEST);
    }

}

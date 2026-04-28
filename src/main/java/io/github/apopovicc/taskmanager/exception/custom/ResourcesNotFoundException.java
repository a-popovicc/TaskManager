package io.github.apopovicc.taskmanager.exception.custom;

import org.springframework.http.HttpStatus;

public class ResourcesNotFoundException extends ApiException {
    public ResourcesNotFoundException(String message) {
        super(message, HttpStatus.NOT_FOUND);
    }
}

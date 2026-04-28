package io.github.apopovicc.taskmanager.exception.custom;

public class DataLoadException extends RuntimeException {

    public DataLoadException(String message, Throwable cause) {
        super(message,  cause);
    }
}

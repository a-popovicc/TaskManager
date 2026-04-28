package io.github.apopovicc.taskmanager.validation;

import io.github.apopovicc.taskmanager.exception.custom.BadRequestException;

public class PasswordValidator {

    public static void validate(String password, String passwordConfirm) {

        if (password == null || password.length() < 6) {
            throw new BadRequestException("Password must be at least 6 characters long");
        }

        if (!password.matches(".*[A-Z].*")) {
            throw new BadRequestException("Password must contain at least one uppercase letter");
        }

        if (!password.matches(".*[!@#$%^&*()_+\\-={}\\[\\]:;\"'<>?,./].*")) {
            throw new BadRequestException("Password must contain at least one special character");
        }
        if(!password.equals(passwordConfirm)){
            throw new BadRequestException("Passwords do not match");
        }
    }
}

package io.github.apopovicc.taskmanager.validation;

public class PasswordValidator {

    public static void validate(String password) {

        if (password == null || password.length() < 6) {
            throw new IllegalArgumentException("Password must be at least 6 characters long");
        }

        if (!password.matches(".*[A-Z].*")) {
            throw new IllegalArgumentException("Password must contain at least one uppercase letter");
        }

        if (!password.matches(".*[!@#$%^&*()_+\\-={}\\[\\]:;\"'<>?,./].*")) {
            throw new IllegalArgumentException("Password must contain at least one special character");
        }
    }
}

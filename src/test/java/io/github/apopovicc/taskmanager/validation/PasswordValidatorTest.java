package io.github.apopovicc.taskmanager.validation;

import io.github.apopovicc.taskmanager.exception.custom.BadRequestException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class PasswordValidatorTest {
    @Test
    void testValidateNull() {
        assertThrows(BadRequestException.class, () -> PasswordValidator.validate(null, null));
    }
    @ParameterizedTest
    @ValueSource(strings = {
            "",     // prazno
            "a",    // 1 znak
            "ab",   // 2
            "abc",  // 3
            "abcd", // 4
            "abcde" // 5
    })
    void testValidateLength(String password) {
        assertThrows(BadRequestException.class, () -> PasswordValidator.validate("", ""));
    }
    @ParameterizedTest
    @ValueSource(strings = {
            "alda",
            "aldk33q",
            "alde2!",
            "Aaaa1"

    })
    void testValidateUpperCase(String password) {
        assertThrows(BadRequestException.class, () -> PasswordValidator.validate("A","A"));
    }
    @Test
    void testValidateUpperCase() {
        assertDoesNotThrow(
                () -> PasswordValidator.validate("Aaaaaaaaa1!", "Aaaaaaaaa1!")
        );
    }
    @ParameterizedTest
    @ValueSource(strings = {
            "Password1",
            "Test1234",
            "ABCDEFG1",
            "abcdefg1",
            "NoSpecialChar9"
    })
    void shouldThrowWhenPasswordHasNoSpecialCharacter(String password) {

        assertThrows(
                BadRequestException.class,
                () -> PasswordValidator.validate(password,  password)
        );}
    @Test
    void shouldNotThrowWhenPasswordHasSpecialCharacter() {
        assertDoesNotThrow(
                () -> PasswordValidator.validate("!Aaaaaa", "!Aaaaaa"));

    }
}
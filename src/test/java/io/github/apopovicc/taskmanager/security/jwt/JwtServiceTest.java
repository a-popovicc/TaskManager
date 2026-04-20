package io.github.apopovicc.taskmanager.security.jwt;

import io.github.apopovicc.taskmanager.dto.response.AuthResponse;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class JwtServiceTest {

    private final JwtService jwtService = new JwtService();

    @Test
    void shouldGenerateToken() {

        AuthResponse response =
                jwtService.generateToken("john@mail.com");

        assertNotNull(response);
        assertNotNull(response.getToken());
        assertFalse(response.getToken().isBlank());
    }
    @Test
    void shouldGenerateDifferentTokensForDifferentEmails() {
        AuthResponse token1 = jwtService.generateToken("user1@mail.com");
        AuthResponse token2 = jwtService.generateToken("user2@mail.com");

        assertNotEquals(token1.getToken(), token2.getToken());
    }
    @Test
    void shouldExtractEmailFromToken() {
        String email = "test@gmail.com";

        AuthResponse token = jwtService.generateToken(email);
        String extracted = jwtService.extractEmail(token.getToken());

        assertNotNull(extracted);
        assertEquals(email, extracted);
    }
    @Test
    void shouldValidateToken() {
        AuthResponse token = jwtService.generateToken("test@mail.com");

        assertTrue(jwtService.isTokenValid(token.getToken()));
    }



}
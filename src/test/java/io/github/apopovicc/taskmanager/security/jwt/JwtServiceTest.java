package io.github.apopovicc.taskmanager.security.jwt;

import io.github.apopovicc.taskmanager.dto.response.AuthResponse;
import io.github.apopovicc.taskmanager.security.jwt.JwtService;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class JwtServiceTest {

    private final JwtService jwtService = new JwtService();

    @Test
    void shouldGenerateToken() {

        AuthResponse response =
                jwtService.generateToken(UUID.randomUUID());

        assertNotNull(response);
        assertNotNull(response.getToken());
        assertFalse(response.getToken().isBlank());
    }
    @Test
    void shouldGenerateDifferentTokensForDifferentIDs() {
        AuthResponse token1 = jwtService.generateToken(UUID.randomUUID());
        AuthResponse token2 = jwtService.generateToken(UUID.randomUUID());

        assertNotEquals(token1.getToken(), token2.getToken());
    }
    @Test
    void shouldExtractIDFromToken() {
        UUID id = UUID.randomUUID();

        AuthResponse token = jwtService.generateToken(id);
        UUID extracted = jwtService.extractUserId(token.getToken());

        assertNotNull(extracted);
        assertEquals(id, extracted);
    }
    @Test
    void shouldValidateToken() {
        AuthResponse token = jwtService.generateToken(UUID.randomUUID());
        assertTrue(jwtService.isTokenValid(token.getToken()));
    }



}
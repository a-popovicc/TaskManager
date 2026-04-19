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

}
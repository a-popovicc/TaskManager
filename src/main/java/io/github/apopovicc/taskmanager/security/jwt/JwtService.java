package io.github.apopovicc.taskmanager.security.jwt;
import io.github.apopovicc.taskmanager.dto.response.AuthResponse;
import org.springframework.stereotype.Service;

import java.util.Base64;
import java.nio.charset.StandardCharsets;

@Service
public class JwtService {
    public AuthResponse generateToken(String email) {

        String payload = email + ":" + System.currentTimeMillis();

        String token = Base64.getEncoder()
                .encodeToString(payload.getBytes(StandardCharsets.UTF_8));

        AuthResponse response = new AuthResponse();
        response.setToken(token);

        return response;
    }
}

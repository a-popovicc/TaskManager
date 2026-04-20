package io.github.apopovicc.taskmanager.security.jwt;
import io.github.apopovicc.taskmanager.dto.response.AuthResponse;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;

@Service
public class JwtService {
    private final Key secretKey = Keys.hmacShaKeyFor(
            "my-super-secret-key-my-super-secret-key".getBytes(StandardCharsets.UTF_8)
    );

    public AuthResponse generateToken(String email) {

        String token = Jwts.builder()
                .setSubject(email)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60)) // 1h
                .signWith(secretKey, SignatureAlgorithm.HS256)
                .compact();

        AuthResponse response = new AuthResponse();
        response.setToken(token);

        return response;
    }


    // EXTRACT EMAIL
    public String extractEmail(String token) {
        return extractAllClaims(token).getSubject();
    }


    // VALIDATE TOKEN
    public boolean isTokenValid(String token) {
        try {
            Claims claims = extractAllClaims(token);

            return !isTokenExpired(claims);
        } catch (Exception e) {
            return false;
        }
    }


    // EXTRACT ALL CLAIMS
    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(secretKey)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }


    // CHECK EXPIRATION
    private boolean isTokenExpired(Claims claims) {
        return claims.getExpiration().before(new Date());
    }
}

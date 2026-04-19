package io.github.apopovicc.taskmanager.service.auth;


import io.github.apopovicc.taskmanager.dto.request.LoginRequest;
import io.github.apopovicc.taskmanager.dto.request.SignupRequest;
import io.github.apopovicc.taskmanager.dto.response.AuthResponse;
import io.github.apopovicc.taskmanager.model.User;
import io.github.apopovicc.taskmanager.repository.UserRepository;
import io.github.apopovicc.taskmanager.security.jwt.JwtService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

abstract class AuthServiceTest {

    protected AuthService authService;

    protected UserRepository userRepository;
    protected JwtService jwtService;
    protected PasswordEncoder passwordEncoder;

    protected abstract AuthService getInstance();

    @BeforeEach
    void setUp() {
        authService = getInstance();
    }
    @AfterEach
    void tearDown() {
        authService = null;
    }
    @Test
    void shouldThrowExceptionWhenUserAlreadyExists() {

        SignupRequest request = new SignupRequest();
        request.setEmail("test@test.com");
        request.setPassword("Password123!");

        when(userRepository.findByEmail(request.getEmail()))
                .thenReturn(Optional.of(new User()));

        assertThrows(RuntimeException.class,
                () -> authService.signUp(request));
    }
    @Test
    void shouldRegisterUserAndReturnToken() {

        SignupRequest request = new SignupRequest();
        request.setEmail("test@test.com");
        request.setPassword("Password123!");

        // 1. user ne postoji
        when(userRepository.findByEmail(request.getEmail()))
                .thenReturn(Optional.empty());

        // 2. password encoder
        when(passwordEncoder.encode(anyString()))
                .thenReturn("encodedPassword");

        // 3. jwt token
        AuthResponse expected = new AuthResponse("token");

        when(jwtService.generateToken(request.getEmail()))
                .thenReturn(expected);

        // ACT
        AuthResponse response = authService.signUp(request);

        // ASSERT
        assertNotNull(response);
        assertEquals(expected, response);

        // proveri da je user snimljen
        verify(userRepository).saveUser(any(User.class));
    }
    @Test
    void shouldThrowIfPasswordInvalid() {

        SignupRequest request = new SignupRequest();
        request.setEmail("test@test.com");
        request.setPassword("123"); // invalid

        when(userRepository.findByEmail(request.getEmail()))
                .thenReturn(Optional.empty());

        assertThrows(IllegalArgumentException.class,
                () -> authService.signUp(request));
    }
    @Test
    void shouldThrowExceptionWhenUserDoesNotExist() {
        LoginRequest request = new LoginRequest();
        request.setEmail("test@test.com");
        request.setPassword("Password123!");

        when(userRepository.findByEmail(request.getEmail()))
                .thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, ()-> authService.login(request));
    }
    @Test
    void shouldThrowExceptionWhenPasswordDoesNotMatch() {
        LoginRequest request = new LoginRequest();
        request.setEmail("test@test.com");
        request.setPassword("Password123!");

        User user = new User();
        user.setEmail(request.getEmail());
        user.setPassword("encodedPassword");

        // 1. user postoji
        when(userRepository.findByEmail(request.getEmail()))
                .thenReturn(Optional.of(user));

        // 2. password mismatch
        when(passwordEncoder.matches(
                request.getPassword(),
                user.getPassword()
        )).thenReturn(false);

        // 3. test
        assertThrows(RuntimeException.class,
                () -> authService.login(request));
    }
    @Test
    void shouldReturnAuthResponse() {
        LoginRequest request = new LoginRequest();
        request.setEmail("test@test.com");
        request.setPassword("Password123!");

        User user = new User();
        user.setEmail(request.getEmail());
        user.setPassword("encodedPassword");

        when(userRepository.findByEmail(request.getEmail()))
        .thenReturn(Optional.of(user));
        when(passwordEncoder.matches(request.getPassword(), user.getPassword()))
        .thenReturn(true);

        AuthResponse expected = new AuthResponse("token");
        when(jwtService.generateToken(user.getEmail()))
        .thenReturn(expected);

        AuthResponse response = authService.login(request);

        assertNotNull(response);
        assertEquals(expected, response);
    }
}
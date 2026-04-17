package io.github.apopovicc.taskmanager.service.auth;

import io.github.apopovicc.taskmanager.dto.request.LoginRequest;
import io.github.apopovicc.taskmanager.dto.request.SignupRequest;
import io.github.apopovicc.taskmanager.dto.response.AuthResponse;

public interface AuthService {
       AuthResponse signUp(SignupRequest request);
       AuthResponse login(LoginRequest request);
}

package io.github.apopovicc.taskmanager.service.user;

import io.github.apopovicc.taskmanager.dto.response.UserMeResponse;
import io.github.apopovicc.taskmanager.mapper.UserMapper;
import io.github.apopovicc.taskmanager.model.User;
import io.github.apopovicc.taskmanager.repository.UserRepository;
import io.github.apopovicc.taskmanager.security.jwt.JwtService;

import java.util.Optional;
import java.util.UUID;


public class UserService {

    private final UserRepository userRepository;
    private final JwtService jwtService;

    public UserService(UserRepository userRepository, JwtService jwtService) {
        this.userRepository = userRepository;
        this.jwtService = jwtService;
    }

    public UserMeResponse getMe(String token) {
        if(!jwtService.isTokenValid(token)) {
            throw new RuntimeException("Invalid token");
        }
        UUID id = jwtService.extractUserId(token);
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return UserMapper.userToDTO(user);
    }
}

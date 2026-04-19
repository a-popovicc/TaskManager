package io.github.apopovicc.taskmanager.service.auth;

import io.github.apopovicc.taskmanager.dto.request.LoginRequest;
import io.github.apopovicc.taskmanager.dto.request.SignupRequest;
import io.github.apopovicc.taskmanager.dto.response.AuthResponse;
import io.github.apopovicc.taskmanager.mapper.UserMapper;
import io.github.apopovicc.taskmanager.model.User;
import io.github.apopovicc.taskmanager.repository.UserRepository;
import io.github.apopovicc.taskmanager.security.jwt.JwtService;
import io.github.apopovicc.taskmanager.validation.PasswordValidator;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    private final JwtService jwtService;
    private final UserRepository userRepository;
    private  final PasswordEncoder passwordEncoder;

    public AuthServiceImpl(UserRepository userRepository, JwtService jwtService,  PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.jwtService = jwtService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public AuthResponse signUp(SignupRequest request) {

        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new RuntimeException("User already exists");
        }

        try {
            PasswordValidator.validate(request.getPassword());
        }catch (Exception e) {
            throw new IllegalArgumentException(e);
        }


        User user = UserMapper.toEntity(request);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.saveUser(user);

        return jwtService.generateToken(user.getEmail());
    }

    @Override
    public AuthResponse login(LoginRequest request) {
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("User doesn't exist"));
 
        if (!passwordEncoder.matches(
                request.getPassword(),
                user.getPassword()))
        {
            throw new RuntimeException("Password doesn't match");
        }
        return jwtService.generateToken(user.getEmail());
    }
}

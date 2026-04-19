package io.github.apopovicc.taskmanager.service.auth;

import io.github.apopovicc.taskmanager.repository.UserRepository;
import io.github.apopovicc.taskmanager.security.jwt.JwtService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
class AuthServiceImplTest extends AuthServiceTest{

    @Mock
    UserRepository userRepository;

    @Mock
    JwtService jwtService;

    @Mock
    PasswordEncoder passwordEncoder;

    @BeforeEach
    void setUp() {

        MockitoAnnotations.openMocks(this);

        super.userRepository = this.userRepository;
        super.jwtService = this.jwtService;
        super.passwordEncoder = this.passwordEncoder;

        authService = new AuthServiceImpl(
                userRepository,
                jwtService,
                passwordEncoder
        );
    }

    @Override
    protected AuthService getInstance() {
        return authService;
    }
}
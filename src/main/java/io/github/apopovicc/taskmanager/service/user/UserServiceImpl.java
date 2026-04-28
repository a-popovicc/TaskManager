package io.github.apopovicc.taskmanager.service.user;

import io.github.apopovicc.taskmanager.dto.response.UserMeResponse;
import io.github.apopovicc.taskmanager.exception.custom.ResourcesNotFoundException;
import io.github.apopovicc.taskmanager.mapper.UserMapper;
import io.github.apopovicc.taskmanager.model.User;
import io.github.apopovicc.taskmanager.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    @Override
    public UserMeResponse getMe(UUID userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourcesNotFoundException("User not found"));

        return UserMapper.userToDTO(user);
    }
}

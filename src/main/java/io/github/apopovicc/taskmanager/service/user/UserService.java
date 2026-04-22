package io.github.apopovicc.taskmanager.service.user;

import io.github.apopovicc.taskmanager.dto.response.UserMeResponse;
import io.github.apopovicc.taskmanager.model.User;

import java.util.UUID;

public interface UserService {
    UserMeResponse getMe(UUID userId);
}

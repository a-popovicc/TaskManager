package io.github.apopovicc.taskmanager.repository;

import io.github.apopovicc.taskmanager.model.User;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository {
    Optional<User> findByEmail(String email);
    void saveUser(User user);
    Optional<User> findById(UUID id);
}

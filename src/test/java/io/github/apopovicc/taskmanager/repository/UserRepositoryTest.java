package io.github.apopovicc.taskmanager.repository;

import io.github.apopovicc.taskmanager.model.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

abstract class UserRepositoryTest {

    protected UserRepository repository;
    protected abstract UserRepository getInstance();

    @BeforeEach
    void setUp() {
        repository = getInstance();
    }
    @AfterEach
    void tearDown() {
        repository = null;
    }

    @Test
    void shouldSaveUser() {
        // arrange
        User user = new User();
        user.setEmail("test@test.com");
        user.setPassword("password");
        // act
        repository.saveUser(user);

        // assert
        Optional<User> result = repository.findByEmail("test@test.com");
        assertTrue(result.isPresent());
    }

    @Test
    void shouldFindUserByEmail() {
        User user = new User();
        user.setEmail("test@test.com");
        user.setPassword("password");
        repository.saveUser(user);

        Optional<User> result = repository.findByEmail("test@test.com");

        assertTrue(result.isPresent());
        assertEquals("test@test.com", result.get().getEmail());
    }

    @Test
    void shouldReturnEmptyWhenUserDoesNotExist() {
        Optional<User> result = repository.findByEmail("unknown@test.com");

        assertTrue(result.isEmpty());
    }
}
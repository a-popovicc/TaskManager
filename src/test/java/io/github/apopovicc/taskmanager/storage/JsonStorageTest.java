package io.github.apopovicc.taskmanager.storage;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.apopovicc.taskmanager.model.User;
import io.github.apopovicc.taskmanager.storage.JsonStorage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class JsonStorageTest {

    private static final String TEST_PATH = "data/test-users.json";

    private JsonStorage storage;

    @BeforeEach
    void setUp() {
        ObjectMapper mapper = new ObjectMapper();

        storage = new JsonStorage(
                mapper,
                TEST_PATH
        );
    }

    @AfterEach
    void cleanup() throws IOException {
        Files.deleteIfExists(Paths.get(TEST_PATH));
    }

    @Test
    void shouldReturnEmptyListWhenFileDoesNotExist() {

        List<User> users = storage.loadUsers();

        assertNotNull(users);
        assertTrue(users.isEmpty());
    }

    @Test
    void shouldSaveAndLoadUsersCorrectly() {

        User user = new User();
        user.setId(UUID.randomUUID());
        user.setFirstName("John");
        user.setLastName("Doe");
        user.setEmail("john@mail.com");

        storage.saveUsers(List.of(user));

        List<User> loaded = storage.loadUsers();

        assertNotNull(loaded);
        assertEquals(1, loaded.size());
        assertEquals("John", loaded.get(0).getFirstName());
        assertEquals("Doe", loaded.get(0).getLastName());
        assertEquals("john@mail.com", loaded.get(0).getEmail());
    }

    @Test
    void shouldCreateFileWhenSavingUsers() {

        User user = new User();
        user.setId(UUID.randomUUID());
        user.setEmail("test@mail.com");

        storage.saveUsers(List.of(user));

        assertTrue(Files.exists(Paths.get(TEST_PATH)));
    }

    @Test
    void shouldHandleNullOrEmptyUsers() {

        storage.saveUsers(List.of());

        List<User> loaded = storage.loadUsers();

        assertNotNull(loaded);
        assertTrue(loaded.isEmpty());
    }
}
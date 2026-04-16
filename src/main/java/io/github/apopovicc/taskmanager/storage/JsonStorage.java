package io.github.apopovicc.taskmanager.storage;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.apopovicc.taskmanager.model.User;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Component
public class JsonStorage {

    private final ObjectMapper objectMapper;
    private final String FILE_PATH = "data/users.json";

    public JsonStorage(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    // 📖 READ
    public List<User> loadUsers() {
        try {
            ClassPathResource resource = new ClassPathResource(FILE_PATH);

            if (!resource.exists()) {
                return new ArrayList<>();
            }

            InputStream inputStream = resource.getInputStream();

            List<User> users = objectMapper.readValue(
                    inputStream,
                    new TypeReference<List<User>>() {}
            );

            return users != null ? users : new ArrayList<>();

        } catch (IOException e) {
            throw new RuntimeException("Failed to load users from JSON", e);
        }
    }

    // 💾 WRITE
    public void saveUsers(List<User> users) {
        try {
            ClassPathResource resource = new ClassPathResource(FILE_PATH);

            File file = resource.getFile();

            objectMapper.writerWithDefaultPrettyPrinter()
                    .writeValue(file, users);

        } catch (IOException e) {
            throw new RuntimeException("Failed to save users to JSON", e);
        }
    }
}

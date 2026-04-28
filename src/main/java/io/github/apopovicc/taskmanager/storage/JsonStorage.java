package io.github.apopovicc.taskmanager.storage;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.apopovicc.taskmanager.exception.custom.DataLoadException;
import io.github.apopovicc.taskmanager.model.User;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;


public class JsonStorage {

    private final ObjectMapper objectMapper;
    private final String filePath;

    public JsonStorage(ObjectMapper objectMapper, String filePath) {
        this.objectMapper = objectMapper;
        this.filePath = filePath;
    }


    public List<User> loadUsers() {
        try {
            Path path = Paths.get(filePath);

            if (Files.notExists(path)) {
                return new ArrayList<>();
            }

            byte[] bytes = Files.readAllBytes(path);

            List<User> users = objectMapper.readValue(
                    bytes,
                    new TypeReference<List<User>>() {}
            );

            return users != null ? users : new ArrayList<>();

        } catch (IOException e) {
            throw new DataLoadException("Failed to load users from JSON", e);
        }
    }


    public void saveUsers(List<User> users) {
        try {
            Path path = Paths.get(filePath);

            // kreira folder ako ne postoji
            if (Files.notExists(path.getParent())) {
                Files.createDirectories(path.getParent());
            }

            // kreira fajl ako ne postoji
            if (Files.notExists(path)) {
                Files.createFile(path);
            }

            byte[] json = objectMapper
                    .writerWithDefaultPrettyPrinter()
                    .writeValueAsBytes(users);

            Files.write(path, json);

        } catch (IOException e) {
            throw new DataLoadException("Failed to save users to JSON", e);
        }
    }
}
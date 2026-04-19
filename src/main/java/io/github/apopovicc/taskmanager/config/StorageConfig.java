package io.github.apopovicc.taskmanager.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.apopovicc.taskmanager.storage.JsonStorage;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StorageConfig {

    @Bean
    public JsonStorage jsonStorage(ObjectMapper mapper) {
        return new JsonStorage(
                mapper,
                "data/users.json"
        );
    }
}

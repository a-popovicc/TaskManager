package io.github.apopovicc.taskmanager.mapper;

import io.github.apopovicc.taskmanager.dto.request.SignupRequest;
import io.github.apopovicc.taskmanager.dto.response.UserMeResponse;
import io.github.apopovicc.taskmanager.model.User;

import java.util.ArrayList;
import java.util.UUID;

public class UserMapper {
    // ENTITY → DTO (me response)
    public static UserMeResponse userToDTO(User user) {
        if (user == null) return null;

        UserMeResponse dto = new UserMeResponse();

        dto.setId(user.getId());
        dto.setFirstName(user.getFirstName());
        dto.setLastName(user.getLastName());
        dto.setEmail(user.getEmail());
        dto.setTasks(user.getTasks()
                .stream()
                .map(TaskMapper::toDTO)
                .toList());

        return dto;
    }
    public static User toEntity(SignupRequest dto) {
        if (dto == null) return null;

        User user = new User();

        user.setId(UUID.randomUUID());
        user.setFirstName(dto.getFirstName());
        user.setLastName(dto.getLastName());
        user.setEmail(dto.getEmail());
        // ⚠️ OBAVEZNO hashuj password (NE čuvaj plain text)
        user.setPassword(dto.getPassword());

        user.setTasks(new ArrayList<>());

        return user;
    }


}

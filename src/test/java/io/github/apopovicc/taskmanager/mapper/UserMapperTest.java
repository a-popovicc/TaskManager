package io.github.apopovicc.taskmanager.mapper;

import io.github.apopovicc.taskmanager.dto.request.SignupRequest;
import io.github.apopovicc.taskmanager.dto.response.UserMeResponse;
import io.github.apopovicc.taskmanager.model.Task;
import io.github.apopovicc.taskmanager.model.User;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class UserMapperTest {

    @Test
    void shouldMapUserToUserMeResponse() {

        User user = new User();
        user.setId(UUID.randomUUID());
        user.setFirstName("John");
        user.setLastName("Doe");
        user.setEmail("john@mail.com");

        Task task = new Task();
        task.setId(UUID.randomUUID());
        task.setTitle("Test task");

        user.setTasks(List.of(task));


        UserMeResponse dto = UserMapper.userToDTO(user);


        assertNotNull(dto);
        assertEquals(user.getId(), dto.getId());
        assertEquals("John", dto.getFirstName());
        assertEquals("Doe", dto.getLastName());
        assertEquals("john@mail.com", dto.getEmail());

        assertNotNull(dto.getTasks());
        assertEquals(1, dto.getTasks().size());
    }
    @Test
    void shouldReturnNullWhenUserIsNull() {
        assertNull(UserMapper.userToDTO(null));
    }
    @Test
    void shouldMapSignupRequestToUserEntity() {
        // given
        SignupRequest dto = new SignupRequest();
        dto.setFirstName("John");
        dto.setLastName("Doe");
        dto.setEmail("john@mail.com");
        dto.setPassword("1234");

        // when
        User user = UserMapper.toEntity(dto);

        // then
        assertNotNull(user);
        assertNotNull(user.getId()); // UUID generisan
        assertEquals("John", user.getFirstName());
        assertEquals("Doe", user.getLastName());
        assertEquals("john@mail.com", user.getEmail());
        assertEquals("1234", user.getPassword());
        assertNotNull(user.getTasks());
        assertTrue(user.getTasks().isEmpty());
    }
    @Test
    void shouldReturnNullWhenSignupRequestIsNull() {
        assertNull(UserMapper.toEntity(null));
    }
}
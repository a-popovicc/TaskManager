package io.github.apopovicc.taskmanager.service.task;

import io.github.apopovicc.taskmanager.dto.request.TaskRequest;
import io.github.apopovicc.taskmanager.dto.response.TaskDTO;
import io.github.apopovicc.taskmanager.model.Task;
import io.github.apopovicc.taskmanager.model.User;
import io.github.apopovicc.taskmanager.repository.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
abstract class TaskServiceTest {
    @Mock
    protected UserRepository userRepository;

    protected TaskService taskService;
    protected abstract TaskService getInstance();

    @BeforeEach
    void setup() {
        taskService = getInstance();
    }
    @AfterEach
    void tearDown() {
        taskService = null;
    }
    // helpers

    protected User createUser() {
        User user = new User();
        user.setId(UUID.randomUUID());
        user.setTasks(new ArrayList<>());
        return user;
    }

    protected Task createTask() {
        Task task = new Task();
        task.setId(UUID.randomUUID());
        task.setTitle("test");
        task.setCompleted(false);
        task.setDueDate(LocalDateTime.now());
        return task;
    }
    @Test
    void shouldAddTaskToUser() {
        User user =  createUser();

        TaskRequest request = new TaskRequest();
        request.setTitle("Task 1");
        request.setDescription("Desc");
        request.setDueDate(LocalDateTime.now());

        when(userRepository.findById(user.getId()))
                .thenReturn(Optional.of(user));

        List<TaskDTO> result = taskService.addTask(user.getId(), request);

        assertEquals(1, user.getTasks().size());
        assertEquals(1, result.size());
        assertEquals("Task 1", result.get(0).getTitle());

        verify(userRepository).findById(user.getId());
        verify(userRepository).saveUser(user);
    }
    @Test
    void shouldRemoveTask() {

        User user = createUser();
        Task task = createTask();

        user.getTasks().add(task);

        when(userRepository.findById(user.getId()))
                .thenReturn(Optional.of(user));

        taskService.removeTask(user.getId(), task.getId());

        assertTrue(user.getTasks().isEmpty());
    }
    @Test
    void shouldEditTask() {

        User user = createUser();
        Task task = createTask();

        user.getTasks().add(task);

        TaskRequest request = new TaskRequest();
        request.setTitle("new");

        when(userRepository.findById(user.getId()))
                .thenReturn(Optional.of(user));

        taskService.editTask(user.getId(), task.getId(), request);

        assertEquals("new", task.getTitle());
    }
    @Test
    void shouldCompleteTask() {

        User user = createUser();
        Task task = createTask();

        user.getTasks().add(task);

        when(userRepository.findById(user.getId()))
                .thenReturn(Optional.of(user));

        taskService.markTaskCompleted(user.getId(), task.getId());

        assertTrue(task.isCompleted());
    }
}
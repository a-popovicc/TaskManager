package io.github.apopovicc.taskmanager.service.task;

import io.github.apopovicc.taskmanager.dto.request.AddTaskRequest;
import io.github.apopovicc.taskmanager.dto.response.TaskDTO;
import io.github.apopovicc.taskmanager.mapper.TaskMapper;
import io.github.apopovicc.taskmanager.model.Task;
import io.github.apopovicc.taskmanager.model.User;
import io.github.apopovicc.taskmanager.repository.UserRepository;
import io.github.apopovicc.taskmanager.security.jwt.JwtService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;

@Service
public class TaskServiceImpl implements TaskService {

    private final UserRepository userRepository;
    private final JwtService jwtService;

    public TaskServiceImpl(UserRepository userRepository, JwtService jwtService) {
        this.userRepository = userRepository;
        this.jwtService = jwtService;
    }

    @Override
    public List<TaskDTO> addTask(UUID userId, AddTaskRequest request) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        Task newTask = TaskMapper.dtoTaskRequestToTask(request);

        if (user.getTasks() == null) {
            user.setTasks(new ArrayList<>());
        }

        user.getTasks().add(newTask);
        userRepository.saveUser(user);

        return user.getTasks()
                .stream()
                .sorted(Comparator.comparing(Task::getDueDate))
                .map(TaskMapper::toDTO)
                .toList();
    }

    @Override
    public List<TaskDTO> removeTask(UUID idUser, UUID idTask) {
        User  user = userRepository.findById(idUser)
                .orElseThrow(() -> new RuntimeException("User not found"));
        /*
        for (int i = 0; i < user.getTasks().size(); i++) {
             if (tasks.get(i).getId().equals(taskId)) {
                tasks.remove(i);
                break;
            }
        }
         */
        user.getTasks().removeIf(task -> task.getId().equals(idTask));
        userRepository.saveUser(user);
        return user.getTasks()
                .stream()
                .sorted(Comparator.comparing(Task::getDueDate))
                .map(TaskMapper::toDTO)
                .toList();
    }

    @Override
    public List<TaskDTO> editTask(UUID idUser, UUID idTask, AddTaskRequest request) {
        User user = userRepository.findById(idUser)
                .orElseThrow(() -> new RuntimeException("User not found"));
        Task existingTask= user.getTasks()
                .stream()
                .filter(task -> task.getId().equals(idTask))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Task not found"));

        existingTask.setTitle(request.getTitle());
        existingTask.setDescription(request.getDescription());
        existingTask.setDueDate(request.getDueDate());

        userRepository.saveUser(user);
        return user.getTasks()
                .stream()
                .sorted(Comparator.comparing(Task::getDueDate))
                .map(TaskMapper::toDTO)
                .toList();
    }
}

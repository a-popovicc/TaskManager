package io.github.apopovicc.taskmanager.service.task;

import io.github.apopovicc.taskmanager.dto.request.AddTaskRequest;
import io.github.apopovicc.taskmanager.dto.response.TaskDTO;

import java.util.List;
import java.util.UUID;

public interface TaskService {
    List<TaskDTO> addTask(UUID id,AddTaskRequest request);
    List<TaskDTO> removeTask(UUID idUser,  UUID idTask);
    List<TaskDTO> editTask(UUID idUser,  UUID idTask, AddTaskRequest request);
    List<TaskDTO> markTaskCompleted(UUID idUser,  UUID idTask);
 }

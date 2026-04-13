package io.github.apopovicc.taskmanager.mapper;

import io.github.apopovicc.taskmanager.dto.request.AddTaskRequest;
import io.github.apopovicc.taskmanager.dto.response.TaskDTO;
import io.github.apopovicc.taskmanager.model.Task;

import java.util.UUID;

public class TaskMapper {
    //DTO-model
    public static Task dtoToTask(TaskDTO taskDTO) {

        if (taskDTO == null) return null;

        Task task = new Task();
        task.setId(taskDTO.getId());
        task.setTitle(taskDTO.getTitle());
        task.setDescription(taskDTO.getDescription());
        task.setCompleted(taskDTO.isCompleted());
        task.setDueDate(taskDTO.getDueDate());

        return task;
    }

    public static Task dtoTaskRequestToTask(AddTaskRequest addTaskRequest) {
        if (addTaskRequest == null) return null;

        Task task = new Task();

        task.setId(UUID.randomUUID());
        task.setTitle(addTaskRequest.getTitle());
        task.setDescription(addTaskRequest.getDescription());
        task.setCompleted(false);
        task.setDueDate(addTaskRequest.getDueDate());

        return task;
    }
    //model-DTO\
    public static TaskDTO toDTO(Task task) {

        if (task == null) return null;

        TaskDTO taskDTO = new TaskDTO();

        taskDTO.setId(task.getId());
        taskDTO.setTitle(task.getTitle());
        taskDTO.setDescription(task.getDescription());
        taskDTO.setCompleted(task.isCompleted());
        taskDTO.setDueDate(task.getDueDate());

        return  taskDTO;
    }

}

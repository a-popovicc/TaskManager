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
    //DTO-model, request mapping
    public static Task addTaskRequestToTask(AddTaskRequest addTaskRequest) {
        if (addTaskRequest == null) return null;

        Task task = new Task();

        task.setId(UUID.randomUUID());
        task.setTitle(addTaskRequest.getTitle());
        task.setDescription(addTaskRequest.getDescription());
        task.setCompleted(false);
        task.setDueDate(addTaskRequest.getDueDate());

        return task;
    }
    //model-DTO, response mapping
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
    //edit request->dto-model
    public static void editRequestToTask(AddTaskRequest addTaskRequest, Task existingTask) {
        if(existingTask == null || addTaskRequest == null)
            throw new RuntimeException("Add task request or add task request null");

        existingTask.setTitle(addTaskRequest.getTitle());
        existingTask.setDescription(addTaskRequest.getDescription());
        existingTask.setDueDate(addTaskRequest.getDueDate());
    }

}

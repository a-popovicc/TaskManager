package io.github.apopovicc.taskmanager.controller.task;

import io.github.apopovicc.taskmanager.dto.request.TaskRequest;
import io.github.apopovicc.taskmanager.dto.response.TaskDTO;
import io.github.apopovicc.taskmanager.model.User;
import io.github.apopovicc.taskmanager.service.task.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/tasks")
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;

    @PostMapping("/add")
    public ResponseEntity<List<TaskDTO>> addTask(
            @AuthenticationPrincipal User user,
            @RequestBody TaskRequest request) {

        return ResponseEntity.ok(taskService.addTask(user.getId(), request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<List<TaskDTO>> removeTask(
            @AuthenticationPrincipal User user,
            @PathVariable UUID id){
        return ResponseEntity.ok(taskService.removeTask(user.getId(), id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<List<TaskDTO>> editTask(
            @AuthenticationPrincipal User user,
            @PathVariable UUID id,
            @RequestBody TaskRequest request) {

        return ResponseEntity.ok(taskService.editTask(user.getId(), id, request));
    }
    @PatchMapping("/{id}")
    public ResponseEntity<List<TaskDTO>> markTaskCompleted(
            @AuthenticationPrincipal User user,
            @PathVariable UUID id) {

        return ResponseEntity.ok(taskService.markTaskCompleted(user.getId(), id));
    }


}

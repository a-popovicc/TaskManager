package io.github.apopovicc.taskmanager.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddTaskRequest {
    private String taskName;
    private String taskDescription;
    private LocalDateTime taskDueDate;
}

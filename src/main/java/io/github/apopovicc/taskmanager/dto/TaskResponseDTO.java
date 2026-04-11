package io.github.apopovicc.taskmanager.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public class TaskResponseDTO {

    private UUID id;

    private String title;

    private String description;

    private boolean completed;

    private LocalDateTime dueDate;
}

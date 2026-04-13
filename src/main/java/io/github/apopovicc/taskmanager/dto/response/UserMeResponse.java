package io.github.apopovicc.taskmanager.dto.response;

import io.github.apopovicc.taskmanager.model.Task;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserMeResponse {
    private String firstName;
    private String lastName;
    private String email;
    private List<Task> tasks;
}

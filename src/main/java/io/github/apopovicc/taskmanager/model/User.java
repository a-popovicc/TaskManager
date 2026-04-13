package io.github.apopovicc.taskmanager.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {

    private UUID id;

    private String firstName;

    private String lastName;

    private String email;

    private String password;

    private List<Task> tasks;

}

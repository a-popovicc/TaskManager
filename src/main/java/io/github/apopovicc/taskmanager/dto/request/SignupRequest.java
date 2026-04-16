package io.github.apopovicc.taskmanager.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SignupRequest {

    @NotBlank(message = "First name cannot be empty")
    @Pattern(
            regexp = "^[A-Z].*",
            message = "First name must start with a capital letter"
    )
    private String firstName;

    @NotBlank(message = "Last name cannot be empty")
    @Pattern(
            regexp = "^[A-Z].*",
            message = "Last name must start with a capital letter"
    )
    private String lastName;

    @NotBlank(message = "Email cannot be empty")
    @Email(message = "Email format is invalid")
    private String email;

    @NotBlank(message = "Password cannot be empty")
    private String password;

    @NotBlank(message = "Password-confirm cannot be empty")
    private String confirmPassword;
}

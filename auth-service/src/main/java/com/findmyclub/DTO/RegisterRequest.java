package com.findmyclub.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class RegisterRequest {
    // will be in the service: - username unique; - email unique; -password complexity: must contain letter + number + symbol
    @NotBlank(message = "Username cannot be empty")
    @Size(max = 30, message = "Username cannot exceed 30 characters")
    @Pattern(
            regexp = "^[A-Za-z].*",
            message = "Username must start with a letter"
    )
    private String username;

    @NotBlank(message = "Email cannot be empty")
    @Email(message = "Email must be valid")
    private String email;

    @NotBlank(message = "Password cannot be empty")
    @Size(min = 8, max = 50, message = "Password must be between 8 and 50 characters")
    private String password;

    public RegisterRequest() {} //needs for JSON deserialization

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

}

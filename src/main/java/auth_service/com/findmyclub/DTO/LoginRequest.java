package auth_service.com.findmyclub.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class LoginRequest {

    @NotBlank(message = "Email cannot be empty")
    @Email(message = "Email must be valid")
    private String email;

    @NotBlank(message = "Password cannot be empty")
    private String password;

    public LoginRequest() {}

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}

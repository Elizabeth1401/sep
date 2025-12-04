package auth_service.com.findmyclub.controller;

import auth_service.com.findmyclub.DTO.LoginRequest;
import auth_service.com.findmyclub.DTO.RegisterRequest;
import auth_service.com.findmyclub.DTO.UserDTO;
import auth_service.com.findmyclub.model.User;
import auth_service.com.findmyclub.repositories.UserRepository;
import auth_service.com.findmyclub.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "http://localhost:5173")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    //Registration endpoint

    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody RegisterRequest request){
        authService.register(request);
        return ResponseEntity.status(201).body("User successfully registered");
    }

    //Login endpoint
    @PostMapping ("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginRequest request){
        User user = authService.login(request);
        return ResponseEntity.ok("Login successful");
    }

}

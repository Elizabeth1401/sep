package com.findmyclub.service;

import com.findmyclub.DTO.LoginRequest;
import com.findmyclub.model.User;
import com.findmyclub.repositories.UserRepository;
import com.findmyclub.security.JWTService;
import jakarta.validation.Valid;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JWTService jwtService;

    public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder, JWTService jwtService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    // REGISTRATION
    public void register(
        com.findmyclub.DTO.@Valid RegisterRequest request){
        validateUsername(request.getUsername());
        validateEmail(request.getEmail());
        validatePassword(request.getPassword());

        String hash = passwordEncoder.encode(request.getPassword());

        User user = new User(
                request.getUsername(),
                request.getEmail(),
                hash
        );
        userRepository.save(user);
    }

    // LOGIN
    public String login(@Valid LoginRequest request){
        User user =  userRepository.findByEmail(request.getEmail())
                .filter(u -> passwordEncoder.matches(request.getPassword(), u.getPasswordHash()))
                .orElseThrow(()-> new IllegalArgumentException("Invalid email or password"));
        String token = jwtService.generateToken(user);

        return token;
    }

    //--------------
    // VALIDATION
    //--------------
    private void validateUsername(String username){
        if (userRepository.existsByUsername(username)){
            throw new IllegalArgumentException("Username is already taken");
        }
    }
    private void validateEmail(String email){
        if (userRepository.existsByEmail(email)){
            throw new IllegalArgumentException("Email is already registered");
        }
    }
    private void validatePassword(String password){
        boolean hasLetter = password.matches(".*[A-Za-z].*");
        boolean hasDigit = password.matches(".*[0-9].*");
        boolean hasSymbol = password.matches(".*[A-Za-z].*");

        if (!hasLetter && !hasDigit && !hasSymbol){
            throw new IllegalArgumentException("Password must contain letters, numbers, and symbols");
        }
    }
}

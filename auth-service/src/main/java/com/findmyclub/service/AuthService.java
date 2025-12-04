package com.findmyclub.service;

import com.findmyclub.DTO.LoginRequest;
import com.findmyclub.DTO.RegisterRequest;
import com.findmyclub.model.User;
import com.findmyclub.repositories.UserRepository;
import jakarta.validation.Valid;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    //--------------
    // REGISTRATION
    //--------------

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

    //--------------
    // LOGIN
    //--------------

    public User login(com.findmyclub.DTO.@Valid LoginRequest request){
        return userRepository.findByEmail(request.getEmail())
                .filter(user -> passwordEncoder.matches(request.getPassword(), user.getPasswordHash()))
                .orElseThrow(()-> new IllegalArgumentException("Invalid email or password"));
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

package com.findmyclub.controller;

import com.findmyclub.DTO.UserDTO;
import com.findmyclub.model.User;
import com.findmyclub.repositories.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "http://localhost:5173")
public class UserController {
    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    //create a new user
    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public User create(@RequestBody UserDTO userDTO) {
        User user = new User(userDTO.username, userDTO.password);
        //for now we do not encrypt username and password it will be in the future
        return userRepository.save(user);
    }

    //login
    @PostMapping ("/login")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<String> login(@RequestBody UserDTO userDTO) {
        var user  = userRepository.findByUsername(userDTO.username);
        if (user.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
        }

        if (user.get().getPassword().equals(userDTO.password)) {
            return ResponseEntity.ok("Logged in");
        }

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

}

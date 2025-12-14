package com.findmyclub.controller;

import com.findmyclub.DTO.LoginRequest;
import com.findmyclub.DTO.RegisterRequest;
import com.findmyclub.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController @RequestMapping("/auth") @CrossOrigin(origins = "http://localhost:5173") public class AuthController
{

  private final AuthService authService;

  public AuthController(AuthService authService)
  {
    this.authService = authService;
  }

  //Registration endpoint

  @PostMapping("/register") public ResponseEntity<String> register(
      @Valid @RequestBody RegisterRequest request)
  {
    try
    {
      authService.register(request);
      return ResponseEntity.status(201).body("User successfully registered");
    }
    catch (IllegalArgumentException e)
    {
      return ResponseEntity.status(409).body(e.getMessage());
    }
  }

  //Login endpoint
  @PostMapping("/login") public ResponseEntity<String> login(
      @Valid @RequestBody LoginRequest request)
  {
    try
    {
      String token = authService.login(request);
      return ResponseEntity.ok(token);
    }
    catch (IllegalArgumentException e)
    {
      return ResponseEntity.status(401) // Unauthorized
          .body(e.getMessage());
    }
  }

}

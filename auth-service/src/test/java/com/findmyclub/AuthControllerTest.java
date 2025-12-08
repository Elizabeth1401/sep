package com.findmyclub;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.findmyclub.DTO.LoginRequest;
import com.findmyclub.DTO.RegisterRequest;
import com.findmyclub.controller.AuthController;
import com.findmyclub.service.AuthService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * AuthController tests following ZOMBIES principles:
 * Z - Zero (empty, null cases)
 * O - One (single, simple case)
 * M - Many (multiple items)
 * B - Boundaries (edge cases, limits)
 * I - Interfaces (interaction with service layer)
 * E - Exceptions (error conditions)
 * S - Simple scenarios (happy path)
 */
@WebMvcTest(AuthController.class) class AuthControllerTest
{

  @Autowired private MockMvc mockMvc;

  @MockBean private AuthService authService;

  @Autowired private ObjectMapper objectMapper;

  // ==================== REGISTER ENDPOINT TESTS ====================

  @Nested @DisplayName("POST /auth/register - ZOMBIES") class RegisterEndpointTests
  {

    // S - Simple scenarios (happy path first)
    @Test @DisplayName("S - Should return 201 when registration succeeds") void simpleCase_SuccessfulRegistration()
        throws Exception
    {
      RegisterRequest request = createRegisterRequest("john",
          "john@example.com", "Pass123!@#");
      doNothing().when(authService).register(any(RegisterRequest.class));

      mockMvc.perform(
              post("/auth/register").contentType(MediaType.APPLICATION_JSON)
                  .content(objectMapper.writeValueAsString(request)))
          .andExpect(status().isCreated())
          .andExpect(content().string("User successfully registered"));

      verify(authService, times(1)).register(any(RegisterRequest.class));
    }

    // Z - Zero (empty/blank fields)
    @Test @DisplayName("Z - Should return 400 when username is blank") void zeroCase_BlankUsername()
        throws Exception
    {
      RegisterRequest request = createRegisterRequest("", "john@example.com",
          "Pass123!@#");

      mockMvc.perform(
              post("/auth/register").contentType(MediaType.APPLICATION_JSON)
                  .content(objectMapper.writeValueAsString(request)))
          .andExpect(status().isBadRequest());

      verify(authService, never()).register(any(RegisterRequest.class));
    }

    @Test @DisplayName("Z - Should return 400 when email is blank") void zeroCase_BlankEmail()
        throws Exception
    {
      RegisterRequest request = createRegisterRequest("john", "", "Pass123!@#");

      mockMvc.perform(
              post("/auth/register").contentType(MediaType.APPLICATION_JSON)
                  .content(objectMapper.writeValueAsString(request)))
          .andExpect(status().isBadRequest());

      verify(authService, never()).register(any(RegisterRequest.class));
    }

    @Test @DisplayName("Z - Should return 400 when password is blank") void zeroCase_BlankPassword()
        throws Exception
    {
      RegisterRequest request = createRegisterRequest("john",
          "john@example.com", "");

      mockMvc.perform(
              post("/auth/register").contentType(MediaType.APPLICATION_JSON)
                  .content(objectMapper.writeValueAsString(request)))
          .andExpect(status().isBadRequest());

      verify(authService, never()).register(any(RegisterRequest.class));
    }

    // O - One (single validation failure)
    @Test @DisplayName("O - Should return 409 when username already exists") void oneCase_DuplicateUsername()
        throws Exception
    {
      RegisterRequest request = createRegisterRequest("john",
          "john@example.com", "Pass123!@#");
      doThrow(new IllegalArgumentException("Username is already taken\n")).when(
          authService).register(any(RegisterRequest.class));

      mockMvc.perform(
              post("/auth/register").contentType(MediaType.APPLICATION_JSON)
                  .content(objectMapper.writeValueAsString(request)))
          .andExpect(status().isConflict())
          .andExpect(content().string("Username is already taken\n"));
    }

    @Test @DisplayName("O - Should return 409 when email already exists") void oneCase_DuplicateEmail()
        throws Exception
    {
      RegisterRequest request = createRegisterRequest("john",
          "john@example.com", "Pass123!@#");
      doThrow(
          new IllegalArgumentException("Email is already registered\n")).when(
          authService).register(any(RegisterRequest.class));

      mockMvc.perform(
              post("/auth/register").contentType(MediaType.APPLICATION_JSON)
                  .content(objectMapper.writeValueAsString(request)))
          .andExpect(status().isConflict())
          .andExpect(content().string("Email is already registered\n"));
    }

    @Test @DisplayName("O - Should return 409 when password validation fails") void oneCase_InvalidPassword()
        throws Exception
    {
      RegisterRequest request = createRegisterRequest("john",
          "john@example.com", "password");
      doThrow(new IllegalArgumentException(
          "Password must contain letters, numbers, and symbols\n")).when(
          authService).register(any(RegisterRequest.class));

      mockMvc.perform(
              post("/auth/register").contentType(MediaType.APPLICATION_JSON)
                  .content(objectMapper.writeValueAsString(request)))
          .andExpect(status().isConflict()).andExpect(content().string(
              "Password must contain letters, numbers, and symbols\n"));
    }

    @Test @DisplayName("O - Should return 400 when email format is invalid") void oneCase_InvalidEmailFormat()
        throws Exception
    {
      RegisterRequest request = createRegisterRequest("john", "not-an-email",
          "Pass123!@#");

      mockMvc.perform(
              post("/auth/register").contentType(MediaType.APPLICATION_JSON)
                  .content(objectMapper.writeValueAsString(request)))
          .andExpect(status().isBadRequest());

      verify(authService, never()).register(any(RegisterRequest.class));
    }

    // B - Boundaries (edge cases on length and format)
    @Test @DisplayName("B - Should return 400 when password is too short (< 8 chars)") void boundary_PasswordTooShort()
        throws Exception
    {
      RegisterRequest request = createRegisterRequest("john",
          "john@example.com", "Pass1!");

      mockMvc.perform(
              post("/auth/register").contentType(MediaType.APPLICATION_JSON)
                  .content(objectMapper.writeValueAsString(request)))
          .andExpect(status().isBadRequest());

      verify(authService, never()).register(any(RegisterRequest.class));
    }

    @Test @DisplayName("B - Should accept password at minimum length (8 chars)") void boundary_PasswordMinimumLength()
        throws Exception
    {
      RegisterRequest request = createRegisterRequest("john",
          "john@example.com", "Pass123!");
      doNothing().when(authService).register(any(RegisterRequest.class));

      mockMvc.perform(
              post("/auth/register").contentType(MediaType.APPLICATION_JSON)
                  .content(objectMapper.writeValueAsString(request)))
          .andExpect(status().isCreated());
    }

    @Test @DisplayName("B - Should return 400 when password exceeds maximum length (> 50 chars)") void boundary_PasswordTooLong()
        throws Exception
    {
      RegisterRequest request = createRegisterRequest("john",
          "john@example.com", "P".repeat(51));

      mockMvc.perform(
              post("/auth/register").contentType(MediaType.APPLICATION_JSON)
                  .content(objectMapper.writeValueAsString(request)))
          .andExpect(status().isBadRequest());

      verify(authService, never()).register(any(RegisterRequest.class));
    }

    @Test @DisplayName("B - Should accept password at maximum length (50 chars)") void boundary_PasswordMaximumLength()
        throws Exception
    {
      RegisterRequest request = createRegisterRequest("john",
          "john@example.com", "P".repeat(50));
      doNothing().when(authService).register(any(RegisterRequest.class));

      mockMvc.perform(
              post("/auth/register").contentType(MediaType.APPLICATION_JSON)
                  .content(objectMapper.writeValueAsString(request)))
          .andExpect(status().isCreated());
    }

    @Test @DisplayName("B - Should return 400 when username exceeds 30 characters") void boundary_UsernameTooLong()
        throws Exception
    {
      String longUsername = "a".repeat(31);
      RegisterRequest request = createRegisterRequest(longUsername,
          "john@example.com", "Pass123!@#");

      mockMvc.perform(
              post("/auth/register").contentType(MediaType.APPLICATION_JSON)
                  .content(objectMapper.writeValueAsString(request)))
          .andExpect(status().isBadRequest());

      verify(authService, never()).register(any(RegisterRequest.class));
    }

    @Test @DisplayName("B - Should accept username at maximum length (30 chars)") void boundary_UsernameMaximumLength()
        throws Exception
    {
      String maxUsername = "a".repeat(30);
      RegisterRequest request = createRegisterRequest(maxUsername,
          "john@example.com", "Pass123!@#");
      doNothing().when(authService).register(any(RegisterRequest.class));

      mockMvc.perform(
              post("/auth/register").contentType(MediaType.APPLICATION_JSON)
                  .content(objectMapper.writeValueAsString(request)))
          .andExpect(status().isCreated());
    }

    @Test @DisplayName("B - Should return 400 when username doesn't start with letter") void boundary_UsernameStartsWithNumber()
        throws Exception
    {
      RegisterRequest request = createRegisterRequest("123john",
          "john@example.com", "Pass123!@#");

      mockMvc.perform(
              post("/auth/register").contentType(MediaType.APPLICATION_JSON)
                  .content(objectMapper.writeValueAsString(request)))
          .andExpect(status().isBadRequest());

      verify(authService, never()).register(any(RegisterRequest.class));
    }

    // I - Interfaces (controller delegates to service)
    @Test @DisplayName("I - Should delegate to AuthService when request is valid") void interface_DelegatesToService()
        throws Exception
    {
      RegisterRequest request = createRegisterRequest("john",
          "john@example.com", "Pass123!@#");
      doNothing().when(authService).register(any(RegisterRequest.class));

      mockMvc.perform(
              post("/auth/register").contentType(MediaType.APPLICATION_JSON)
                  .content(objectMapper.writeValueAsString(request)))
          .andExpect(status().isCreated());

      verify(authService, times(1)).register(any(RegisterRequest.class));
    }

    @Test @DisplayName("I - Should not call service when validation fails") void interface_DoesNotCallServiceOnValidationFailure()
        throws Exception
    {
      RegisterRequest request = createRegisterRequest("", "john@example.com",
          "Pass123!@#");

      mockMvc.perform(
              post("/auth/register").contentType(MediaType.APPLICATION_JSON)
                  .content(objectMapper.writeValueAsString(request)))
          .andExpect(status().isBadRequest());

      verify(authService, never()).register(any(RegisterRequest.class));
    }

    // E - Exceptions (proper error handling)
    @Test @DisplayName("E - Should handle IllegalArgumentException from service") void exception_HandlesServiceException()
        throws Exception
    {
      RegisterRequest request = createRegisterRequest("john",
          "john@example.com", "Pass123!@#");
      doThrow(new IllegalArgumentException("Custom error message")).when(
          authService).register(any(RegisterRequest.class));

      mockMvc.perform(
              post("/auth/register").contentType(MediaType.APPLICATION_JSON)
                  .content(objectMapper.writeValueAsString(request)))
          .andExpect(status().isConflict())
          .andExpect(content().string("Custom error message"));
    }
  }

  // ==================== LOGIN ENDPOINT TESTS ====================

  @Nested @DisplayName("POST /auth/login - ZOMBIES") class LoginEndpointTests
  {

    // S - Simple scenarios (happy path first)
    @Test @DisplayName("S - Should return 200 with token when login succeeds") void simpleCase_SuccessfulLogin()
        throws Exception
    {
      LoginRequest request = createLoginRequest("john@example.com",
          "Pass123!@#");
      String expectedToken = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...";
      when(authService.login(any(LoginRequest.class))).thenReturn(
          expectedToken);

      mockMvc.perform(
              post("/auth/login").contentType(MediaType.APPLICATION_JSON)
                  .content(objectMapper.writeValueAsString(request)))
          .andExpect(status().isOk())
          .andExpect(content().string(expectedToken));

      verify(authService, times(1)).login(any(LoginRequest.class));
    }

    // Z - Zero (empty/blank fields)
    @Test @DisplayName("Z - Should return 400 when email is blank") void zeroCase_BlankEmail()
        throws Exception
    {
      LoginRequest request = createLoginRequest("", "Pass123!@#");

      mockMvc.perform(
              post("/auth/login").contentType(MediaType.APPLICATION_JSON)
                  .content(objectMapper.writeValueAsString(request)))
          .andExpect(status().isBadRequest());

      verify(authService, never()).login(any(LoginRequest.class));
    }

    @Test @DisplayName("Z - Should return 400 when password is blank") void zeroCase_BlankPassword()
        throws Exception
    {
      LoginRequest request = createLoginRequest("john@example.com", "");

      mockMvc.perform(
              post("/auth/login").contentType(MediaType.APPLICATION_JSON)
                  .content(objectMapper.writeValueAsString(request)))
          .andExpect(status().isBadRequest());

      verify(authService, never()).login(any(LoginRequest.class));
    }

    // O - One (single validation/authentication failure)
    @Test @DisplayName("O - Should return 400 when email format is invalid") void oneCase_InvalidEmailFormat()
        throws Exception
    {
      LoginRequest request = createLoginRequest("not-an-email", "Pass123!@#");

      mockMvc.perform(
              post("/auth/login").contentType(MediaType.APPLICATION_JSON)
                  .content(objectMapper.writeValueAsString(request)))
          .andExpect(status().isBadRequest());

      verify(authService, never()).login(any(LoginRequest.class));
    }

    @Test @DisplayName("O - Should return 400 when credentials are invalid") void oneCase_InvalidCredentials()
        throws Exception
    {
      LoginRequest request = createLoginRequest("john@example.com",
          "WrongPass123!");
      when(authService.login(any(LoginRequest.class))).thenThrow(
          new IllegalArgumentException("Invalid email or password"));

      mockMvc.perform(
              post("/auth/login").contentType(MediaType.APPLICATION_JSON)
                  .content(objectMapper.writeValueAsString(request)))
          .andExpect(status().isBadRequest());
    }

    // I - Interfaces (controller delegates to service)
    @Test @DisplayName("I - Should delegate to AuthService when request is valid") void interface_DelegatesToService()
        throws Exception
    {
      LoginRequest request = createLoginRequest("john@example.com",
          "Pass123!@#");
      when(authService.login(any(LoginRequest.class))).thenReturn("token");

      mockMvc.perform(
              post("/auth/login").contentType(MediaType.APPLICATION_JSON)
                  .content(objectMapper.writeValueAsString(request)))
          .andExpect(status().isOk());

      verify(authService, times(1)).login(any(LoginRequest.class));
    }

    @Test @DisplayName("I - Should not call service when validation fails") void interface_DoesNotCallServiceOnValidationFailure()
        throws Exception
    {
      LoginRequest request = createLoginRequest("", "Pass123!@#");

      mockMvc.perform(
              post("/auth/login").contentType(MediaType.APPLICATION_JSON)
                  .content(objectMapper.writeValueAsString(request)))
          .andExpect(status().isBadRequest());

      verify(authService, never()).login(any(LoginRequest.class));
    }

    // E - Exceptions (proper error handling)
    @Test @DisplayName("E - Should handle IllegalArgumentException from service") void exception_HandlesServiceException()
        throws Exception
    {
      LoginRequest request = createLoginRequest("john@example.com",
          "Pass123!@#");
      when(authService.login(any(LoginRequest.class))).thenThrow(
          new IllegalArgumentException("Invalid email or password"));

      mockMvc.perform(
              post("/auth/login").contentType(MediaType.APPLICATION_JSON)
                  .content(objectMapper.writeValueAsString(request)))
          .andExpect(status().isBadRequest());
    }

    // B - Boundaries (valid edge case inputs)
    @Test @DisplayName("B - Should accept valid email at edge of typical length") void boundary_LongValidEmail()
        throws Exception
    {
      String longEmail = "a".repeat(50) + "@example.com";
      LoginRequest request = createLoginRequest(longEmail, "Pass123!@#");
      when(authService.login(any(LoginRequest.class))).thenReturn("token");

      mockMvc.perform(
              post("/auth/login").contentType(MediaType.APPLICATION_JSON)
                  .content(objectMapper.writeValueAsString(request)))
          .andExpect(status().isOk());

      verify(authService, times(1)).login(any(LoginRequest.class));
    }
  }

  // ==================== HELPER METHODS ====================

  private RegisterRequest createRegisterRequest(String username, String email,
      String password)
  {
    return new RegisterRequest()
    {{
      try
      {
        var usernameField = RegisterRequest.class.getDeclaredField("username");
        usernameField.setAccessible(true);
        usernameField.set(this, username);

        var emailField = RegisterRequest.class.getDeclaredField("email");
        emailField.setAccessible(true);
        emailField.set(this, email);

        var passwordField = RegisterRequest.class.getDeclaredField("password");
        passwordField.setAccessible(true);
        passwordField.set(this, password);
      }
      catch (Exception e)
      {
        throw new RuntimeException(e);
      }
    }};
  }

  private LoginRequest createLoginRequest(String email, String password)
  {
    return new LoginRequest()
    {{
      try
      {
        var emailField = LoginRequest.class.getDeclaredField("email");
        emailField.setAccessible(true);
        emailField.set(this, email);

        var passwordField = LoginRequest.class.getDeclaredField("password");
        passwordField.setAccessible(true);
        passwordField.set(this, password);
      }
      catch (Exception e)
      {
        throw new RuntimeException(e);
      }
    }};
  }
}
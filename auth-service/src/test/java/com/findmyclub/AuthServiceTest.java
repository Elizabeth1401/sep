package com.findmyclub;

import com.findmyclub.DTO.LoginRequest;
import com.findmyclub.DTO.RegisterRequest;
import com.findmyclub.model.User;
import com.findmyclub.repositories.UserRepository;
import com.findmyclub.security.JWTService;
import com.findmyclub.service.AuthService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

/**
 * AuthService tests following ZOMBIES principles:
 * Z - Zero (empty, null cases)
 * O - One (single, simple case)
 * M - Many (multiple items)
 * B - Boundaries (edge cases, limits)
 * I - Interfaces (interaction with dependencies)
 * E - Exceptions (error conditions)
 * S - Simple scenarios (happy path)
 */
@ExtendWith(MockitoExtension.class) class AuthServiceTest
{

  @Mock private UserRepository userRepository;

  @Mock private PasswordEncoder passwordEncoder;

  @Mock private JWTService jwtService;

  @InjectMocks private AuthService authService;

  // ==================== REGISTER TESTS ====================

  @Nested @DisplayName("Register - ZOMBIES") class RegisterTests
  {

    // S - Simple scenarios (happy path first)
    @Test @DisplayName("S - Should successfully register user with valid data") void simpleCase_ValidRegistration()
    {
      RegisterRequest request = createRegisterRequest("john",
          "john@example.com", "Pass123!");
      when(userRepository.existsByUsername(anyString())).thenReturn(false);
      when(userRepository.existsByEmail(anyString())).thenReturn(false);
      when(passwordEncoder.encode(anyString())).thenReturn("hashedPassword");

      authService.register(request);

      ArgumentCaptor<User> userCaptor = ArgumentCaptor.forClass(User.class);
      verify(userRepository, times(1)).save(userCaptor.capture());
      User savedUser = userCaptor.getValue();
      assertEquals("john", savedUser.getUsername());
      assertEquals("john@example.com", savedUser.getEmail());
      assertEquals("hashedPassword", savedUser.getPasswordHash());
    }

    // O - One (single validation failure)
    @Test @DisplayName("O - Should fail when username already exists") void oneCase_DuplicateUsername()
    {
      RegisterRequest request = createRegisterRequest("john",
          "john@example.com", "Pass123!");
      when(userRepository.existsByUsername("john")).thenReturn(true);
      when(userRepository.existsByEmail(anyString())).thenReturn(false);

      IllegalArgumentException exception = assertThrows(
          IllegalArgumentException.class, () -> authService.register(request));

      assertTrue(exception.getMessage().contains("Username is already taken"));
      verify(userRepository, never()).save(any(User.class));
    }

    @Test @DisplayName("O - Should fail when email already exists") void oneCase_DuplicateEmail()
    {
      RegisterRequest request = createRegisterRequest("john",
          "john@example.com", "Pass123!");
      when(userRepository.existsByUsername(anyString())).thenReturn(false);
      when(userRepository.existsByEmail("john@example.com")).thenReturn(true);

      IllegalArgumentException exception = assertThrows(
          IllegalArgumentException.class, () -> authService.register(request));

      assertTrue(
          exception.getMessage().contains("Email is already registered"));
      verify(userRepository, never()).save(any(User.class));
    }

    @Test @DisplayName("O - Should fail with one password validation error") void oneCase_InvalidPassword()
    {
      RegisterRequest request = createRegisterRequest("john",
          "john@example.com", "password");
      when(userRepository.existsByUsername(anyString())).thenReturn(false);
      when(userRepository.existsByEmail(anyString())).thenReturn(false);

      IllegalArgumentException exception = assertThrows(
          IllegalArgumentException.class, () -> authService.register(request));

      assertTrue(exception.getMessage()
          .contains("Password must contain letters, numbers, and symbols"));
      verify(userRepository, never()).save(any(User.class));
    }

    // M - Many (multiple validation failures)
    @Test @DisplayName("M - Should fail with multiple validation errors") void manyCase_MultipleValidationFailures()
    {
      RegisterRequest request = createRegisterRequest("john",
          "john@example.com", "password");
      when(userRepository.existsByUsername("john")).thenReturn(true);
      when(userRepository.existsByEmail("john@example.com")).thenReturn(true);

      IllegalArgumentException exception = assertThrows(
          IllegalArgumentException.class, () -> authService.register(request));

      String message = exception.getMessage();
      assertTrue(message.contains("Username is already taken"));
      assertTrue(message.contains("Email is already registered"));
      assertTrue(message.contains(
          "Password must contain letters, numbers, and symbols"));
      verify(userRepository, never()).save(any(User.class));
    }

    // B - Boundaries (edge cases)
    @Test @DisplayName("B - Should accept username at minimum valid length (1 char)") void boundary_MinimumUsernameLength()
    {
      RegisterRequest request = createRegisterRequest("a", "a@example.com",
          "Pass123!");
      when(userRepository.existsByUsername(anyString())).thenReturn(false);
      when(userRepository.existsByEmail(anyString())).thenReturn(false);
      when(passwordEncoder.encode(anyString())).thenReturn("hashedPassword");

      assertDoesNotThrow(() -> authService.register(request));
      verify(userRepository, times(1)).save(any(User.class));
    }

    @Test @DisplayName("B - Should accept username at maximum valid length (30 chars)") void boundary_MaximumUsernameLength()
    {
      RegisterRequest request = createRegisterRequest("a".repeat(30),
          "test@example.com", "Pass123!");
      when(userRepository.existsByUsername(anyString())).thenReturn(false);
      when(userRepository.existsByEmail(anyString())).thenReturn(false);
      when(passwordEncoder.encode(anyString())).thenReturn("hashedPassword");

      assertDoesNotThrow(() -> authService.register(request));
      verify(userRepository, times(1)).save(any(User.class));
    }

    @Test @DisplayName("B - Should accept password with minimum complexity (letters + numbers)") void boundary_MinimumPasswordComplexity()
    {
      RegisterRequest request = createRegisterRequest("john",
          "john@example.com", "Pass123");
      when(userRepository.existsByUsername(anyString())).thenReturn(false);
      when(userRepository.existsByEmail(anyString())).thenReturn(false);
      when(passwordEncoder.encode(anyString())).thenReturn("hashedPassword");

      assertDoesNotThrow(() -> authService.register(request));
      verify(userRepository, times(1)).save(any(User.class));
    }

    // I - Interfaces (interaction with dependencies)
    @Test @DisplayName("I - Should interact with UserRepository to check username existence") void interface_CheckUsernameExists()
    {
      RegisterRequest request = createRegisterRequest("john",
          "john@example.com", "Pass123!");
      when(userRepository.existsByUsername("john")).thenReturn(false);
      when(userRepository.existsByEmail(anyString())).thenReturn(false);
      when(passwordEncoder.encode(anyString())).thenReturn("hashedPassword");

      authService.register(request);

      verify(userRepository, times(1)).existsByUsername("john");
    }

    @Test @DisplayName("I - Should interact with UserRepository to check email existence") void interface_CheckEmailExists()
    {
      RegisterRequest request = createRegisterRequest("john",
          "john@example.com", "Pass123!");
      when(userRepository.existsByUsername(anyString())).thenReturn(false);
      when(userRepository.existsByEmail("john@example.com")).thenReturn(false);
      when(passwordEncoder.encode(anyString())).thenReturn("hashedPassword");

      authService.register(request);

      verify(userRepository, times(1)).existsByEmail("john@example.com");
    }

    @Test @DisplayName("I - Should interact with PasswordEncoder to hash password") void interface_PasswordEncoding()
    {
      RegisterRequest request = createRegisterRequest("john",
          "john@example.com", "Pass123!");
      when(userRepository.existsByUsername(anyString())).thenReturn(false);
      when(userRepository.existsByEmail(anyString())).thenReturn(false);
      when(passwordEncoder.encode("Pass123!")).thenReturn("hashedPassword");

      authService.register(request);

      verify(passwordEncoder, times(1)).encode("Pass123!");
      ArgumentCaptor<User> userCaptor = ArgumentCaptor.forClass(User.class);
      verify(userRepository).save(userCaptor.capture());
      assertEquals("hashedPassword", userCaptor.getValue().getPasswordHash());
    }

    @Test @DisplayName("I - Should interact with UserRepository to save user") void interface_SaveUser()
    {
      RegisterRequest request = createRegisterRequest("john",
          "john@example.com", "Pass123!");
      when(userRepository.existsByUsername(anyString())).thenReturn(false);
      when(userRepository.existsByEmail(anyString())).thenReturn(false);
      when(passwordEncoder.encode(anyString())).thenReturn("hashedPassword");

      authService.register(request);

      verify(userRepository, times(1)).save(any(User.class));
    }

    // E - Exceptions (error conditions already covered above)
    // Already tested: duplicate username, duplicate email, invalid password, multiple errors
  }

  // ==================== LOGIN TESTS ====================

  @Nested @DisplayName("Login - ZOMBIES") class LoginTests
  {

    // S - Simple scenarios (happy path first)
    @Test @DisplayName("S - Should successfully login with valid credentials") void simpleCase_ValidLogin()
    {
      LoginRequest request = createLoginRequest("john@example.com", "Pass123!");
      User user = new User("john", "john@example.com", "hashedPassword");
      String expectedToken = "jwt.token.here";

      when(userRepository.findByEmail("john@example.com")).thenReturn(
          Optional.of(user));
      when(passwordEncoder.matches("Pass123!", "hashedPassword")).thenReturn(
          true);
      when(jwtService.generateToken(user)).thenReturn(expectedToken);

      String token = authService.login(request);

      assertEquals(expectedToken, token);
    }

    // Z - Zero (user not found)
    @Test @DisplayName("Z - Should fail when user does not exist") void zeroCase_UserNotFound()
    {
      LoginRequest request = createLoginRequest("nonexistent@example.com",
          "Pass123!");
      when(userRepository.findByEmail("nonexistent@example.com")).thenReturn(
          Optional.empty());

      IllegalArgumentException exception = assertThrows(
          IllegalArgumentException.class, () -> authService.login(request));

      assertEquals("Invalid email or password", exception.getMessage());
      verify(jwtService, never()).generateToken(any(User.class));
    }

    // O - One (single failure case)
    @Test @DisplayName("O - Should fail with wrong password") void oneCase_WrongPassword()
    {
      LoginRequest request = createLoginRequest("john@example.com",
          "WrongPass123!");
      User user = new User("john", "john@example.com", "hashedPassword");

      when(userRepository.findByEmail("john@example.com")).thenReturn(
          Optional.of(user));
      when(passwordEncoder.matches("WrongPass123!",
          "hashedPassword")).thenReturn(false);

      IllegalArgumentException exception = assertThrows(
          IllegalArgumentException.class, () -> authService.login(request));

      assertEquals("Invalid email or password", exception.getMessage());
      verify(jwtService, never()).generateToken(any(User.class));
    }

    // I - Interfaces (interaction with dependencies)
    @Test @DisplayName("I - Should interact with UserRepository to find user by email") void interface_FindUserByEmail()
    {
      LoginRequest request = createLoginRequest("john@example.com", "Pass123!");
      User user = new User("john", "john@example.com", "hashedPassword");

      when(userRepository.findByEmail("john@example.com")).thenReturn(
          Optional.of(user));
      when(passwordEncoder.matches("Pass123!", "hashedPassword")).thenReturn(
          true);
      when(jwtService.generateToken(user)).thenReturn("token");

      authService.login(request);

      verify(userRepository, times(1)).findByEmail("john@example.com");
    }

    @Test @DisplayName("I - Should interact with PasswordEncoder to verify password") void interface_PasswordVerification()
    {
      LoginRequest request = createLoginRequest("john@example.com", "Pass123!");
      User user = new User("john", "john@example.com", "hashedPassword");

      when(userRepository.findByEmail("john@example.com")).thenReturn(
          Optional.of(user));
      when(passwordEncoder.matches("Pass123!", "hashedPassword")).thenReturn(
          true);
      when(jwtService.generateToken(user)).thenReturn("token");

      authService.login(request);

      verify(passwordEncoder, times(1)).matches("Pass123!", "hashedPassword");
    }

    @Test @DisplayName("I - Should interact with JWTService to generate token") void interface_TokenGeneration()
    {
      LoginRequest request = createLoginRequest("john@example.com", "Pass123!");
      User user = new User("john", "john@example.com", "hashedPassword");
      String expectedToken = "jwt.token.here";

      when(userRepository.findByEmail("john@example.com")).thenReturn(
          Optional.of(user));
      when(passwordEncoder.matches("Pass123!", "hashedPassword")).thenReturn(
          true);
      when(jwtService.generateToken(user)).thenReturn(expectedToken);

      String token = authService.login(request);

      verify(jwtService, times(1)).generateToken(user);
      assertEquals(expectedToken, token);
    }

    // E - Exceptions (security considerations)
    @Test @DisplayName("E - Should not reveal if user exists when password is wrong") void exception_GenericErrorMessage()
    {
      LoginRequest request = createLoginRequest("john@example.com",
          "WrongPass!");
      User user = new User("john", "john@example.com", "hashedPassword");

      when(userRepository.findByEmail("john@example.com")).thenReturn(
          Optional.of(user));
      when(passwordEncoder.matches("WrongPass!", "hashedPassword")).thenReturn(
          false);

      IllegalArgumentException exception = assertThrows(
          IllegalArgumentException.class, () -> authService.login(request));

      // Same error message for both non-existent user and wrong password (security best practice)
      assertEquals("Invalid email or password", exception.getMessage());
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
package com.findmyclub.service;

import com.findmyclub.DTO.LoginRequest;
import com.findmyclub.DTO.RegisterRequest;
import com.findmyclub.model.User;
import com.findmyclub.repositories.UserRepository;
import com.findmyclub.security.JWTService;
import jakarta.validation.Valid;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service public class AuthService
{

  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;
  private final JWTService jwtService;

  public AuthService(UserRepository userRepository,
      PasswordEncoder passwordEncoder, JWTService jwtService)
  {
    this.userRepository = userRepository;
    this.passwordEncoder = passwordEncoder;
    this.jwtService = jwtService;
  }

  // REGISTRATION
  public void register(RegisterRequest request)
  {

    boolean isValidUsername = validateUsername(request.getUsername());
    boolean isValidEmail = validateEmail(request.getEmail());
    boolean isValidPassword = validatePassword(request.getPassword());

    if (isValidUsername && isValidEmail && isValidPassword)
    {
      String hash = passwordEncoder.encode(request.getPassword());

      User user = new User(request.getUsername(), request.getEmail(), hash);

    // REGISTRATION
    public void register(RegisterRequest request){

        boolean isValidUsername =  validateUsername(request.getUsername());
        boolean isValidEmail = validateEmail(request.getEmail());
        boolean isValidPassword = validatePassword(request.getPassword());

        if(isValidUsername && isValidEmail && isValidPassword){
            String hash = passwordEncoder.encode(request.getPassword());

            User user = new User(
                    request.getUsername(),
                    request.getEmail(),
                    hash
            );

            userRepository.save(user);
        }
        else {
            String errorMessage = "";

            if(!isValidUsername){
                errorMessage += "Username is already taken\n";
            }
            if(!isValidEmail){
                errorMessage += "Email is already registered\n";
            }
            if(!isValidPassword){
                errorMessage += "Password must contain letters, numbers, and symbols\n";
            }

            throw new IllegalArgumentException(errorMessage);
        }




    }
    else
    {
      String errorMessage = "";

      if(!isValidUsername){
        errorMessage += "Username is already taken; ";
      }
      if(!isValidEmail){
        errorMessage += "Email is already registered; ";
      }
      if(!isValidPassword){
        errorMessage += "Password must contain letters, numbers, and symbols";
      }

      throw new IllegalArgumentException(errorMessage);
    }
  }

    //--------------
    // VALIDATION
    //--------------
    private boolean validateUsername(String username){
        if (userRepository.existsByUsername(username)){
            return false;
           // throw new IllegalArgumentException("Username is already taken");
        }
        return true;
    }
    private boolean validateEmail(String email){
        if (userRepository.existsByEmail(email)){
            return false;
           // throw new IllegalArgumentException("Email is already registered");
        }
        return true;
    }
    private boolean validatePassword(String password){
        boolean hasLetter = password.matches(".*[A-Za-z].*");
        boolean hasDigit = password.matches(".*[0-9].*");
        boolean hasSymbol = password.matches(".*[A-Za-z].*");

        if (!hasLetter && !hasDigit && !hasSymbol){
            return false;
           // throw new IllegalArgumentException("Password must contain letters, numbers, and symbols");
        }
        return true;
    }
    return true;
  }
}

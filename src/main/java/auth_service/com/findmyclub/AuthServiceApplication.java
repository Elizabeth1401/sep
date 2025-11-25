package auth_service.com.findmyclub;

import auth_service.com.findmyclub.model.User;
import auth_service.com.findmyclub.repositories.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


import java.util.Map;

@SpringBootApplication
public class AuthServiceApplication
{
    public static void main(String[] args)
    {
        SpringApplication app = new SpringApplication(AuthServiceApplication.class);
        app.setDefaultProperties(Map.of("server.port", "8081"));
        app.run(args);
    }
}
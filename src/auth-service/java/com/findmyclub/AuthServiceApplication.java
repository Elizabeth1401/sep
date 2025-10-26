package com.findmyclub;

import com.findmyclub.model.User;
import com.findmyclub.repositories.UserRepository;
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

    @Bean
    CommandLineRunner demoUser(UserRepository userRepository) {
        return args -> {
            userRepository.deleteAll();
            System.out.println("Seeding data...");
            User user1 = new User("liza", "12345");
            User user2 = new User("teo", "mypassword");
            User user3 = new User("bianca", "qwerty");

            userRepository.save(user1);
            userRepository.save(user2);
            userRepository.save(user3);

        };
    }
}
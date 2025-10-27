package com.findmyclub;
import com.findmyclub.model.Club;
import com.findmyclub.repositories.ClubRepository;
//import com.findmyclub.repositories.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Map;


@SpringBootApplication
public class APIServiceApplication
{
  public static void main(String[] args)
  {
    SpringApplication app = new SpringApplication(APIServiceApplication.class);
    app.setDefaultProperties(Map.of("server.port", "8082"));
    app.run(args);
  }

  //for you to start with data inside the DB
  @Bean
  CommandLineRunner demoAPI(ClubRepository clubRepository) {
    return args -> {
      clubRepository.deleteAll();
      System.out.println("Seeding data...");
      Club club1 = new Club("Music","Horsens");
      Club club2 = new Club("Box","Copenhagen");
      Club club3 = new Club("Dance","Aarhus");

      clubRepository.save(club1);
      clubRepository.save(club2);
      clubRepository.save(club3);
    };
 }
}

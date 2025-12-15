package com.findmyclub.startup;

import com.findmyclub.model.Club;
import com.findmyclub.networking.FindMyClubGrpcServer;
import com.findmyclub.repositories.ClubRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import java.util.Map;

@SpringBootApplication(scanBasePackages = {
    "com.findmyclub.startup",
    "com.findmyclub.service",
    "com.findmyclub.networking"
})
@EnableJpaRepositories("com.findmyclub.repositories")
@EntityScan("com.findmyclub.model")
public class APIServiceApplication
{
  public static void main(String[] args) throws Exception
  {
    SpringApplication app = new SpringApplication(APIServiceApplication.class);
    app.setDefaultProperties(Map.of("server.port", "8080"));
    ApplicationContext context = app.run(args);

    //Get data to start with from demoAPI
    ClubRepository clubRepository = context.getBean(ClubRepository.class);
    context.getBean(CommandLineRunner.class).run(args);
  }

  //for you to start with data inside the DB
  @Bean
  CommandLineRunner demoAPI(ClubRepository clubRepository) {
    return args -> {
      clubRepository.deleteAll();
      System.out.println("Seeding data...");
      clubRepository.save(new Club("Wiggly Melody","Horsens",  "We like talking about music"));
      clubRepository.save(new Club("Boxing Bastards","Copenhagen",  "We like hitting each other"));
      clubRepository.save(new Club("Dancing Monkeys","Aarhus",  "We like dancing around"));
    };
  }
}
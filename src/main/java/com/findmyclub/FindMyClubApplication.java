package com.findmyclub;

import com.findmyclub.model.ClubDBtable;
import com.findmyclub.repositories.ClubRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;

@SpringBootApplication
public class FindMyClubApplication
{
  public static void main(String[] args)
  {
    SpringApplication.run(FindMyClubApplication.class, args);
  }

  @Bean CommandLineRunner demo(ClubRepository clubRepository) {
    return args -> {
      System.out.println(" demo...");
      ClubDBtable clubDBtable = new ClubDBtable("Music","Horsens");
      clubRepository.save(clubDBtable);

    };
  }
}

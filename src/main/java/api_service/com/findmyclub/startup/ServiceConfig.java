package api_service.com.findmyclub.startup;

import api_service.com.findmyclub.repositories.ClubRepository;
import api_service.com.findmyclub.service.ClubServiceDatabase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServiceConfig {

  private final ClubRepository clubRepository;

  public ServiceConfig(ClubRepository clubRepository) {
    this.clubRepository = clubRepository;
  }

  @Bean
  public ClubServiceDatabase clubServiceDatabase() {
    return new ClubServiceDatabase(clubRepository);
  }
}

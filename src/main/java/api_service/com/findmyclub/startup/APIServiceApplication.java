package api_service.com.findmyclub.startup;
import api_service.com.findmyclub.model.Club;
import api_service.com.findmyclub.networking.FindMyClubGrpcServer;
import api_service.com.findmyclub.repositories.ClubRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.Map;

@SpringBootApplication(scanBasePackages = {
    "api_service.com.findmyclub.startup",
    "api_service.com.findmyclub.service",
    "api_service.com.findmyclub.networking"
})
@EnableJpaRepositories("api_service.com.findmyclub.repositories")
@EntityScan("api_service.com.findmyclub.model")
public class APIServiceApplication
{
  public static void main(String[] args)
  {
    ApplicationContext context = (SpringApplication.run(APIServiceApplication.class,args));
    FindMyClubGrpcServer server = context.getBean(FindMyClubGrpcServer.class);
    server.start();
  }

  //for you to start with data inside the DB
  @Bean
  CommandLineRunner demoAPI(ClubRepository clubRepository) {
    return args -> {
      clubRepository.deleteAll();
      System.out.println("Seeding data...");
      clubRepository.save(new Club("Music","Horsens"));
      clubRepository.save(new Club("Box","Copenhagen"));
      clubRepository.save(new Club("Dance","Aarhus"));
    };
  }
}

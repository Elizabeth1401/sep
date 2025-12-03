package main.java.com.findmyclub.startup;
import main.java.com.findmyclub.model.Club;
import main.java.com.findmyclub.networking.FindMyClubGrpcServer;
import main.java.com.findmyclub.repositories.ClubRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = {
    "main.java.com.findmyclub.startup",
    "main.java.com.findmyclub.service",
    "main.java.com.findmyclub.networking"
})
@EnableJpaRepositories("main.java.com.findmyclub.repositories")
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

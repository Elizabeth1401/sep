package auth_service.com.findmyclub;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import java.util.Map;

@SpringBootApplication
@ComponentScan({
    "auth_service.com.findmyclub",
    "api_service.com.findmyclub"
})
@EnableJpaRepositories(basePackages = "auth_service.com.findmyclub.repositories")
@EntityScan(basePackages = "auth_service.com.findmyclub.model")
public class AuthServiceApplication
{
    public static void main(String[] args)
    {
        SpringApplication app = new SpringApplication(AuthServiceApplication.class);
        app.setDefaultProperties(Map.of("server.port", "8082")); // ha úgyis 8082-t használsz
        app.run(args);
    }
}

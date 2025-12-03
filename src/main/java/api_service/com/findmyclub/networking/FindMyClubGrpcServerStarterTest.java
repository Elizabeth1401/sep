package api_service.com.findmyclub.networking;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

@Component
public class FindMyClubGrpcServerStarterTest {

  private final FindMyClubGrpcServer grpcServer;

  public FindMyClubGrpcServerStarterTest(FindMyClubGrpcServer grpcServer) {
    this.grpcServer = grpcServer;
  }

  @PostConstruct
  public void startGrpc() {
    grpcServer.start();
  }
}

package api_service.com.findmyclub.startup;

import api_service.com.findmyclub.repositories.ClubRepository;
import api_service.com.findmyclub.networking.handlers.ClubHandler;
import api_service.com.findmyclub.networking.handlers.FindMyClubHandler;
import api_service.com.findmyclub.service.ClubServiceDatabase;
import api_service.com.findmyclub.service.ClubService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServiceProvider {

  @Autowired
  private ClubServiceDatabase clubServiceDatabase; // injected automatically

  @Autowired
  private ClubRepository clubRepository; // injected automatically

  public ClubService getClubService() {
    return clubServiceDatabase;
  }
  public FindMyClubHandler getClubHandler() {
    return new ClubHandler(getClubService());
  }
}

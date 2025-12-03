package com.findmyclub.startup;

//import api_service.com.findmyclub.repositories.ClubRepository;
import main.java.com.findmyclub.networking.handlers.AuthHandler;
import main.java.com.findmyclub.networking.handlers.ClubHandler;
import main.java.com.findmyclub.networking.handlers.FindMyClubHandler;
import main.java.com.findmyclub.repositories.ClubRepository;
import main.java.com.findmyclub.service.ClubService;
import main.java.com.findmyclub.service.ClubServiceDatabase;
import main.java.com.findmyclub.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServiceProvider {

  @Autowired
  private ClubServiceDatabase clubServiceDatabase; // injected automatically

  @Autowired
  private ClubRepository clubRepository; // injected automatically

    @Autowired
    private AuthService authService; // injected automatically by spring

    public ClubService getClubService() {
    return clubServiceDatabase;
  }
  public FindMyClubHandler getClubHandler() {
    return new ClubHandler(getClubService());
  }

    public FindMyClubHandler getAuthHandler() {return new AuthHandler(authService);} // responsible for processing gRPC requests for authentication(login/register)
}

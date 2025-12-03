package com.findmyclub.startup;

import com.findmyclub.repositories.ClubRepository;
import com.findmyclub.networking.handlers.AuthHandler;
import com.findmyclub.networking.handlers.ClubHandler;
import com.findmyclub.networking.handlers.FindMyClubHandler;
import com.findmyclub.service.ClubService;
import com.findmyclub.service.ClubServiceDatabase;
import com.findmyclub.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServiceProvider {

  @Autowired
  private ClubServiceDatabase clubServiceDatabase; // injected automatically

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

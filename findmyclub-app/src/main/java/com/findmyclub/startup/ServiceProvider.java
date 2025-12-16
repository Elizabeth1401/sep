package com.findmyclub.startup;

import com.findmyclub.networking.handlers.ClubHandler;
import com.findmyclub.networking.handlers.FindMyClubHandler;
import com.findmyclub.service.ClubService;
import com.findmyclub.service.ClubServiceDatabase;
import org.springframework.stereotype.Service;

@Service
public class ServiceProvider
{
  private final ClubServiceDatabase clubServiceDatabase;

  public ServiceProvider(ClubServiceDatabase clubServiceDatabase)
  {
    this.clubServiceDatabase = clubServiceDatabase;
  }

  public ClubService getClubService()
  {
    return clubServiceDatabase;
  }

  public FindMyClubHandler getClubHandler()
  {
    return new ClubHandler(getClubService());
  }
}

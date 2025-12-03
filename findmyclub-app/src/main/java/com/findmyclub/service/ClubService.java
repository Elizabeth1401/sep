package main.java.com.findmyclub.service;

import main.java.com.findmyclub.Grpc.Sep3.ClubListProto;
import main.java.com.findmyclub.Grpc.Sep3.ClubProto;
import org.springframework.stereotype.Service;

@Service
public interface ClubService
{
  ClubProto create(ClubProto payload);
  void update(ClubProto payload);
  void delete(int id);
  ClubProto getSingle(int id);
  ClubListProto getMany();
}

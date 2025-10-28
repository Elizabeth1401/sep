package api_service.com.findmyclub.service;

import api_service.com.findmyclub.Grpc.Sep3;
import api_service.com.findmyclub.Grpc.Sep3.ClubProto;
import api_service.com.findmyclub.Grpc.Sep3.ClubListProto;
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

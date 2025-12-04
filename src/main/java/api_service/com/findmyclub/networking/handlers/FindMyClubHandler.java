package api_service.com.findmyclub.networking.handlers;

import api_service.com.findmyclub.Grpc.Sep3;
import api_service.com.findmyclub.Grpc.Sep3.ResponseProto;
import api_service.com.findmyclub.Grpc.Sep3.ActionTypeProto;
import com.google.protobuf.Message;
import org.springframework.stereotype.Service;

@Service
public interface FindMyClubHandler
{
  Message handle(ActionTypeProto actionType, Object payload);
}

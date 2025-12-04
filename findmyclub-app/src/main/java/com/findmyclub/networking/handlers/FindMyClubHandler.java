package com.findmyclub.networking.handlers;

import com.findmyclub.Grpc.Sep3.ActionTypeProto;
import com.google.protobuf.Message;
import org.springframework.stereotype.Service;

@Service
public interface FindMyClubHandler
{
  Message handle(ActionTypeProto actionType, Object payload);
}

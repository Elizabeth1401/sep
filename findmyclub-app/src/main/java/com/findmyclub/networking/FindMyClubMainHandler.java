package com.findmyclub.networking;

import api_service.com.findmyclub.Grpc.FindMyClubProtoServiceGrpc;
import api_service.com.findmyclub.Grpc.Sep3.RequestProto;
import api_service.com.findmyclub.Grpc.Sep3.ResponseProto;
import api_service.com.findmyclub.Grpc.Sep3.StatusTypeProto;
import com.findmyclub.networking.handlers.FindMyClubHandler;
import com.findmyclub.startup.ServiceProvider;
import com.google.protobuf.Any;
import com.google.protobuf.Message;
import com.google.protobuf.StringValue;
import io.grpc.stub.StreamObserver;
import org.lognet.springboot.grpc.GRpcService;

@GRpcService public class FindMyClubMainHandler
    extends FindMyClubProtoServiceGrpc.FindMyClubProtoServiceImplBase
{
  private final ServiceProvider serviceProvider;

  public FindMyClubMainHandler(ServiceProvider serviceProvider)
  {
    this.serviceProvider = serviceProvider;
  }

  @Override public void sendRequest(RequestProto request,
      StreamObserver<ResponseProto> responseObserver)
  {
    try
    {
      // Route request based on HandlerType
      FindMyClubHandler handler = switch (request.getHandler())
      {
        case HANDLER_CLUB -> serviceProvider.getClubHandler();
        default -> throw new IllegalArgumentException("Unknown handler type");
      };
      // Message is the protobuf object
      Message result = handler.handle(request.getAction(),
          request.getPayload());
      // Only pack if not already an Any
      Any payload;
      if (result instanceof Any)
      {
        payload = (Any) result;
      }
      else
      {
        payload = Any.pack(result);
      }

      ResponseProto response = ResponseProto.newBuilder()
          .setStatus(StatusTypeProto.STATUS_OK).setPayload(payload).build();
      sendResponseWithHandleException(responseObserver, response);

    }
    catch (Exception e)
    {
      sendGrpcError(responseObserver, StatusTypeProto.STATUS_ERROR,
          e.getMessage());
    }
  }

  private void sendGrpcError(StreamObserver<ResponseProto> observer,
      StatusTypeProto status, String errorMessage)
  {
    Any payload = Any.pack(StringValue.of(
        errorMessage));// convert error message to protobuf message
    ResponseProto response = ResponseProto.newBuilder().setStatus(status)
        .setPayload(payload).build();
    observer.onNext(response);
    observer.onCompleted();
  }

  private void sendResponseWithHandleException(
      StreamObserver<ResponseProto> responseObserver, ResponseProto response)
  {
    try
    {
      responseObserver.onNext(response);
    }
    catch (ClassCastException e)
    {
      sendGrpcError(responseObserver, StatusTypeProto.STATUS_INVALID_PAYLOAD,
          "Invalid request");
      return;
    }
    catch (Exception e)
    {
      sendGrpcError(responseObserver, StatusTypeProto.STATUS_ERROR,
          e.getMessage());
      return;
    }
    try
    {
      responseObserver.onCompleted();
    }
    catch (Exception e)
    {
      System.err.println("Error completing gRPC response: " + e.getMessage());
    }
  }
}



package com.findmyclub.Grpc;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.66.0)",
    comments = "Source: sep3.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class ClubProtoServiceGrpc {

  private ClubProtoServiceGrpc() {}

  public static final String SERVICE_NAME = "api_service.com.findmyclub.Grpc.ClubProtoService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<com.findmyclub.Grpc.Sep3.RequestProto,
      com.findmyclub.Grpc.Sep3.ResponseProto> getSendRequestMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "SendRequest",
      requestType = com.findmyclub.Grpc.Sep3.RequestProto.class,
      responseType = com.findmyclub.Grpc.Sep3.ResponseProto.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.findmyclub.Grpc.Sep3.RequestProto,
      com.findmyclub.Grpc.Sep3.ResponseProto> getSendRequestMethod() {
    io.grpc.MethodDescriptor<com.findmyclub.Grpc.Sep3.RequestProto, com.findmyclub.Grpc.Sep3.ResponseProto> getSendRequestMethod;
    if ((getSendRequestMethod = ClubProtoServiceGrpc.getSendRequestMethod) == null) {
      synchronized (ClubProtoServiceGrpc.class) {
        if ((getSendRequestMethod = ClubProtoServiceGrpc.getSendRequestMethod) == null) {
          ClubProtoServiceGrpc.getSendRequestMethod = getSendRequestMethod =
              io.grpc.MethodDescriptor.<com.findmyclub.Grpc.Sep3.RequestProto, com.findmyclub.Grpc.Sep3.ResponseProto>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "SendRequest"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.findmyclub.Grpc.Sep3.RequestProto.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.findmyclub.Grpc.Sep3.ResponseProto.getDefaultInstance()))
              .setSchemaDescriptor(new ClubProtoServiceMethodDescriptorSupplier("SendRequest"))
              .build();
        }
      }
    }
    return getSendRequestMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static ClubProtoServiceStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<ClubProtoServiceStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<ClubProtoServiceStub>() {
        @Override
        public ClubProtoServiceStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new ClubProtoServiceStub(channel, callOptions);
        }
      };
    return ClubProtoServiceStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static ClubProtoServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<ClubProtoServiceBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<ClubProtoServiceBlockingStub>() {
        @Override
        public ClubProtoServiceBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new ClubProtoServiceBlockingStub(channel, callOptions);
        }
      };
    return ClubProtoServiceBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static ClubProtoServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<ClubProtoServiceFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<ClubProtoServiceFutureStub>() {
        @Override
        public ClubProtoServiceFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new ClubProtoServiceFutureStub(channel, callOptions);
        }
      };
    return ClubProtoServiceFutureStub.newStub(factory, channel);
  }

  /**
   */
  public interface AsyncService {

    /**
     */
    default void sendRequest(com.findmyclub.Grpc.Sep3.RequestProto request,
        io.grpc.stub.StreamObserver<com.findmyclub.Grpc.Sep3.ResponseProto> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getSendRequestMethod(), responseObserver);
    }
  }

  /**
   * Base class for the server implementation of the service ClubProtoService.
   */
  public static abstract class ClubProtoServiceImplBase
      implements io.grpc.BindableService, AsyncService {

    @Override public final io.grpc.ServerServiceDefinition bindService() {
      return ClubProtoServiceGrpc.bindService(this);
    }
  }

  /**
   * A stub to allow clients to do asynchronous rpc calls to service ClubProtoService.
   */
  public static final class ClubProtoServiceStub
      extends io.grpc.stub.AbstractAsyncStub<ClubProtoServiceStub> {
    private ClubProtoServiceStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @Override
    protected ClubProtoServiceStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new ClubProtoServiceStub(channel, callOptions);
    }

    /**
     */
    public void sendRequest(com.findmyclub.Grpc.Sep3.RequestProto request,
        io.grpc.stub.StreamObserver<com.findmyclub.Grpc.Sep3.ResponseProto> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getSendRequestMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * A stub to allow clients to do synchronous rpc calls to service ClubProtoService.
   */
  public static final class ClubProtoServiceBlockingStub
      extends io.grpc.stub.AbstractBlockingStub<ClubProtoServiceBlockingStub> {
    private ClubProtoServiceBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @Override
    protected ClubProtoServiceBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new ClubProtoServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public com.findmyclub.Grpc.Sep3.ResponseProto sendRequest(com.findmyclub.Grpc.Sep3.RequestProto request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getSendRequestMethod(), getCallOptions(), request);
    }
  }

  /**
   * A stub to allow clients to do ListenableFuture-style rpc calls to service ClubProtoService.
   */
  public static final class ClubProtoServiceFutureStub
      extends io.grpc.stub.AbstractFutureStub<ClubProtoServiceFutureStub> {
    private ClubProtoServiceFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @Override
    protected ClubProtoServiceFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new ClubProtoServiceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.findmyclub.Grpc.Sep3.ResponseProto> sendRequest(
        com.findmyclub.Grpc.Sep3.RequestProto request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getSendRequestMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_SEND_REQUEST = 0;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final AsyncService serviceImpl;
    private final int methodId;

    MethodHandlers(AsyncService serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @Override
    @SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_SEND_REQUEST:
          serviceImpl.sendRequest((com.findmyclub.Grpc.Sep3.RequestProto) request,
              (io.grpc.stub.StreamObserver<com.findmyclub.Grpc.Sep3.ResponseProto>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @Override
    @SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        default:
          throw new AssertionError();
      }
    }
  }

  public static final io.grpc.ServerServiceDefinition bindService(AsyncService service) {
    return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
        .addMethod(
          getSendRequestMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
                com.findmyclub.Grpc.Sep3.RequestProto,
                com.findmyclub.Grpc.Sep3.ResponseProto>(
                service, METHODID_SEND_REQUEST)))
        .build();
  }

  private static abstract class ClubProtoServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    ClubProtoServiceBaseDescriptorSupplier() {}

    @Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return com.findmyclub.Grpc.Sep3.getDescriptor();
    }

    @Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("ClubProtoService");
    }
  }

  private static final class ClubProtoServiceFileDescriptorSupplier
      extends ClubProtoServiceBaseDescriptorSupplier {
    ClubProtoServiceFileDescriptorSupplier() {}
  }

  private static final class ClubProtoServiceMethodDescriptorSupplier
      extends ClubProtoServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    ClubProtoServiceMethodDescriptorSupplier(String methodName) {
      this.methodName = methodName;
    }

    @Override
    public com.google.protobuf.Descriptors.MethodDescriptor getMethodDescriptor() {
      return getServiceDescriptor().findMethodByName(methodName);
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (ClubProtoServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new ClubProtoServiceFileDescriptorSupplier())
              .addMethod(getSendRequestMethod())
              .build();
        }
      }
    }
    return result;
  }
}

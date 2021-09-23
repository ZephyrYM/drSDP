package com.example.messages;

import static io.grpc.MethodDescriptor.generateFullMethodName;
import static io.grpc.stub.ClientCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ClientCalls.asyncClientStreamingCall;
import static io.grpc.stub.ClientCalls.asyncServerStreamingCall;
import static io.grpc.stub.ClientCalls.asyncUnaryCall;
import static io.grpc.stub.ClientCalls.blockingServerStreamingCall;
import static io.grpc.stub.ClientCalls.blockingUnaryCall;
import static io.grpc.stub.ClientCalls.futureUnaryCall;
import static io.grpc.stub.ServerCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ServerCalls.asyncClientStreamingCall;
import static io.grpc.stub.ServerCalls.asyncServerStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnaryCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.25.0)",
    comments = "Source: DroneMessages.proto")
public final class DroneMessagesGrpc {

  private DroneMessagesGrpc() {}

  public static final String SERVICE_NAME = "com.example.messages.DroneMessages";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<com.example.messages.DroneMessagesOuterClass.WelcomeMessage,
      com.example.messages.DroneMessagesOuterClass.AnswerWelcome> getWelcomeMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "welcome",
      requestType = com.example.messages.DroneMessagesOuterClass.WelcomeMessage.class,
      responseType = com.example.messages.DroneMessagesOuterClass.AnswerWelcome.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.example.messages.DroneMessagesOuterClass.WelcomeMessage,
      com.example.messages.DroneMessagesOuterClass.AnswerWelcome> getWelcomeMethod() {
    io.grpc.MethodDescriptor<com.example.messages.DroneMessagesOuterClass.WelcomeMessage, com.example.messages.DroneMessagesOuterClass.AnswerWelcome> getWelcomeMethod;
    if ((getWelcomeMethod = DroneMessagesGrpc.getWelcomeMethod) == null) {
      synchronized (DroneMessagesGrpc.class) {
        if ((getWelcomeMethod = DroneMessagesGrpc.getWelcomeMethod) == null) {
          DroneMessagesGrpc.getWelcomeMethod = getWelcomeMethod =
              io.grpc.MethodDescriptor.<com.example.messages.DroneMessagesOuterClass.WelcomeMessage, com.example.messages.DroneMessagesOuterClass.AnswerWelcome>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "welcome"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.example.messages.DroneMessagesOuterClass.WelcomeMessage.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.example.messages.DroneMessagesOuterClass.AnswerWelcome.getDefaultInstance()))
              .setSchemaDescriptor(new DroneMessagesMethodDescriptorSupplier("welcome"))
              .build();
        }
      }
    }
    return getWelcomeMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static DroneMessagesStub newStub(io.grpc.Channel channel) {
    return new DroneMessagesStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static DroneMessagesBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new DroneMessagesBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static DroneMessagesFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new DroneMessagesFutureStub(channel);
  }

  /**
   */
  public static abstract class DroneMessagesImplBase implements io.grpc.BindableService {

    /**
     */
    public void welcome(com.example.messages.DroneMessagesOuterClass.WelcomeMessage request,
        io.grpc.stub.StreamObserver<com.example.messages.DroneMessagesOuterClass.AnswerWelcome> responseObserver) {
      asyncUnimplementedUnaryCall(getWelcomeMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getWelcomeMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.example.messages.DroneMessagesOuterClass.WelcomeMessage,
                com.example.messages.DroneMessagesOuterClass.AnswerWelcome>(
                  this, METHODID_WELCOME)))
          .build();
    }
  }

  /**
   */
  public static final class DroneMessagesStub extends io.grpc.stub.AbstractStub<DroneMessagesStub> {
    private DroneMessagesStub(io.grpc.Channel channel) {
      super(channel);
    }

    private DroneMessagesStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected DroneMessagesStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new DroneMessagesStub(channel, callOptions);
    }

    /**
     */
    public void welcome(com.example.messages.DroneMessagesOuterClass.WelcomeMessage request,
        io.grpc.stub.StreamObserver<com.example.messages.DroneMessagesOuterClass.AnswerWelcome> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getWelcomeMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class DroneMessagesBlockingStub extends io.grpc.stub.AbstractStub<DroneMessagesBlockingStub> {
    private DroneMessagesBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private DroneMessagesBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected DroneMessagesBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new DroneMessagesBlockingStub(channel, callOptions);
    }

    /**
     */
    public com.example.messages.DroneMessagesOuterClass.AnswerWelcome welcome(com.example.messages.DroneMessagesOuterClass.WelcomeMessage request) {
      return blockingUnaryCall(
          getChannel(), getWelcomeMethod(), getCallOptions(), request);
    }
  }

  /**
   */
  public static final class DroneMessagesFutureStub extends io.grpc.stub.AbstractStub<DroneMessagesFutureStub> {
    private DroneMessagesFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private DroneMessagesFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected DroneMessagesFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new DroneMessagesFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.example.messages.DroneMessagesOuterClass.AnswerWelcome> welcome(
        com.example.messages.DroneMessagesOuterClass.WelcomeMessage request) {
      return futureUnaryCall(
          getChannel().newCall(getWelcomeMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_WELCOME = 0;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final DroneMessagesImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(DroneMessagesImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_WELCOME:
          serviceImpl.welcome((com.example.messages.DroneMessagesOuterClass.WelcomeMessage) request,
              (io.grpc.stub.StreamObserver<com.example.messages.DroneMessagesOuterClass.AnswerWelcome>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        default:
          throw new AssertionError();
      }
    }
  }

  private static abstract class DroneMessagesBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    DroneMessagesBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return com.example.messages.DroneMessagesOuterClass.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("DroneMessages");
    }
  }

  private static final class DroneMessagesFileDescriptorSupplier
      extends DroneMessagesBaseDescriptorSupplier {
    DroneMessagesFileDescriptorSupplier() {}
  }

  private static final class DroneMessagesMethodDescriptorSupplier
      extends DroneMessagesBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    DroneMessagesMethodDescriptorSupplier(String methodName) {
      this.methodName = methodName;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.MethodDescriptor getMethodDescriptor() {
      return getServiceDescriptor().findMethodByName(methodName);
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (DroneMessagesGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new DroneMessagesFileDescriptorSupplier())
              .addMethod(getWelcomeMethod())
              .build();
        }
      }
    }
    return result;
  }
}

package grpc;

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
    value = "by gRPC proto compiler (version 1.15.0)",
    comments = "Source: user.proto")
public final class UserGrpc {

  private UserGrpc() {}

  public static final String SERVICE_NAME = "User";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<grpc.UserOuterClass.LoginRequest,
      grpc.UserOuterClass.LoginResponse> getLoginMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "login",
      requestType = grpc.UserOuterClass.LoginRequest.class,
      responseType = grpc.UserOuterClass.LoginResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<grpc.UserOuterClass.LoginRequest,
      grpc.UserOuterClass.LoginResponse> getLoginMethod() {
    io.grpc.MethodDescriptor<grpc.UserOuterClass.LoginRequest, grpc.UserOuterClass.LoginResponse> getLoginMethod;
    if ((getLoginMethod = UserGrpc.getLoginMethod) == null) {
      synchronized (UserGrpc.class) {
        if ((getLoginMethod = UserGrpc.getLoginMethod) == null) {
          UserGrpc.getLoginMethod = getLoginMethod = 
              io.grpc.MethodDescriptor.<grpc.UserOuterClass.LoginRequest, grpc.UserOuterClass.LoginResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "User", "login"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  grpc.UserOuterClass.LoginRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  grpc.UserOuterClass.LoginResponse.getDefaultInstance()))
                  .setSchemaDescriptor(new UserMethodDescriptorSupplier("login"))
                  .build();
          }
        }
     }
     return getLoginMethod;
  }

  private static volatile io.grpc.MethodDescriptor<grpc.UserOuterClass.MedRequest,
      grpc.UserOuterClass.MedResponse> getGetMedMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "getMed",
      requestType = grpc.UserOuterClass.MedRequest.class,
      responseType = grpc.UserOuterClass.MedResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<grpc.UserOuterClass.MedRequest,
      grpc.UserOuterClass.MedResponse> getGetMedMethod() {
    io.grpc.MethodDescriptor<grpc.UserOuterClass.MedRequest, grpc.UserOuterClass.MedResponse> getGetMedMethod;
    if ((getGetMedMethod = UserGrpc.getGetMedMethod) == null) {
      synchronized (UserGrpc.class) {
        if ((getGetMedMethod = UserGrpc.getGetMedMethod) == null) {
          UserGrpc.getGetMedMethod = getGetMedMethod = 
              io.grpc.MethodDescriptor.<grpc.UserOuterClass.MedRequest, grpc.UserOuterClass.MedResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "User", "getMed"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  grpc.UserOuterClass.MedRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  grpc.UserOuterClass.MedResponse.getDefaultInstance()))
                  .setSchemaDescriptor(new UserMethodDescriptorSupplier("getMed"))
                  .build();
          }
        }
     }
     return getGetMedMethod;
  }

  private static volatile io.grpc.MethodDescriptor<grpc.UserOuterClass.TakeMedRequest,
      grpc.UserOuterClass.Empty> getMedTakeMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "medTake",
      requestType = grpc.UserOuterClass.TakeMedRequest.class,
      responseType = grpc.UserOuterClass.Empty.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<grpc.UserOuterClass.TakeMedRequest,
      grpc.UserOuterClass.Empty> getMedTakeMethod() {
    io.grpc.MethodDescriptor<grpc.UserOuterClass.TakeMedRequest, grpc.UserOuterClass.Empty> getMedTakeMethod;
    if ((getMedTakeMethod = UserGrpc.getMedTakeMethod) == null) {
      synchronized (UserGrpc.class) {
        if ((getMedTakeMethod = UserGrpc.getMedTakeMethod) == null) {
          UserGrpc.getMedTakeMethod = getMedTakeMethod = 
              io.grpc.MethodDescriptor.<grpc.UserOuterClass.TakeMedRequest, grpc.UserOuterClass.Empty>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "User", "medTake"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  grpc.UserOuterClass.TakeMedRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  grpc.UserOuterClass.Empty.getDefaultInstance()))
                  .setSchemaDescriptor(new UserMethodDescriptorSupplier("medTake"))
                  .build();
          }
        }
     }
     return getMedTakeMethod;
  }

  private static volatile io.grpc.MethodDescriptor<grpc.UserOuterClass.NotTakeMedRequest,
      grpc.UserOuterClass.Empty> getMedNotTakeMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "medNotTake",
      requestType = grpc.UserOuterClass.NotTakeMedRequest.class,
      responseType = grpc.UserOuterClass.Empty.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<grpc.UserOuterClass.NotTakeMedRequest,
      grpc.UserOuterClass.Empty> getMedNotTakeMethod() {
    io.grpc.MethodDescriptor<grpc.UserOuterClass.NotTakeMedRequest, grpc.UserOuterClass.Empty> getMedNotTakeMethod;
    if ((getMedNotTakeMethod = UserGrpc.getMedNotTakeMethod) == null) {
      synchronized (UserGrpc.class) {
        if ((getMedNotTakeMethod = UserGrpc.getMedNotTakeMethod) == null) {
          UserGrpc.getMedNotTakeMethod = getMedNotTakeMethod = 
              io.grpc.MethodDescriptor.<grpc.UserOuterClass.NotTakeMedRequest, grpc.UserOuterClass.Empty>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "User", "medNotTake"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  grpc.UserOuterClass.NotTakeMedRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  grpc.UserOuterClass.Empty.getDefaultInstance()))
                  .setSchemaDescriptor(new UserMethodDescriptorSupplier("medNotTake"))
                  .build();
          }
        }
     }
     return getMedNotTakeMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static UserStub newStub(io.grpc.Channel channel) {
    return new UserStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static UserBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new UserBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static UserFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new UserFutureStub(channel);
  }

  /**
   */
  public static abstract class UserImplBase implements io.grpc.BindableService {

    /**
     */
    public void login(grpc.UserOuterClass.LoginRequest request,
        io.grpc.stub.StreamObserver<grpc.UserOuterClass.LoginResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getLoginMethod(), responseObserver);
    }

    /**
     */
    public void getMed(grpc.UserOuterClass.MedRequest request,
        io.grpc.stub.StreamObserver<grpc.UserOuterClass.MedResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getGetMedMethod(), responseObserver);
    }

    /**
     */
    public void medTake(grpc.UserOuterClass.TakeMedRequest request,
        io.grpc.stub.StreamObserver<grpc.UserOuterClass.Empty> responseObserver) {
      asyncUnimplementedUnaryCall(getMedTakeMethod(), responseObserver);
    }

    /**
     */
    public void medNotTake(grpc.UserOuterClass.NotTakeMedRequest request,
        io.grpc.stub.StreamObserver<grpc.UserOuterClass.Empty> responseObserver) {
      asyncUnimplementedUnaryCall(getMedNotTakeMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getLoginMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                grpc.UserOuterClass.LoginRequest,
                grpc.UserOuterClass.LoginResponse>(
                  this, METHODID_LOGIN)))
          .addMethod(
            getGetMedMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                grpc.UserOuterClass.MedRequest,
                grpc.UserOuterClass.MedResponse>(
                  this, METHODID_GET_MED)))
          .addMethod(
            getMedTakeMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                grpc.UserOuterClass.TakeMedRequest,
                grpc.UserOuterClass.Empty>(
                  this, METHODID_MED_TAKE)))
          .addMethod(
            getMedNotTakeMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                grpc.UserOuterClass.NotTakeMedRequest,
                grpc.UserOuterClass.Empty>(
                  this, METHODID_MED_NOT_TAKE)))
          .build();
    }
  }

  /**
   */
  public static final class UserStub extends io.grpc.stub.AbstractStub<UserStub> {
    private UserStub(io.grpc.Channel channel) {
      super(channel);
    }

    private UserStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected UserStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new UserStub(channel, callOptions);
    }

    /**
     */
    public void login(grpc.UserOuterClass.LoginRequest request,
        io.grpc.stub.StreamObserver<grpc.UserOuterClass.LoginResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getLoginMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void getMed(grpc.UserOuterClass.MedRequest request,
        io.grpc.stub.StreamObserver<grpc.UserOuterClass.MedResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getGetMedMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void medTake(grpc.UserOuterClass.TakeMedRequest request,
        io.grpc.stub.StreamObserver<grpc.UserOuterClass.Empty> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getMedTakeMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void medNotTake(grpc.UserOuterClass.NotTakeMedRequest request,
        io.grpc.stub.StreamObserver<grpc.UserOuterClass.Empty> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getMedNotTakeMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class UserBlockingStub extends io.grpc.stub.AbstractStub<UserBlockingStub> {
    private UserBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private UserBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected UserBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new UserBlockingStub(channel, callOptions);
    }

    /**
     */
    public grpc.UserOuterClass.LoginResponse login(grpc.UserOuterClass.LoginRequest request) {
      return blockingUnaryCall(
          getChannel(), getLoginMethod(), getCallOptions(), request);
    }

    /**
     */
    public grpc.UserOuterClass.MedResponse getMed(grpc.UserOuterClass.MedRequest request) {
      return blockingUnaryCall(
          getChannel(), getGetMedMethod(), getCallOptions(), request);
    }

    /**
     */
    public grpc.UserOuterClass.Empty medTake(grpc.UserOuterClass.TakeMedRequest request) {
      return blockingUnaryCall(
          getChannel(), getMedTakeMethod(), getCallOptions(), request);
    }

    /**
     */
    public grpc.UserOuterClass.Empty medNotTake(grpc.UserOuterClass.NotTakeMedRequest request) {
      return blockingUnaryCall(
          getChannel(), getMedNotTakeMethod(), getCallOptions(), request);
    }
  }

  /**
   */
  public static final class UserFutureStub extends io.grpc.stub.AbstractStub<UserFutureStub> {
    private UserFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private UserFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected UserFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new UserFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<grpc.UserOuterClass.LoginResponse> login(
        grpc.UserOuterClass.LoginRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getLoginMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<grpc.UserOuterClass.MedResponse> getMed(
        grpc.UserOuterClass.MedRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getGetMedMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<grpc.UserOuterClass.Empty> medTake(
        grpc.UserOuterClass.TakeMedRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getMedTakeMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<grpc.UserOuterClass.Empty> medNotTake(
        grpc.UserOuterClass.NotTakeMedRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getMedNotTakeMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_LOGIN = 0;
  private static final int METHODID_GET_MED = 1;
  private static final int METHODID_MED_TAKE = 2;
  private static final int METHODID_MED_NOT_TAKE = 3;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final UserImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(UserImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_LOGIN:
          serviceImpl.login((grpc.UserOuterClass.LoginRequest) request,
              (io.grpc.stub.StreamObserver<grpc.UserOuterClass.LoginResponse>) responseObserver);
          break;
        case METHODID_GET_MED:
          serviceImpl.getMed((grpc.UserOuterClass.MedRequest) request,
              (io.grpc.stub.StreamObserver<grpc.UserOuterClass.MedResponse>) responseObserver);
          break;
        case METHODID_MED_TAKE:
          serviceImpl.medTake((grpc.UserOuterClass.TakeMedRequest) request,
              (io.grpc.stub.StreamObserver<grpc.UserOuterClass.Empty>) responseObserver);
          break;
        case METHODID_MED_NOT_TAKE:
          serviceImpl.medNotTake((grpc.UserOuterClass.NotTakeMedRequest) request,
              (io.grpc.stub.StreamObserver<grpc.UserOuterClass.Empty>) responseObserver);
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

  private static abstract class UserBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    UserBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return grpc.UserOuterClass.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("User");
    }
  }

  private static final class UserFileDescriptorSupplier
      extends UserBaseDescriptorSupplier {
    UserFileDescriptorSupplier() {}
  }

  private static final class UserMethodDescriptorSupplier
      extends UserBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    UserMethodDescriptorSupplier(String methodName) {
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
      synchronized (UserGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new UserFileDescriptorSupplier())
              .addMethod(getLoginMethod())
              .addMethod(getGetMedMethod())
              .addMethod(getMedTakeMethod())
              .addMethod(getMedNotTakeMethod())
              .build();
        }
      }
    }
    return result;
  }
}

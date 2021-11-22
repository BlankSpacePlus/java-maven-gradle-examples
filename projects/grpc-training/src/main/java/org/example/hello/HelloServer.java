package org.example.hello;

import java.io.IOException;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;

/**
 * 服务端
 * @author BlankSpace
 */
public class HelloServer {

    private static final int PORT = 50051;

    private Server server;

    /**
     * 启动服务,并且接受请求
     * @throws IOException IO异常
     */
    private void start() throws IOException {
        server = ServerBuilder.forPort(PORT).addService(new HelloServiceImpl()).build().start();
        System.out.println("------服务开始启动-------");
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.err.println("------gRPC服务将随着JVM关闭而关闭-------");
            HelloServer.this.stop();
            System.err.println("------服务器端关闭------");
        }));
    }

    /**
     * 停止服务
     */
    private void stop() {
        if (server != null) {
            server.shutdown();
        }
    }


    /**
     * 服务器端阻塞到程序退出
     * @throws InterruptedException 中断异常
     */
    private void  blockUntilShutdown() throws InterruptedException {
        if (server!=null){
            server.awaitTermination();
        }
    }

    /**
     * 实现服务接口的类
     */
    private class HelloServiceImpl extends HelloServiceGrpc.HelloServiceImplBase {
        @Override
        public void hello(HelloRequest request,
                          StreamObserver<HelloResponse> responseObserver) {
            String name = request.getName();
            String greeting = "Hello, " + name + "!";
            // onNext()方法向客户端返回结果
            responseObserver.onNext(HelloResponse.newBuilder().setGreeting(greeting).
                    setCode(ResultCode.SUCCESS).build());
            // 告诉客户端这次调用已经完成
            responseObserver.onCompleted();
        }
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        HelloServer server = new HelloServer();
        server.start();
        server.blockUntilShutdown();
    }

}


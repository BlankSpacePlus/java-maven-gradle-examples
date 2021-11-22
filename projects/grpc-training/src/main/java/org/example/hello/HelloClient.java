package org.example.hello;

import java.util.concurrent.TimeUnit;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

/**
 * 客户端
 * @author BlankSpace
 */
public class HelloClient {

    private final ManagedChannel channel;

    private final HelloServiceGrpc.HelloServiceBlockingStub blockingStub;

    private static final String host = "127.0.0.1";

    private static final int IP = 50051;

    public HelloClient(String host, int port) {
        channel = ManagedChannelBuilder.forAddress(host, port).usePlaintext(true).build();
        blockingStub = HelloServiceGrpc.newBlockingStub(channel);
    }

    public void shutdown() throws InterruptedException {
        channel.shutdown().awaitTermination(5, TimeUnit.SECONDS);
    }

    public void printHelloResult(String name) {
        HelloRequest request = HelloRequest.newBuilder().setName(name).build();
        HelloResponse response = blockingStub.hello(request);
        if (response.getCode() == ResultCode.SUCCESS) {
            System.out.println(response.getGreeting());
        }
    }

    public static void main(String[] args) {
        HelloClient client = new HelloClient(host, IP);
        for (int i = 0; i < 10; i++) {
            client.printHelloResult("Zhan" + i);
        }
    }

}

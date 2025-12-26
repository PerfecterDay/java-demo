package cn.com.gtht.nio;
import java.net.InetSocketAddress;
import java.nio.channels.ServerSocketChannel;

public class MiniNettyServer {

    public static void main(String[] args) throws Exception {
        ServerSocketChannel server = ServerSocketChannel.open();
        server.configureBlocking(false);
        server.bind(new InetSocketAddress(9000));

        EventLoop eventLoop = new EventLoop();
        eventLoop.start();

        eventLoop.register(server);

        System.out.println("server started on 8080");
    }
}
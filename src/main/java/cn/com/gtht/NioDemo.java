package cn.com.gtht;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class NioDemo {

    static ExecutorService threadPoolExecutor = Executors.newFixedThreadPool(3);

    public static void main(String[] args) throws IOException {
        ServerSocketChannel ssc = ServerSocketChannel.open();
        ssc.configureBlocking(false);
        ServerSocket ss = ssc.socket();
        InetSocketAddress address = new InetSocketAddress(9000);
        ss.bind(address);

        Selector selector = Selector.open();
        ssc.register(selector, SelectionKey.OP_ACCEPT);

        while (true) {
            selector.select();
            Set<SelectionKey> selectedKeys = selector.selectedKeys();
            Iterator<SelectionKey> it = selectedKeys.iterator();
            while (it.hasNext()) {
                SelectionKey key = it.next();
                processAccept(ssc, key, selector);
                it.remove();
            }
        }
    }

    private static void processAccept(ServerSocketChannel ssc, SelectionKey key, Selector selector) throws IOException {
        if ((key.readyOps() & SelectionKey.OP_ACCEPT)
                == SelectionKey.OP_ACCEPT) {
            SocketChannel sc = ssc.accept();
            sc.configureBlocking(false);
            EventLoop eventLoop = new EventLoop();
            eventLoop.register(sc);
            threadPoolExecutor.submit(eventLoop);
        }

    }

    public static void processRead(SelectionKey key) throws IOException {
        if ((key.readyOps() & SelectionKey.OP_READ)
                == SelectionKey.OP_READ) {
            SocketChannel sc = (SocketChannel) key.channel();
            ByteBuffer redBuf = ByteBuffer.allocate(1024 * 1024);
            sc.read(redBuf);

            redBuf.flip();
            byte[] dst = new byte[redBuf.limit() - 1];
            redBuf.get(dst);

            System.out.println(new String(dst));
        }
    }
}

class EventLoop implements Runnable {
    Selector selector;

    EventLoop() throws IOException {
        selector = Selector.open();
    }

    public void register(SocketChannel sc) throws ClosedChannelException {
        sc.register(selector, SelectionKey.OP_READ);
    }

    @Override
    public void run() {
        while (true) {
            try {
                selector.select();
                Set<SelectionKey> selectedKeys = selector.selectedKeys();
                Iterator<SelectionKey> it = selectedKeys.iterator();
                while (it.hasNext()) {
                    SelectionKey key = it.next();
                    NioDemo.processRead(key);
                    it.remove();
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}

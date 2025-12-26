package cn.com.gtht.nio;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class EventLoop implements Runnable {

    private final Selector selector;
    private final Thread thread;
    private final Queue<Runnable> taskQueue = new ConcurrentLinkedQueue<>();

    EventLoop() throws IOException {
        this.selector = Selector.open();
        this.thread = new Thread(this, "event-loop");
    }

    void start() {
        thread.start();
    }

    /**
     * Netty 风格：跨线程安全提交任务
     */
    void execute(Runnable task) {
        taskQueue.offer(task);
        selector.wakeup(); // 关键
    }

    /**
     * 只能在 EventLoop 线程调用
     */
    void register(ServerSocketChannel server) {
        execute(() -> {
            try {
                server.register(selector, SelectionKey.OP_ACCEPT);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    @Override
    public void run() {
        try {
            while (true) {
                selector.select();

                // 1️⃣ 执行任务（注册、interestOps 变更等）
                Runnable task;
                while ((task = taskQueue.poll()) != null) {
                    task.run();
                }

                // 2️⃣ 处理 IO 事件
                Iterator<SelectionKey> it = selector.selectedKeys().iterator();
                while (it.hasNext()) {
                    SelectionKey key = it.next();
                    it.remove();

                    if (key.isAcceptable()) {
                        handleAccept(key);
                    } else if (key.isReadable()) {
                        handleRead(key);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void handleAccept(SelectionKey key) throws IOException {
        ServerSocketChannel server = (ServerSocketChannel) key.channel();
        SocketChannel client = server.accept();
        client.configureBlocking(false);
        client.register(selector, SelectionKey.OP_READ);
    }

    ExecutorService workers = Executors.newFixedThreadPool(4);

    private void handleRead(SelectionKey key) throws IOException {
        SocketChannel ch = (SocketChannel) key.channel();
        ByteBuffer buf = ByteBuffer.allocate(1024);

        int n = ch.read(buf);
        if (n < 0) {
            ch.close();
            return;
        }

        buf.flip();

        workers.submit(() -> {
            // 模拟业务处理
            try {
                byte[] dst = new byte[buf.limit() - 1];
                buf.get(dst);
                System.out.println(new String(dst));
                Thread.sleep(100);
//                buf.put("hello guys".getBytes());
            } catch (InterruptedException ignored) {
            }

            // 回到 EventLoop 写数据
            execute(() -> {
                try {
                    ch.write(buf);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        });
    }
}

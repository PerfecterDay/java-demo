package cn.com.gtht;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class ServerSocketDemo {

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(9000,3);
        ExecutorService executorService = Executors.newFixedThreadPool(20);
        ((ThreadPoolExecutor) executorService).prestartAllCoreThreads();

        while (true){
            Socket socket = serverSocket.accept();
//            new Thread(new Processor(socket)).start();
            executorService.submit(new Processor(socket));
        }
    }


    static class Processor implements Runnable{
        Socket socket;

        public Processor(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            try{
                InputStream inputStream = socket.getInputStream();

                byte readBuf[] = new byte[256];
                while (inputStream.read(readBuf) > 0){
                    // 1. 读取数据
                    System.out.println(new String(readBuf));

                    // 2.响应数据
                    OutputStream outputStream = socket.getOutputStream();
                    outputStream.write("Hello".getBytes());
                    outputStream.flush();

                    //3. 回到循环等待客户端数据
                }
                socket.close();

            } catch (Exception e) {
                throw new RuntimeException(e);
            }

        }
    }
}
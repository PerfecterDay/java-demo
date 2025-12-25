package com.gtja.gjyw;


import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Author: zhongzhu.wang
 * Date:2025/12/11 15:06
 */
public class NetDemo {
    public static void main(String[] args) throws IOException, InterruptedException {
        ServerSocket serverSocket = new ServerSocket(10000, 2);
        while (true) {
            Socket clientSocket = serverSocket.accept();
            if (clientSocket.isConnected()) {
                System.out.println(clientSocket.getInetAddress().getHostAddress() + ":" + clientSocket.getPort());
            }
            clientSocket.getOutputStream().write("hello".getBytes());
            Thread.sleep(1000000);
        }
    }
}

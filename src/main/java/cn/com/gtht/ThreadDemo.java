package cn.com.gtht;

import java.io.IOException;
import java.net.ServerSocket;

public class ThreadDemo {
    public static void main(String[] args) {
        Object o = new Object();
        Thread t1 = new Thread(() -> {
            System.out.println("hello");
            doNothing(1);
            try {
                synchronized (o){
                    doNothing(3);
                }
                Thread.sleep(3*1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        System.out.println(t1.getState());

        t1.start();
        System.out.println(t1.getState());

        new Thread(()->{
            while (true){
                Thread.State state = t1.getState();
                if (state.equals(Thread.State.TERMINATED)){
                    System.out.println(state);
                    return;
                }
                System.out.println(state);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }).start();

        synchronized (o){
            doNothing(3);
        }

    }

    public static void doNothing(int seconds){
        long l = System.currentTimeMillis();
        long end = l + seconds*1000;
        while ( l < end){
            l = System.currentTimeMillis();
        }
    }
}

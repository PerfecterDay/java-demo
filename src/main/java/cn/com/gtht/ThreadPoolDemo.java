package cn.com.gtht;

import java.util.concurrent.*;

public class ThreadPoolDemo {


    public static void main(String[] args) {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(
                3,10,10, TimeUnit.MINUTES,new ArrayBlockingQueue<>(10),
                (r,threadPoolExecutor)->{
                    System.out.println(r);
                }
        );
//        executor.prestartCoreThread();
        executor.prestartAllCoreThreads();
//        executor.execute(()->{
//            System.out.println("hello");
//        });
        System.out.println(((ThreadPoolExecutor)executor).getPoolSize());
        System.out.println(((ThreadPoolExecutor)executor).getCorePoolSize());
        System.out.println(((ThreadPoolExecutor)executor).getActiveCount());
    }
}

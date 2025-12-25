package com.gtja.gjyw;

/**
 * Author: zhongzhu.wang
 * Date:2025/12/11 17:55
 */
public class ThreadDemo {
    public static void main(String[] args) {

        Runnable task = () -> {
            while (true) {
                try {
                    Thread.sleep(100000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        };
        new Thread(task).start();
        new Thread(task).start();
        new Thread(task).start();
//        new Thread(()->{while(true){
//            System.out.println("hello");
////            try {
////                Thread.sleep(203);
////            } catch (InterruptedException e) {
////                throw new RuntimeException(e);
////            }
//        }}).start();
    }
}

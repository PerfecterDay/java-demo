package cn.com.gtht;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Rabbit extends Animal {


    private void test() {
        super.quack();
        Rabbit r = this;
    }

    public void quack() {
        Runnable r1 = () -> {
            System.out.println(this);
        };

        Runnable r2 = new Runnable() {
            @Override
            public void run() {
                System.out.println(this);
            }
        };

        r1.run();
        r2.run();
    }

    public static void main(String[] args) throws ClassNotFoundException {

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            for (int i = 0; i < 10000; i++) {
                System.out.println("shutdown hook1");
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }));

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            for (int i = 0; i < 10000; i++) {
                System.out.println("shutdown hook2");
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }));
//        System.getenv()
//                .forEach((key,val)-> System.out.println(key+"="+val));
//
//        System.out.println("===============");
//
//        System.getProperties().forEach((key,val)-> System.out.println(key+"="+val));


    }

    public static void repeatMsg(String text, int delay) {
        Runnable task = () -> {
            System.out.println(text);
        };
        ScheduledExecutorService scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
        scheduledExecutorService.schedule(task, 10, TimeUnit.SECONDS);
    }
}

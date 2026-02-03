package spring.startup;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;

public class TestBean {


    @PostConstruct
    public void postConstruct() throws InterruptedException {
        System.out.println("Test bean postConstruct");
        Thread.currentThread().sleep(1000);
    }



    @PreDestroy
    public void preDestroy() throws InterruptedException {
        System.out.println("preDestroy");
        Thread.currentThread().sleep(1000);
    }
}

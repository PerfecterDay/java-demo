package spring.startup;


import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

//@Component
public class TestBean2 {

    @PostConstruct
    public void init() throws InterruptedException {
        System.out.println("TestBean2 init");
        Thread.currentThread().sleep(1000);
    }
}

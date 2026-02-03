package spring.lifecycle;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Start {

    @Bean(initMethod = "init",destroyMethod = "myDestroy")
    public A a(){
        return new A();
    }

    public static void main(String[] args) {
        ConfigurableApplicationContext context = new AnnotationConfigApplicationContext(Start.class);
        context.registerShutdownHook();
        context.start();
        context.stop();
    }
}

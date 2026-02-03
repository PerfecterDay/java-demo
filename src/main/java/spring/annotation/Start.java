package spring.annotation;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import spring.circle.A;
import spring.circle.B;

@SpringBootApplication
public class Start {

    @Bean
    String x() {
        return "a";
    }


    public static void main(String[] args) {
        ConfigurableApplicationContext context = new AnnotationConfigApplicationContext(Start.class);
        System.out.println("aaa");
    }
}

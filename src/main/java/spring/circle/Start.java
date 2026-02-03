package spring.circle;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import spring.scope.Prototype;

@SpringBootApplication
public class Start {


    public static void main(String[] args) {
        ConfigurableApplicationContext context = new AnnotationConfigApplicationContext(Start.class);
        A bean = context.getBean(A.class);
        B bean1 = context.getBean(B.class);
        context.close();
    }
}

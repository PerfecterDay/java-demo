package spring.annotation;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class A {
    public A() {
        System.out.println("default");
    }

    public A(String a) {
        System.out.println("with string");
    }
}

package spring.dependencies;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class A {

    @Autowired
    B b;

    @Autowired
    public void injectB(B b) {
        System.out.println("inject B");
//        this.b = b;
    }

    @Autowired
    public void setB(B b) {
        System.out.println("setB");
        this.b = b;
    }
}

package spring.circle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class A {

    @Autowired
    private B b;
//    public A(B b) {
//        this.b = b;
//    }
//    @Autowired
//    public void setB(B b) {
//        this.b = b;
//    }
}

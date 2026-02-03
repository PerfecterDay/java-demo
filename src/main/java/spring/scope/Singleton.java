package spring.scope;


import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Singleton {
    private Prototype prototype;

    public Singleton(Prototype prototype) {
        this.prototype = prototype;
        System.out.println("Singleton with prototype: " + prototype);
    }

//    public Singleton() {
//        this.prototype = new Prototype();
//        System.out.println("Singleton");
//    }

    @PostConstruct
    public void init() {
        System.out.println("init");
    }

    @PreDestroy
    public void destroy() {
        System.out.println("singleton destroy");
    }
}

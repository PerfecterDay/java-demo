package spring.scope;


import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope(value = BeanDefinition.SCOPE_PROTOTYPE)
public class Prototype {

    public Prototype() {
        System.out.println("prototype created");
    }

    @PostConstruct
    public void init() {
        System.out.println("prototype init");
    }

    @PreDestroy
    public void destroy() {
        System.out.println("prototype destroy");
    }
}

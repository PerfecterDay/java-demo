package spring.containerExtension;

import jakarta.annotation.PostConstruct;
import org.jspecify.annotations.Nullable;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

@Component
public class MyBeanPostProcessor implements BeanPostProcessor , Ordered {

    @Autowired
    private A a;

    @PostConstruct
    public void init() {
        System.out.println("MyBeanPostProcessor init");
    }

    public MyBeanPostProcessor() {
    }

    @Override
    public @Nullable Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("postProcessBeforeInitialization: "+beanName+","+bean.getClass().getName());
        return null;
    }

    @Override
    public @Nullable Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("postProcessAfterInitialization");
        return null;
    }

    @Override
    public int getOrder() {
        return 10;
    }
}

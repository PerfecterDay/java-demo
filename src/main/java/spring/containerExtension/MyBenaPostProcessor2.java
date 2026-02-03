package spring.containerExtension;

import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

@Component
public class MyBenaPostProcessor2 implements BeanPostProcessor, Ordered {

    @Override
    public int getOrder() {
        return 100;
    }
}

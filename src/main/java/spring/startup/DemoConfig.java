package spring.startup;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DemoConfig {

    @Bean
    public TestBean testBean() {
        return new TestBean();
    }

}

package cn.com.gtht.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Properties;

@SpringBootApplication
@RestController
public class Application {
    public static void main(String[] args) {

        SpringApplication application = new SpringApplication(Application.class);
        Properties properties = new Properties();
        properties.setProperty("server.port","9000");
        application.setDefaultProperties(properties);
        application.run(args);

    }


    @PostMapping("/")
    public String hello() throws InterruptedException {
        Thread.sleep(10*1000);
        return "hello";
    }
}

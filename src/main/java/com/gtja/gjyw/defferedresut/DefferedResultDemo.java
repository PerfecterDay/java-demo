package com.gtja.gjyw.defferedresut;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Properties;

/**
 * Author: zhongzhu.wang
 * Date:2025/12/15 17:13
 */

@SpringBootApplication
@RestController
public class DefferedResultDemo {
    public static void main(String[] args) throws IOException {
        SpringApplication springApplication = new SpringApplication(DefferedResultDemo.class);
        Properties properties = new Properties();
        properties.put("server.port", "9000");
        springApplication.setDefaultProperties(properties);
        ConfigurableApplicationContext run = springApplication.run(args);
    }


    Map<String,DeferredResult<String>> map = new HashMap<>();

    @GetMapping("/get/{id}")
    public DeferredResult<String> deferredResult(@PathVariable String id) {
        DeferredResult<String> result = new DeferredResult<>();
        map.put(id, result);
        return result;
    }

    @GetMapping("/put/{id}")
    public Boolean putResult(@PathVariable String id) {
        DeferredResult<String> stringDeferredResult = map.get(id);
        return stringDeferredResult.setResult("I am back");
    }
}

package spring.lifecycle;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.Lifecycle;

public class A implements Lifecycle, InitializingBean, DisposableBean {

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("afterPropertiesSet");
    }

    @PostConstruct
    public void postConstruct(){
        System.out.println("postConstruct");
    }

    public void destroy(){
        System.out.println("DisposableBean destroy");
    }

    public void myDestroy(){
        System.out.println("myDestroy");
    }

    @PreDestroy
    public void preDestroy(){
        System.out.println("preDestroy");
    }

    public void init(){
        System.out.println("init");
    }

    @Override
    public void start() {
        System.out.println("lifecycle start");
        isRunning= true;
    }

    @Override
    public void stop() {
        System.out.println("lifecycle stop");
        isRunning= false;
    }

    boolean isRunning = false;
    @Override
    public boolean isRunning() {
        return isRunning;
    }
}

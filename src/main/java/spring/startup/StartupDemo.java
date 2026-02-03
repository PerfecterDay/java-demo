package spring.startup;

import org.springframework.boot.context.metrics.buffering.BufferingApplicationStartup;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
public class StartupDemo {


    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        BufferingApplicationStartup bufferingApplicationStartup = new BufferingApplicationStartup(1024);

        context.setApplicationStartup(bufferingApplicationStartup);
        context.scan(DemoConfig.class.getPackage().getName());
        context.refresh();

        bufferingApplicationStartup.getBufferedTimeline().getEvents()
                .forEach( e -> {
                    System.out.print(e.getStartupStep().getName()+":");
                    System.out.println(e.getDuration());
                });
    }

}

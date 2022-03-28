package ru.otus.spring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import ru.otus.spring.config.Config;
import ru.otus.spring.service.Application;

@ComponentScan({"ru.otus.spring"})
public class TestRunner {

    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(TestRunner.class, Config.class);
        Application application = context.getBean(Application.class);
        application.runApp();
    }
}

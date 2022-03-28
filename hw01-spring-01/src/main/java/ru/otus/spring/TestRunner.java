package ru.otus.spring;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.otus.spring.service.TestService;

import java.util.Scanner;


public class TestRunner {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("/spring-context.xml");
        TestService testService = (TestService) context.getBean("testService");
        Scanner scanner = new Scanner(System.in);
        testService.runTest(scanner);
        scanner.close();
        context.close();
    }
}

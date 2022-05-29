package ru.otus.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories("ru.otus.spring.dao.repository")
@EntityScan("ru.otus.spring.dao.entity")
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class);
    }
}

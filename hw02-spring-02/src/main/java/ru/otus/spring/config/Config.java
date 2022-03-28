package ru.otus.spring.config;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import ru.otus.spring.domain.SimpleTest;
import ru.otus.spring.domain.SimpleTestImpl;
import ru.otus.spring.service.Impl.TestServiceImpl;
import ru.otus.spring.service.TestService;

@Configuration
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@PropertySource("classpath:app.properties")
public class Config {

    String path;

    public Config(@Value("${app.path}") String path) {
        this.path = path;
    }

    @Bean
    public SimpleTest simpleTest() {
        SimpleTest simpleTest = new SimpleTestImpl();
        simpleTest.loadTest(path);
        return simpleTest;
    }

    @Bean
    public TestService testService(SimpleTest simpleTest) {
        return new TestServiceImpl(simpleTest);
    }

}

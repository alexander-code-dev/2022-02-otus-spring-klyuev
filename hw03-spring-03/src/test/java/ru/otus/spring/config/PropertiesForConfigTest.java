package ru.otus.spring.config;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import ru.otus.spring.service.Impl.MessageServiceImpl;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ContextConfiguration(classes = {PropertiesForConfig.class})
@EnableConfigurationProperties
class PropertiesForConfigTest {

    @Autowired
    PropertiesForConfig properties;

    @Test
    @DisplayName("property creation check")
    void getPath() {
        assertEquals(properties.getPath(), "questions.csv");
    }
}
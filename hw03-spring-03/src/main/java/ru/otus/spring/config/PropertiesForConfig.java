package ru.otus.spring.config;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "application")
@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
public class PropertiesForConfig {
    String path;
    String locale;
    String problem;
    String messageNotLoaded;
    String ready;
    String answer;
    String question;
    String summingResults;
    String totalQuestions;
    String correctAnswers;
    String wrongAnswers;
}

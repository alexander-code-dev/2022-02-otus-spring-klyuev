package ru.otus.spring.config;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.otus.spring.domain.SimpleTest;
import ru.otus.spring.domain.SimpleTestImpl;
import ru.otus.spring.service.IOService;
import ru.otus.spring.service.Impl.IOStringServiceImpl;
import ru.otus.spring.service.MessageService;

import java.util.Locale;
import java.util.Scanner;

@Configuration
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@Slf4j
public class Config {

    PropertiesForConfig properties;

    @Bean
    public SimpleTest simpleTest(MessageService messageService) {

        Locale.setDefault(new Locale(properties.getLocale()));
        log.debug("current locale - {}", Locale.getDefault());

        SimpleTest simpleTest = new SimpleTestImpl();
        log.debug("current path for upload file - {}", messageService.getMessage(properties.getPath()));
        simpleTest.loadTest(messageService.getMessage(properties.getPath()));
        return simpleTest;
    }

    @Bean
    public IOService getIOService() {
        return new IOStringServiceImpl(new Scanner(System.in));
    }
}

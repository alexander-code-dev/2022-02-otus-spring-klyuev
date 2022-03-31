package ru.otus.spring.service.Impl;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import ru.otus.spring.service.Application;
import ru.otus.spring.service.TestService;

import java.util.Scanner;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ApplicationImpl implements Application {

    TestService testService;

    @Override
    public void runApp() {
        Scanner scanner = new Scanner(System.in);
        testService.runTest(scanner);
        scanner.close();
    }
}

package ru.otus.spring.service;

import ru.otus.spring.domain.Question;
import ru.otus.spring.domain.SimpleTest;

import java.util.Scanner;

public interface TestService {
    void askAQuestion(Question question, Scanner scanner);
    void runTest(Scanner scanner);
    void printStatistic(SimpleTest test);
}

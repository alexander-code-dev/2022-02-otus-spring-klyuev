package ru.otus.spring.service;

import ru.otus.spring.domain.SimpleTest;
import ru.otus.spring.domain.dto.Question;

public interface TestService {
    void askAQuestion(Question question);
    void runTest();
    void printStatistic(SimpleTest test);
}

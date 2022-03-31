package ru.otus.spring.domain;

import ru.otus.spring.domain.dto.Question;

import java.util.List;

public interface SimpleTest {
    List<Question> getQuestion();
    void loadTest(String path);
}

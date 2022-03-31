package ru.otus.spring.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import ru.otus.spring.domain.SimpleTest;
import ru.otus.spring.domain.SimpleTestImpl;
import ru.otus.spring.domain.dto.Answer;
import ru.otus.spring.domain.dto.Question;
import ru.otus.spring.service.Impl.TestServiceImpl;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@ContextConfiguration(classes = {TestServiceImpl.class, SimpleTestImpl.class})
class TestServiceImplTest {

    @Autowired
    TestService testService;
    @MockBean
    SimpleTest simpleTest;

    @Test
    @DisplayName("correct answer to the question test")
    void runTest() {
        Scanner scanner = new Scanner(new ByteArrayInputStream("yes\n4".getBytes()));
        List<Answer> answers = new ArrayList<>();
        answers.add(new Answer("Paul"));
        answers.add(new Answer("Vladimir"));
        answers.add(new Answer("Anastasia"));
        answers.add(new Answer("Aleksander", true));
        Question question = new Question("Your name?", answers);
        List<Question> questions = new ArrayList<>();
        questions.add(question);
        Mockito.when(simpleTest.getQuestion()).thenReturn(questions);
        testService.runTest(scanner);
        assertEquals(question.getUserAnswer(), true);
    }
}

package ru.otus.spring.domain;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import ru.otus.spring.domain.dto.Question;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@ContextConfiguration(classes = {SimpleTestImpl.class})
@TestPropertySource("classpath:app-test.properties")
@FieldDefaults(level = AccessLevel.PRIVATE)
class SimpleTestImplTest {

    @Autowired
    SimpleTest simpleTest;
    @Value("${app.path}")
    String path;

    @Test
    @DisplayName("correct load scv file")
    void loadTestAndGetPossibleAnswer() {
        simpleTest.loadTest(path);
        Question question = simpleTest.getQuestion().get(0);
        assertEquals(question.getQuestion(), "Your name?");
        assertEquals(question.getAnswers().get(0).getPossibleAnswer(), "Paul");
        assertEquals(question.getAnswers().get(1).getPossibleAnswer(), "Vladimir");
        assertEquals(question.getAnswers().get(2).getPossibleAnswer(), "Anastasia");
        assertEquals(question.getAnswers().get(3).getPossibleAnswer(), "Aleksander");
        assertEquals(question.getAnswers().get(3).getCorrect(), true);
        assertEquals(question.getUserAnswer(), false);
    }

    @Test
    @DisplayName("correct get dto question")
    void getTest() {
        simpleTest.loadTest(path);
        assertEquals(simpleTest.getQuestion().size(), 1);
    }
}
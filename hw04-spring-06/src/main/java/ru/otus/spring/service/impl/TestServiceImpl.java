package ru.otus.spring.service.impl;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;
import ru.otus.spring.config.PropertiesForConfig;
import ru.otus.spring.domain.SimpleTest;
import ru.otus.spring.domain.dto.Question;
import ru.otus.spring.service.IOService;
import ru.otus.spring.service.MessageService;
import ru.otus.spring.service.TestService;

import java.util.List;
import java.util.Locale;
import java.util.Scanner;

@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class TestServiceImpl implements TestService {

    SimpleTest sTest;
    IOService ioService;
    MessageService mService;
    PropertiesForConfig properties;

    @Override
    public void runTest() {
        if (!sTest.isLoaded()) {
            sTest.loadTest(mService.getMessage(properties.getPath()));
        }
        ioService.setReader(new Scanner(System.in));
        if (this.sTest.getQuestion().size() != 0) {
            ioService.output(mService.getMessage(properties.getReady(), new Integer[]{sTest.getQuestion().size()}));
            ioService.output(mService.getMessage(properties.getAnswer()));
            String agree = ioService.read();
            if (agree.toLowerCase(Locale.ROOT).equals(mService.getMessage(properties.getShortAgree()))
                    || agree.toLowerCase(Locale.ROOT).equals(mService.getMessage(properties.getAgree()))
                    && this.sTest.getQuestion().size() != 0)
            {
                for (int i = 0; i < this.sTest.getQuestion().size(); i++) {
                    this.askAQuestion(this.sTest.getQuestion().get(i));
                }
                this.printStatistic(this.sTest);
            } else {
                ioService.output(mService.getMessage(properties.getProblem()));
            }
        } else {
            ioService.output(mService.getMessage(properties.getMessageNotLoaded()));
        }
        //ioService.closeReader();
    }

    @Override
    public void askAQuestion(Question question) {
        ioService.output(mService.getMessage(properties.getQuestion(), new String[]{question.getQuestion()}));
        int correctAnswerNumber = 0;
        for (int i = 0; i < question.getAnswers().size(); i++) {
            int answerToQuestionNumber = i+1;
            ioService.output(answerToQuestionNumber+") "+question.getAnswers().get(i).getPossibleAnswer());
            if (question.getAnswers().get(i).getCorrect()) {
                correctAnswerNumber = i+1;
            }
        }
        ioService.output(mService.getMessage(properties.getAnswer()));
        int userAnswer = ioService.readInt();
        if (correctAnswerNumber == userAnswer) {
            question.setUserAnswer(true);
        }
    }

    @Override
    public void printStatistic(SimpleTest test) {
        List<Question> questions = test.getQuestion();
        ioService.output("\n***************************************");
        ioService.output(mService.getMessage(properties.getSummingResults()));
        ioService.output(mService.getMessage(properties.getTotalQuestions(),
                new Integer[]{questions.size()}));
        ioService.output(mService.getMessage(properties.getCorrectAnswers(),
                new Long[]{questions.stream().filter(Question::getUserAnswer).count()}));
        ioService.output(mService.getMessage(properties.getWrongAnswers(),
                new Long[]{questions.stream().filter(f -> f.getUserAnswer().equals(false)).count()}));
        ioService.output("\n");
    }
}

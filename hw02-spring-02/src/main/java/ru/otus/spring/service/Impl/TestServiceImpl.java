package ru.otus.spring.service.Impl;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import ru.otus.spring.domain.SimpleTest;
import ru.otus.spring.domain.dto.Question;
import ru.otus.spring.service.TestService;

import java.util.List;
import java.util.Locale;
import java.util.Scanner;

@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class TestServiceImpl implements TestService {

    SimpleTest test;

    @Override
    public void runTest(Scanner scanner) {
        if (this.test.getQuestion().size() != 0) {
            System.out.printf("Ready to take a %d question test y(yes)?%n", test.getQuestion().size());
            System.out.print("Enter your answer: ");
            String agree = scanner.nextLine();
            if ((agree.toLowerCase(Locale.ROOT).equals("y")
                    || agree.toLowerCase(Locale.ROOT).equals("yes"))
                    && this.test.getQuestion().size() != 0)
            {
                for (int i = 0; i < this.test.getQuestion().size(); i++) {
                    this.askAQuestion(this.test.getQuestion().get(i), scanner);
                }
                this.printStatistic(this.test);
            } else {
                System.out.println("No problem, you can come later.");
            }
        } else {
            System.out.println("Sorry, there are no suitable tests to run");
        }
    }

    @Override
    public void askAQuestion(Question question, Scanner scanner) {
        System.out.println("\nQuestion: "+question.getQuestion());
        int correctAnswerNumber = 0;
        for (int i = 0; i < question.getAnswers().size(); i++) {
            int answerToQuestionNumber = i+1;
            System.out.println(answerToQuestionNumber+") "+question.getAnswers().get(i).getPossibleAnswer());
            if (question.getAnswers().get(i).getCorrect()) {
                correctAnswerNumber = i+1;
            }
        }
        System.out.print("Enter your answer: ");
        int userAnswer = scanner.nextInt();
        if (correctAnswerNumber == userAnswer) {
            question.setUserAnswer(true);
        }
    }

    @Override
    public void printStatistic(SimpleTest test) {
        List<Question> questions = test.getQuestion();
        System.out.println("\n***************************************");
        System.out.println("Summing up the results of the test");
        System.out.println("Total questions asked: "+questions.size());
        System.out.println("Correct answers: "+questions.stream().filter(Question::getUserAnswer).count());
        System.out.println("Wrong answers: "+questions.stream().filter(f -> f.getUserAnswer().equals(false)).count()+"\n");
    }
}

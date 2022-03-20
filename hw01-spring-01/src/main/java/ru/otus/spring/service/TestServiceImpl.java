package ru.otus.spring.service;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import ru.otus.spring.domain.Question;
import ru.otus.spring.domain.SimpleTest;

import java.util.List;
import java.util.Locale;
import java.util.Scanner;

@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class TestServiceImpl implements TestService {

    SimpleTest test;

    @Override
    public void runTest(Scanner scanner) {
        if (this.test.getTest().size() != 0) {
            System.out.println("Ready to take a "+test.getTest().size()+" question test y(yes)?");
            System.out.print("Enter your answer: ");
            String agree = scanner.nextLine();
            if ((agree.toLowerCase(Locale.ROOT).equals("y")
                    || agree.toLowerCase(Locale.ROOT).equals("yes"))
                    && this.test.getTest().size() != 0)
            {
                for (int i = 0; i < this.test.getTest().size(); i++) {
                    this.askAQuestion(this.test.getTest().get(i), scanner);
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
            System.out.println(answerToQuestionNumber+") "+question.getAnswers().get(i));
            if (question.getAnswer().equals(question.getAnswers().get(i))) {
                correctAnswerNumber = i+1;
            }
        }
        System.out.print("Enter your answer: ");
        int userAnswer = scanner.nextInt();
        if (correctAnswerNumber == userAnswer) {
            question.setUserAnswer(true);
        } else {System.out.println("Не верно");}
    }

    @Override
    public void printStatistic(SimpleTest test) {
        List<Question> questions = test.getTest();
        System.out.println("\n***************************************");
        System.out.println("Summing up the results of the test");
        System.out.println("Total questions asked: "+questions.size());
        System.out.println("Correct answers: "+questions.stream().filter(Question::getUserAnswer).count());
        System.out.println("Wrong answers: "+questions.stream().filter(f -> f.getUserAnswer().equals(false)).count()+"\n");
    }
}

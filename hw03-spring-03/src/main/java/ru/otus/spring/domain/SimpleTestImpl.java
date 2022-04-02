package ru.otus.spring.domain;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import ru.otus.spring.domain.dto.Answer;
import ru.otus.spring.domain.dto.Question;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class SimpleTestImpl implements SimpleTest {

    List<Question> questions;

    public SimpleTestImpl() {
        this.questions = new ArrayList<>();
    }

    @Override
    public void loadTest(String path) {
        Resource resource = new ClassPathResource(path);
        try (var reader = new InputStreamReader(resource.getInputStream());
             var csvReader = new CSVReader(reader)) {
            String[] values;
            while ((values = csvReader.readNext()) != null) {
                this.questions.add(new Question(values[0],
                        this.createAnswersFromString(values[1], Arrays.asList(values[2].split(";")))));
            }
        } catch (CsvValidationException | IOException e) {
            log.warn(e.getMessage());
        }
    }

    public List<Answer> createAnswersFromString(String correctAnswer, List<String> answers) {
        return answers.stream()
                .map(i -> new Answer(i, i.equals(correctAnswer)))
                .collect(Collectors.toList());
    }

    @Override
    public List<Question> getQuestion() {
        return this.questions;
    }

}

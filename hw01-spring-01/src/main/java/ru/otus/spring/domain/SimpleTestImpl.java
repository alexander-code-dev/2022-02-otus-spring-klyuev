package ru.otus.spring.domain;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class SimpleTestImpl implements SimpleTest {
    List<Question> questions = new ArrayList<>();

    public SimpleTestImpl(String path) {
        Resource resource = new ClassPathResource(path);
        try (var reader = new InputStreamReader(resource.getInputStream());
             var csvReader = new CSVReader(reader)) {
            String[] values = null;
            while ((values = csvReader.readNext()) != null) {
                this.questions.add(new Question(values[0], values[1], Arrays.asList(values[2].split(";"))));
            }
        } catch (CsvValidationException | IOException e) {
            System.out.println(e.getMessage());
        }
    }
    @Override
    public List<Question> getTest() {
        return this.questions;
    }
}

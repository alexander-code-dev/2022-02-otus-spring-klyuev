package ru.otus.spring.domain;

import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;

import java.util.List;

@RequiredArgsConstructor
@ToString
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class Question {
    String question;
    String answer;
    List<String> answers;
    @Setter
    @NonFinal
    Boolean userAnswer = false;
}

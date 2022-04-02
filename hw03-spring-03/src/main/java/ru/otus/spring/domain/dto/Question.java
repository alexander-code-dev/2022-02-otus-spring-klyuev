package ru.otus.spring.domain.dto;

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
    List<Answer> answers;
    @Setter
    @NonFinal
    Boolean userAnswer = false;
}

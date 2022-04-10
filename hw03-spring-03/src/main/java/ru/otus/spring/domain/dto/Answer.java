package ru.otus.spring.domain.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

@RequiredArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Answer {
    final String possibleAnswer;
    @Setter
    Boolean correct = false;
}

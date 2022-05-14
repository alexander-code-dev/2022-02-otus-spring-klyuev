package ru.otus.spring.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CommentDto {
    Long id;
    BookDto bookDto;
    String comment;
}

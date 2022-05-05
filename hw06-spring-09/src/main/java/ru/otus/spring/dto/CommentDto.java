package ru.otus.spring.dto;

import lombok.Builder;
import lombok.Data;
import ru.otus.spring.dao.entity.Book;

@Data
@Builder
public class CommentDto {
    Long id;
    BookDto bookDto;
    String comment;
}

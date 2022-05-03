package ru.otus.spring.dto;

import lombok.Builder;
import lombok.Data;
import ru.otus.spring.dao.entity.Book;

import java.util.List;

@Data
@Builder
public class AuthorDto {
    Long id;
    String name;
    String surname;
    List<Book> books;
}

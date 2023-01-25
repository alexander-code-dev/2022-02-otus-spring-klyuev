package ru.otus.spring.mappers;

import ru.otus.spring.dao.entity.Author;
import ru.otus.spring.dao.entity.Book;
import ru.otus.spring.dto.AuthorDto;

import java.util.List;

public interface AuthorMapper {
    AuthorDto convertToDto(String authorName, String authorSurname);
    AuthorDto convertToDto(Author author, List<Book> books);
}

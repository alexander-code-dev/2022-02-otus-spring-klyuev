package ru.otus.spring.mappers;

import ru.otus.spring.dao.entity.Book;
import ru.otus.spring.dao.entity.Genre;
import ru.otus.spring.dto.GenreDto;

import java.util.List;

public interface GenreMapper {
    GenreDto convertToDto(String genreName);
    GenreDto convertToDto(Genre genre, List<Book> books);
}

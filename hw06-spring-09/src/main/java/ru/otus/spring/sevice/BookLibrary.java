package ru.otus.spring.sevice;

import ru.otus.spring.dto.AuthorDto;
import ru.otus.spring.dto.BookDto;
import ru.otus.spring.dto.DescriptionDto;
import ru.otus.spring.dto.GenreDto;

public interface BookLibrary {
    void selectBook(String id);
    void updateBook(BookDto bookDto);
    void insertBook(AuthorDto authorDto, GenreDto genreDto, BookDto bookDto, DescriptionDto descriptionDto);
    void deleteBook(Integer id);
}

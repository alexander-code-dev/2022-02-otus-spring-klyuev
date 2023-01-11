package ru.otus.spring.sevice;

import ru.otus.spring.dao.entity.Book;
import ru.otus.spring.dto.AuthorDto;
import ru.otus.spring.dto.BookDto;
import ru.otus.spring.dto.DescriptionDto;
import ru.otus.spring.dto.GenreDto;

import java.util.List;

public interface BookLibrary {
    List<Book> selectBook(String id);
    void updateBook(BookDto bookDto);
    Long insertBook(AuthorDto authorDto, GenreDto genreDto, BookDto bookDto, DescriptionDto descriptionDto);
    void deleteBook(Integer id);
}

package ru.otus.spring.mappers;

import ru.otus.spring.dao.entity.Author;
import ru.otus.spring.dao.entity.Book;
import ru.otus.spring.dao.entity.Genre;
import ru.otus.spring.dto.*;

import java.util.List;

public interface Mappers {
    GenreDto getGenreDto(String genreName);
    GenreDto getGenreDto(Genre genre, List<Book> books);
    DescriptionDto getDescriptionDto(String description);
    BookDto getBookDto(Long id, String bookName, Integer pageVolume, Integer bookReleaseYear);
    BookDto getBookDto(String bookName, Integer pageVolume, Integer bookReleaseYear);
    BookDto getBookDto(Book book);
    AuthorDto getAuthorDto(String authorName, String authorSurname);
    AuthorDto getAuthorDto(Author author, List<Book> books);
    CommentDto getCommentDto(Long id, String comment);
}

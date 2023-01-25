package ru.otus.spring.mappers.impl;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;
import ru.otus.spring.dao.entity.Author;
import ru.otus.spring.dao.entity.Book;
import ru.otus.spring.dao.entity.Genre;
import ru.otus.spring.dto.*;
import ru.otus.spring.mappers.*;

import java.util.List;

@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class MappersImpl implements Mappers {

    GenreMapper genreMapper;
    DescriptionMapper descriptionMapper;
    BookMapper bookMapper;
    AuthorMapper authorMapper;
    CommentMapper commentMapper;

    @Override
    public GenreDto getGenreDto(String genreName) {
        return genreMapper.convertToDto(genreName);
    }

    @Override
    public GenreDto getGenreDto(Genre genre, List<Book> books) {
        return genreMapper.convertToDto(genre, books);
    }

    @Override
    public DescriptionDto getDescriptionDto(String description) {
        return descriptionMapper.convertToDto(description);
    }

    @Override
    public BookDto getBookDto(Long id, String bookName, Integer pageVolume, Integer bookReleaseYear) {
        return bookMapper.convertToDto(id, bookName, pageVolume, bookReleaseYear);
    }

    @Override
    public BookDto getBookDto(String bookName, Integer pageVolume, Integer bookReleaseYear) {
        return bookMapper.convertToDto(bookName, pageVolume, bookReleaseYear);
    }

    @Override
    public BookDto getBookDto(Book book) {
        return bookMapper.convertToDto(book);
    }

    @Override
    public AuthorDto getAuthorDto(String authorName, String authorSurname) {
        return authorMapper.convertToDto(authorName, authorSurname);
    }

    @Override
    public AuthorDto getAuthorDto(Author author, List<Book> books) {
        return authorMapper.convertToDto(author, books);
    }

    @Override
    public CommentDto getCommentDto(Long id, String comment) {
        return commentMapper.convertToDto(id, comment);
    }
}

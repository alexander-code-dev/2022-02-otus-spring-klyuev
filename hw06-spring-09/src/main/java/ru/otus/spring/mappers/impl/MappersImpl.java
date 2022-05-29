package ru.otus.spring.mappers.impl;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;
import ru.otus.spring.dto.*;
import ru.otus.spring.mappers.*;

@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class MappersImpl implements Mappers {

    GenreMapper genreMapper;
    DescriptionMapper descriptionMapper;
    BookMapper bookMapper;
    AuthorMapper authorMapper;
    CommentMapper commentMapper;

    public GenreDto getGenreDto(String genreName) {
        return genreMapper.convertToDto(genreName);
    }
    public DescriptionDto getDescriptionDto(String description) {
        return descriptionMapper.convertToDto(description);
    }
    public BookDto getBookDto(Long id, String bookName, Integer pageVolume, Integer bookReleaseYear) {
        return bookMapper.convertToDto(id, bookName, pageVolume, bookReleaseYear);
    }
    public BookDto getBookDto(String bookName, Integer pageVolume, Integer bookReleaseYear) {
        return bookMapper.convertToDto(bookName, pageVolume, bookReleaseYear);
    }
    public AuthorDto getAuthorDto(String authorName, String authorSurname) {
        return authorMapper.convertToDto(authorName, authorSurname);
    }

    @Override
    public CommentDto getCommentDto(Long id, String comment) {
        return commentMapper.convertToDto(id, comment);
    }
}

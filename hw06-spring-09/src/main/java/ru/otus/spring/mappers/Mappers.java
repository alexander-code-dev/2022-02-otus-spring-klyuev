package ru.otus.spring.mappers;

import ru.otus.spring.dto.*;

public interface Mappers {
    GenreDto getGenreDto(String genreName);
    DescriptionDto getDescriptionDto(String description);
    BookDto getBookDto(Long id, String bookName, Integer pageVolume, Integer bookReleaseYear);
    BookDto getBookDto(String bookName, Integer pageVolume, Integer bookReleaseYear);
    AuthorDto getAuthorDto(String authorName, String authorSurname);
    CommentDto getCommentDto(Long id, String comment);
}

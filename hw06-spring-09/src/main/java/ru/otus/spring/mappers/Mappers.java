package ru.otus.spring.mappers;

import ru.otus.spring.dto.AuthorDto;
import ru.otus.spring.dto.BookDto;
import ru.otus.spring.dto.DescriptionDto;
import ru.otus.spring.dto.GenreDto;

public interface Mappers {
    GenreDto getGenreDto(String genreName);
    DescriptionDto getDescriptionDto(String description);
    BookDto getBookDto(Long id, String bookName, Integer pageVolume, Integer bookReleaseYear);
    BookDto getBookDto(String bookName, Integer pageVolume, Integer bookReleaseYear);
    AuthorDto getAuthorDto(String authorName, String authorSurname);
}

package ru.otus.spring.dto;

import lombok.Builder;
import lombok.Data;
import ru.otus.spring.dao.entity.Author;
import ru.otus.spring.dao.entity.Description;
import ru.otus.spring.dao.entity.Genre;

@Data
@Builder
public class BookDto {
    Long id;
    String name;
    DescriptionDto descriptionDto;
    int pageVolume;
    int bookReleaseYear;
    AuthorDto authorDto;
    GenreDto genreDto;
}

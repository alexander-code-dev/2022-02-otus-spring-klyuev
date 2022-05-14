package ru.otus.spring.dto;

import lombok.Builder;
import lombok.Data;

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

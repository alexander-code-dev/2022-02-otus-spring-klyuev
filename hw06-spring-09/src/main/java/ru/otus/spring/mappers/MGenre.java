package ru.otus.spring.mappers;

import ru.otus.spring.dto.GenreDto;

public interface MGenre {
    GenreDto convertToDto(String genreName);
}

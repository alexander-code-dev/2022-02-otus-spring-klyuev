package ru.otus.spring.mappers;

import ru.otus.spring.dto.GenreDto;

public interface GenreMapper {
    GenreDto convertToDto(String genreName);
}

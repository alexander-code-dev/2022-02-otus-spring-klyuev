package ru.otus.spring.mappers.impl;

import org.springframework.stereotype.Component;
import ru.otus.spring.dto.GenreDto;
import ru.otus.spring.mappers.MGenre;

@Component
public class MGenreImpl implements MGenre {
    public GenreDto convertToDto(String genreName) {
        return GenreDto.builder().name(genreName).build();
    }
}

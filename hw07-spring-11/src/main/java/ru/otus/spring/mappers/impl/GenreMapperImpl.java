package ru.otus.spring.mappers.impl;

import org.springframework.stereotype.Component;
import ru.otus.spring.dto.GenreDto;
import ru.otus.spring.mappers.GenreMapper;

@Component
public class GenreMapperImpl implements GenreMapper {
    public GenreDto convertToDto(String genreName) {
        return GenreDto.builder().name(genreName).build();
    }
}

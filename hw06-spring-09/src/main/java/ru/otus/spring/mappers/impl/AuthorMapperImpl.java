package ru.otus.spring.mappers.impl;

import org.springframework.stereotype.Component;
import ru.otus.spring.dto.AuthorDto;
import ru.otus.spring.mappers.AuthorMapper;

@Component
public class AuthorMapperImpl implements AuthorMapper {
    public AuthorDto convertToDto(String authorName, String authorSurname) {
        return AuthorDto.builder()
                .name(authorName)
                .surname(authorSurname)
                .build();
    }
}

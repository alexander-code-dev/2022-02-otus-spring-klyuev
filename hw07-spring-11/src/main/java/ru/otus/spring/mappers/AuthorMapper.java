package ru.otus.spring.mappers;

import ru.otus.spring.dto.AuthorDto;

public interface AuthorMapper {
    AuthorDto convertToDto(String authorName, String authorSurname);
}

package ru.otus.spring.mappers;

import ru.otus.spring.dto.AuthorDto;

public interface MAuthor {
    AuthorDto convertToDto(String authorName, String authorSurname);
}

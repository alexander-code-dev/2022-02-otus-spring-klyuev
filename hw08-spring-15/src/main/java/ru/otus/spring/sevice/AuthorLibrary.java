package ru.otus.spring.sevice;

import ru.otus.spring.dto.AuthorDto;

import java.util.List;

public interface AuthorLibrary {
    List<AuthorDto> selectAuthor(String id);
}

package ru.otus.spring.sevice;

import ru.otus.spring.dto.GenreDto;

import java.util.List;

public interface GenreLibrary {
    List<GenreDto> selectGenre(String id);
}

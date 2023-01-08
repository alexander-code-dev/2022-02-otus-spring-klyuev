package ru.otus.spring.sevice;

import ru.otus.spring.dao.entity.Genre;

import java.util.List;

public interface GenreLibrary {
    List<Genre> selectGenre(String id);
}

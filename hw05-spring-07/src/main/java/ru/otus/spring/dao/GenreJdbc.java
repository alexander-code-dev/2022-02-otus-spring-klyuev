package ru.otus.spring.dao;

import ru.otus.spring.domain.Genre;

import java.util.List;

public interface GenreJdbc {
    long insert(Genre genre);
    Genre getById(long id);
    List<Genre> getAll();
    void update(Genre genre);
    void deleteById(long id);
}

package ru.otus.spring.dao.repository;

import ru.otus.spring.dao.entity.Genre;

import java.util.List;
import java.util.Optional;

public interface GenreRepo {
    Optional<Genre> findById(long id);
    List<Genre> findAll();
    void save(Genre genre);
    void remove(Genre genre);
}

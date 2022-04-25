package ru.otus.spring.dao.repository;

import ru.otus.spring.dao.entity.Author;

import java.util.List;
import java.util.Optional;

public interface AuthorRepo {
    Optional<Author> findById(long id);
    List<Author> findAll();
    void save(Author author);
    void remove(Author author);
}

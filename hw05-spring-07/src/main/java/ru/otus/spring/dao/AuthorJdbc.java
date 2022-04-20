package ru.otus.spring.dao;

import ru.otus.spring.domain.Author;

import java.util.List;

public interface AuthorJdbc {
    long insert(Author author);
    Author getById(long id);
    List<Author> getAll();
    void update(Author author);
    void deleteById(long id);
}

package ru.otus.spring.dao.repository;

import ru.otus.spring.dao.entity.Book;

import java.util.List;
import java.util.Optional;

public interface BookRepo {
    Optional<Book> findById(long id);
    List<Book> findAll();
    void save(Book book);
    void remove(Book book);
}

package ru.otus.spring.dao;

import ru.otus.spring.domain.Book;

import java.util.List;

public interface BookJdbc {
    long insert(Book book);
    Book getById(long id);
    List<Book> getAll();
    void update(Book book);
    void deleteById(long id);
}

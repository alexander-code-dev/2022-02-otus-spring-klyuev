package ru.otus.spring.sevice;

import ru.otus.spring.dao.entity.Author;
import ru.otus.spring.dao.entity.Book;

import java.util.List;

public interface OBook {
    void print(List<Book> books);
    void print(String str);
}

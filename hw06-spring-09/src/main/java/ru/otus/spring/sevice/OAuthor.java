package ru.otus.spring.sevice;

import ru.otus.spring.dao.entity.Author;

import java.util.List;

public interface OAuthor {
    void print(List<Author> authors);
}

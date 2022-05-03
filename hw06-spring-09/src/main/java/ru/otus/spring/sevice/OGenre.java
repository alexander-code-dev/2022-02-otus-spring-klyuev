package ru.otus.spring.sevice;

import ru.otus.spring.dao.entity.Book;
import ru.otus.spring.dao.entity.Genre;

import java.util.List;

public interface OGenre {
    void print(List<Genre> genres);
}

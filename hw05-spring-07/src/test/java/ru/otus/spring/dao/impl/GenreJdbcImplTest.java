package ru.otus.spring.dao.impl;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import ru.otus.spring.dao.GenreJdbc;
import ru.otus.spring.domain.Author;
import ru.otus.spring.domain.Genre;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Dao with genres")
@JdbcTest
@Import(GenreJdbcImpl.class)
class GenreJdbcImplTest {

    @Autowired
    GenreJdbc genreJdbc;

    @Test
    @DisplayName("Add genre")
    void insertAndGetById() {
        genreJdbc.insert(new Genre(null, "Учебная литература", new ArrayList<>()));
        assertEquals("Учебная литература", genreJdbc.getById(6).getName());
    }

    @Test
    @DisplayName("Get genre")
    void GetById() {
        genreJdbc.insert(new Genre(15L, "Учебная литература", new ArrayList<>()));
        Genre genre = genreJdbc.getById(15L);
        assertEquals("Учебная литература", genre.getName());
    }

    @Test
    @DisplayName("Get all genre")
    void getAll() {
        genreJdbc.getAll().forEach(System.out::println);
        assertEquals(5, genreJdbc.getAll().size());
    }

    @Test
    @DisplayName("Update genre by id")
    void update() {
        Genre genre = genreJdbc.getById(1);
        assertEquals("Триллер", genre.getName());
        genre.setName("Учебная литература");
        genreJdbc.update(genre);
        assertEquals("Учебная литература", genre.getName());
    }

    @Test
    @DisplayName("Delete genre by id")
    void deleteById() {
        assertEquals(5, genreJdbc.getAll().size());
        genreJdbc.deleteById(1);
        assertEquals(4, genreJdbc.getAll().size());
    }
}
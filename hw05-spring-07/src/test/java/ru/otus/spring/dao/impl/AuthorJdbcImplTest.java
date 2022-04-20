package ru.otus.spring.dao.impl;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import ru.otus.spring.dao.AuthorJdbc;
import ru.otus.spring.domain.Author;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Dao with authors")
@JdbcTest
@Import(AuthorJdbcImpl.class)
class AuthorJdbcImplTest {

    @Autowired
    AuthorJdbc authorJdbc;

    @Test
    @DisplayName("Add Author")
    void insert() {
        authorJdbc.insert(new Author(25L, "Дмитрий", "Клюев", new ArrayList<>()));
        Author author = authorJdbc.getById(25L);
        assertEquals(12, authorJdbc.getAll().size());
        assertEquals("Дмитрий", author.getName());
        assertEquals("Клюев", author.getSurname());
    }

    @Test
    @DisplayName("Get author by id")
    void getById() {
        Author author = authorJdbc.getById(11L);
        assertEquals(11, author.getId());
        assertEquals("Дмитрий", author.getName());
        assertEquals("Клюев", author.getSurname());
    }

    @Test
    @DisplayName("Get all authors")
    void getAll() {
        assertEquals(11, authorJdbc.getAll().size());
    }

    @Test
    @DisplayName("Update author by id")
    void update() {
        assertEquals(new Author(10L, "Александр", "Дюма", new ArrayList<>()), authorJdbc.getById(10));
        Author author = new Author(10L, "Владимир", "Клюев", new ArrayList<>());
        authorJdbc.update(author);
        assertEquals(new Author(10L, "Владимир", "Клюев", new ArrayList<>()), authorJdbc.getById(10));
    }

    @Test
    @DisplayName("Delete author by id")
    void delete() {
        assertEquals(11, authorJdbc.getAll().size());
        authorJdbc.deleteById(10);
        authorJdbc.deleteById(9);
        assertEquals(9, authorJdbc.getAll().size());
    }
}
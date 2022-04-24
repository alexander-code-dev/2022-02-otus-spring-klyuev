package ru.otus.spring.dao.impl;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import ru.otus.spring.dao.AuthorJdbc;
import ru.otus.spring.dao.BookJdbc;
import ru.otus.spring.dao.GenreJdbc;
import ru.otus.spring.domain.Author;
import ru.otus.spring.domain.Book;
import ru.otus.spring.domain.Genre;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Dao with books")
@JdbcTest
@Import(value = {BookJdbcImpl.class, GenreJdbcImpl.class, AuthorJdbcImpl.class})
class BookJdbcImplTest {

    @Autowired
    BookJdbc bookJdbc;

    @Test
    @DisplayName("Add/Get book")
    void insertAndGetById() {

        Author author = new Author(null, "Александр", "Клюев", new ArrayList<>());
        Genre genre = new Genre(null, "Бездельничество", new ArrayList<>());
        Book book = new Book(15L,
                "Путешествие Клюева",
                "...Бла бла бла ел, спал, гулял...",
                1589,
                2022,
                author,
                genre);

        this.bookJdbc.insert(book);

        System.out.println(this.bookJdbc.getById(15).getAuthor());
        System.out.println(this.bookJdbc.getById(15).getGenre());

        assertEquals(book.getId(), this.bookJdbc.getById(15).getId());
        assertEquals(book.getName(), this.bookJdbc.getById(15).getName());
        assertEquals(book.getDescription(), this.bookJdbc.getById(15).getDescription());
        assertEquals(book.getPageVolume(), this.bookJdbc.getById(15).getPageVolume());
        assertEquals(book.getBookReleaseYear(), this.bookJdbc.getById(15).getBookReleaseYear());
        author.setId(12L);
        assertEquals(author, this.bookJdbc.getById(15).getAuthor());
        genre.setId(6L);
        assertEquals(genre, this.bookJdbc.getById(15).getGenre());
    }

    @Test
    @DisplayName("Get all books")
    void getAll() {
        assertEquals(13, this.bookJdbc.getAll().size());
    }

    @Test
    @DisplayName("Update book by id")
    void update() {
        Book bookBeforeUpdate = this.bookJdbc.getById(11);
        assertEquals("Всё та же я", bookBeforeUpdate.getName());
        bookBeforeUpdate.setName("Двадцать тысяч лье под водой");
        this.bookJdbc.update(bookBeforeUpdate);
        Book bookAfterUpdate = this.bookJdbc.getById(11);
        assertEquals("Двадцать тысяч лье под водой", bookAfterUpdate.getName());
    }

    @Test
    @DisplayName("Delete book by id")
    void deleteById() {
        assertEquals(13, this.bookJdbc.getAll().size());
        this.bookJdbc.deleteById(13);
        assertEquals(12, this.bookJdbc.getAll().size());
    }
}
package ru.otus.spring.dao.repository.impl;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.ComponentScan;
import ru.otus.spring.dao.entity.Author;
import ru.otus.spring.dao.entity.Book;
import ru.otus.spring.dao.entity.Description;
import ru.otus.spring.dao.entity.Genre;
import ru.otus.spring.dao.repository.*;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ComponentScan({"ru.otus.spring.dao.repository.impl"})
@DisplayName("Repository with books")
class BookRepoImplTest {

    @Autowired
    CRUD<Genre> genreRepo;
    @Autowired
    CRUD<Description> descriptionRepo;
    @Autowired
    CRUD<Book> bookRepo;
    @Autowired
    CRUD<Author> authorRepo;
    @Autowired
    TestEntityManager testEntityManager;

    @Test
    @DisplayName("Get book by id")
    void findById() {
        Book book = testEntityManager.find(Book.class, 1L);
        assertThat(bookRepo.findById(1)).isPresent().get()
                .usingRecursiveComparison().isEqualTo(book);
        Optional<Book> OBook = bookRepo.findById(1);
        if (OBook.isPresent()) {
            assertEquals("Стивен", OBook.get().getAuthor().getName());
            assertEquals("Кинг", OBook.get().getAuthor().getSurname());
            assertEquals("Триллер", OBook.get().getGenre().getName());
        } else {
            fail();
        }
    }

    @Test
    @DisplayName("Get all books")
    void findAll() {
        assertEquals(13, bookRepo.findAll().size());
    }

    @Test
    @DisplayName("Updating or adding book")
    void save() {

        assertEquals(13, descriptionRepo.findAll().size());
        Description description = new Description();
        description.setDescription("...Бла бла бла ел, спал, гулял...");
        descriptionRepo.save(description);

        assertEquals(11, authorRepo.findAll().size());
        Author author = new Author();
        author.setName("Чиполино");
        author.setSurname("Чипало");
        authorRepo.save(author);

        assertEquals(5, genreRepo.findAll().size());
        Genre genre = new Genre();
        genre.setName("Романтик колекшен");
        genreRepo.save(genre);

        assertEquals(13, bookRepo.findAll().size());
        Book book = new Book();
        book.setName("Путешествие Клюева");
        book.setPageVolume(1589);
        book.setBookReleaseYear(2022);
        book.setDescription(description);
        book.setAuthor(author);
        book.setGenre(genre);

        bookRepo.save(book);

        assertEquals(14, descriptionRepo.findAll().size());
        assertEquals(12, authorRepo.findAll().size());
        assertEquals(14, bookRepo.findAll().size());
        assertEquals(6, genreRepo.findAll().size());

        assertThat(bookRepo.findById(14))
                .isPresent()
                .get()
                .usingRecursiveComparison()
                .isEqualTo(testEntityManager.find(Book.class, 14L));

        book = testEntityManager.find(Book.class, 14L);
        book.setName("Путешествие Клюева завершилось");
        book.setPageVolume(25001);
        book.setBookReleaseYear(1999);
        bookRepo.save(book);

        assertThat(bookRepo.findById(14))
                .isPresent()
                .get()
                .usingRecursiveComparison()
                .isEqualTo(book);
    }

    @Test
    @DisplayName("Removing book")
    void remove() {
        assertEquals(13, bookRepo.findAll().size());
        Optional<Book> OBook = bookRepo.findById(10);
        Book book;
        if (OBook.isPresent()) {
            book = OBook.get();
            bookRepo.remove(book);
        } else {
            fail();
        }
        assertEquals(12, bookRepo.findAll().size());
        assertTrue(bookRepo.findById(10).isEmpty());
    }
}
package ru.otus.spring.dao.repository.impl;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import ru.otus.spring.dao.entity.Author;
import ru.otus.spring.dao.repository.AuthorRepo;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@Import(AuthorRepoImpl.class)
@DisplayName("Repository with authors")
class AuthorRepoImplTest {

    @Autowired
    AuthorRepo authorRepo;
    @Autowired
    TestEntityManager testEntityManager;

    @Test
    @DisplayName("Get author by id")
    void findById() {
        Author author = testEntityManager.find(Author.class, 1L);
        assertThat(authorRepo.findById(1)).isPresent().get()
                .usingRecursiveComparison().isEqualTo(author);
        Optional<Author> OAuthor = authorRepo.findById(1);
        int countBooks = 0;
        if (OAuthor.isPresent()) {
            countBooks = OAuthor.get().getBooks().size();
        } else {
            fail();
        }
        assertEquals(author.getBooks().size(), countBooks);
    }

    @Test
    @DisplayName("Get all authors")
    void findAll() {
        assertEquals(11, authorRepo.findAll().size());
    }

    @Test
    @DisplayName("Updating or adding author")
    void save() {
        assertEquals(11, authorRepo.findAll().size());
        Author author = new Author();
        author.setName("Чиполино");
        author.setSurname("Чиполотсович");
        authorRepo.save(author);
        assertEquals(12, authorRepo.findAll().size());

        assertThat(authorRepo.findById(12))
                .isPresent()
                .get()
                .usingRecursiveComparison()
                .isEqualTo(testEntityManager.find(Author.class, 12L));

        author = testEntityManager.find(Author.class, 12L);
        author.setName("Анатолий");
        author.setSurname("Филипов");
        authorRepo.save(author);

        assertThat(authorRepo.findById(12))
                .isPresent()
                .get()
                .usingRecursiveComparison()
                .isEqualTo(author);
    }

    @Test
    @DisplayName("Removing author")
    void remove() {
        Optional<Author> OAuthor = authorRepo.findById(10);
        Author author1;
        if (OAuthor.isPresent()) {
            author1 = OAuthor.get();
            authorRepo.remove(author1);
        } else {
            fail();
        }
        assertEquals(10, authorRepo.findAll().size());
        assertTrue(authorRepo.findById(10).isEmpty());
    }
}
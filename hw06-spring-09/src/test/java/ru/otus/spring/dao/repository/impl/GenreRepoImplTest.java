package ru.otus.spring.dao.repository.impl;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import ru.otus.spring.dao.entity.Author;
import ru.otus.spring.dao.entity.Genre;
import ru.otus.spring.dao.repository.AuthorRepo;
import ru.otus.spring.dao.repository.GenreRepo;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@Import(GenreRepoImpl.class)
@DisplayName("Repository with genres")
class GenreRepoImplTest {

    @Autowired
    GenreRepo genreRepo;
    @Autowired
    TestEntityManager testEntityManager;

    @Test
    @DisplayName("Get genre by id")
    void findById() {
        Genre genre = testEntityManager.find(Genre.class, 1L);
        assertThat(genreRepo.findById(1)).isPresent().get()
                .usingRecursiveComparison().isEqualTo(genre);
        Optional<Genre> OGenre = genreRepo.findById(1);
        int countBooks = 0;
        if (OGenre.isPresent()) {
            countBooks = OGenre.get().getBooks().size();
        } else {
            fail();
        }
        assertEquals(genre.getBooks().size(), countBooks);
    }

    @Test
    @DisplayName("Get all genres")
    void findAll() {
        assertEquals(5, genreRepo.findAll().size());
    }

    @Test
    @DisplayName("Updating or adding genre")
    void save() {
        assertEquals(5, genreRepo.findAll().size());
        Genre genre = new Genre();
        genre.setName("Учебник");
        genreRepo.save(genre);
        assertEquals(6, genreRepo.findAll().size());

        assertThat(genreRepo.findById(6))
                .isPresent()
                .get()
                .usingRecursiveComparison()
                .isEqualTo(testEntityManager.find(Genre.class, 6L));

        genre = testEntityManager.find(Genre.class, 6L);
        genre.setName("Обычная книжка");
        genreRepo.save(genre);

        assertThat(genreRepo.findById(6))
                .isPresent()
                .get()
                .usingRecursiveComparison()
                .isEqualTo(genre);
    }

    @Test
    @DisplayName("Removing genre")
    void remove() {
        Optional<Genre> OGenre = genreRepo.findById(5);
        Genre genre;
        if (OGenre.isPresent()) {
            genre = OGenre.get();
            genreRepo.remove(genre);
        } else {
            fail();
        }
        assertEquals(4, genreRepo.findAll().size());
        assertTrue(genreRepo.findById(5).isEmpty());
    }
}
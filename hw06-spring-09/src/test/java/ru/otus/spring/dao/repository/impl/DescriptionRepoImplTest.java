package ru.otus.spring.dao.repository.impl;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import ru.otus.spring.dao.entity.Book;
import ru.otus.spring.dao.entity.Description;
import ru.otus.spring.dao.repository.CRUD;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@Import(DescriptionRepoImpl.class)
@DisplayName("Repository with descriptions")
class DescriptionRepoImplTest {

    @Autowired
    CRUD<Description> descriptionRepo;
    @Autowired
    TestEntityManager testEntityManager;

    @Test
    @DisplayName("Get description by id")
    void findById() {
        Description description = testEntityManager.find(Description.class, 1L);
        assertThat(descriptionRepo.findById(1)).isPresent().get()
                .usingRecursiveComparison().isEqualTo(description);
        Optional<Description> ODescription = descriptionRepo.findById(1);
        Book book;
        if (ODescription.isPresent()) {
            book = ODescription.get().getBook();
        } else {
            book = new Book();
        }
        assertEquals(description.getBook(), book);
    }

    @Test
    @DisplayName("Get all descriptions")
    void findAll() {
        assertEquals(13, descriptionRepo.findAll().size());
    }

    @Test
    @DisplayName("Updating or adding description")
    void save() {
        assertEquals(13, descriptionRepo.findAll().size());
        Description description = new Description();
        description.setDescription("Тут какое-то тестовое описание учебника");
        descriptionRepo.save(description);
        assertEquals(14, descriptionRepo.findAll().size());

        assertThat(descriptionRepo.findById(14))
                .isPresent()
                .get()
                .usingRecursiveComparison()
                .isEqualTo(testEntityManager.find(Description.class, 14L));

        description = testEntityManager.find(Description.class, 14L);
        description.setDescription("Тут какое-то тестовое описание учебника было и еще мы тут кое-что поменяли");
        descriptionRepo.save(description);

        assertThat(descriptionRepo.findById(14))
                .isPresent()
                .get()
                .usingRecursiveComparison()
                .isEqualTo(description);
    }

    @Test
    @DisplayName("Removing description")
    void remove() {
        Optional<Description> ODescription = descriptionRepo.findById(11);
        Description description;
        if (ODescription.isPresent()) {
            description = ODescription.get();
            descriptionRepo.remove(description);
        } else {
            fail();
        }
        assertEquals(12, descriptionRepo.findAll().size());
        assertTrue(descriptionRepo.findById(11).isEmpty());
    }
}
package ru.otus.spring.dao.repository.impl;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.ComponentScan;
import ru.otus.spring.dao.entity.Book;
import ru.otus.spring.dao.entity.Comment;
import ru.otus.spring.dao.entity.Description;
import ru.otus.spring.dao.entity.Genre;
import ru.otus.spring.dao.repository.CRUD;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ComponentScan({"ru.otus.spring.dao.repository.impl"})
@DisplayName("Repository with comments")
class CommentRepoImplTest {

    @Autowired
    CRUD<Comment> commentRepo;
    @Autowired
    TestEntityManager testEntityManager;

    @Test
    @DisplayName("Get comment by id")
    void findById() {
        Comment comment = testEntityManager.find(Comment.class, 1L);
        assertThat(commentRepo.findById(1)).isPresent().get()
                .usingRecursiveComparison().isEqualTo(comment);
        Optional<Comment> OComment = commentRepo.findById(1);
        Book book;
        if (OComment.isPresent()) {
            book = OComment.get().getBook();
        } else {
            book = new Book();
        }
        assertEquals(comment.getBook(), book);
    }

    @Test
    @DisplayName("Get all comments")
    void findAll() {
        assertEquals(4, commentRepo.findAll().size());
    }

    @Test
    @DisplayName("Updating or adding comment")
    void save() {
        assertEquals(4, commentRepo.findAll().size());
        Comment comment = new Comment();
        comment.setComment("Тут какой-то комментарий к книге");
        commentRepo.save(comment);
        assertEquals(5, commentRepo.findAll().size());

        assertThat(commentRepo.findById(5))
                .isPresent()
                .get()
                .usingRecursiveComparison()
                .isEqualTo(testEntityManager.find(Comment.class, 5L));

        comment = testEntityManager.find(Comment.class, 5L);
        comment.setComment("Тут какой-то комментарий к книге и еще мы тут кое-что поменяли");
        commentRepo.save(comment);

        assertThat(commentRepo.findById(5))
                .isPresent()
                .get()
                .usingRecursiveComparison()
                .isEqualTo(comment);
    }

    @Test
    @DisplayName("Removing comment")
    void remove() {
        Optional<Comment> OComment = commentRepo.findById(4);
        Comment comment;
        if (OComment.isPresent()) {
            comment = OComment.get();
            commentRepo.remove(comment);
        } else {
            fail();
        }
        assertEquals(3, commentRepo.findAll().size());
        assertTrue(commentRepo.findById(4).isEmpty());
    }
}
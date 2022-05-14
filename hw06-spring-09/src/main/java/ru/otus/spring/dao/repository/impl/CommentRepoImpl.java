package ru.otus.spring.dao.repository.impl;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;
import ru.otus.spring.dao.entity.Comment;
import ru.otus.spring.dao.repository.CommentRepo;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CommentRepoImpl implements CommentRepo {

    @PersistenceContext
    EntityManager entityManager;

    public Optional<Comment> findById(long id) {
        return Optional.ofNullable(entityManager.find(Comment.class, id));
    }

    public List<Comment> findAll() {
        TypedQuery<Comment> query = entityManager.createQuery("SELECT a FROM Comment a", Comment.class);
        return query.getResultList();
    }

    public List<Comment> findAllByBookId(long bookId) {
        TypedQuery<Comment> query = entityManager
                .createQuery("SELECT a FROM Comment a WHERE a.book.id = :bookId", Comment.class)
                .setParameter("bookId", bookId);
        return query.getResultList();
    }

    public void save(Comment comment) {
        if (comment.getId() == null) {
            entityManager.persist(comment);
        } else {
            entityManager.merge(comment);
        }
    }

    public void remove(Comment comment) {
        entityManager.remove(comment);
    }
}

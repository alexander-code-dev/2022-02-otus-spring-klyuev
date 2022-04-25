package ru.otus.spring.dao.repository.impl;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.spring.dao.entity.Book;
import ru.otus.spring.dao.repository.BookRepo;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class BookRepoImpl implements BookRepo {

    @PersistenceContext
    EntityManager entityManager;

    public Optional<Book> findById(long id) {
        return Optional.ofNullable(entityManager.find(Book.class, id));
    }

    public List<Book> findAll() {
        TypedQuery<Book> query = entityManager.createQuery("SELECT a FROM Book a", Book.class);
        return query.getResultList();
    }

    public void save(Book book) {
        if (book.getId() == null) {
            entityManager.persist(book);
        } else {
            entityManager.merge(book);
        }
    }

    public void remove(Book book) {
        entityManager.remove(book);
    }
}

package ru.otus.spring.dao.repository.impl;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;
import ru.otus.spring.dao.entity.Author;
import ru.otus.spring.dao.repository.CRUD;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthorRepoImpl implements CRUD<Author> {

    @PersistenceContext
    EntityManager entityManager;

    public Optional<Author> findById(long id) {
        return Optional.ofNullable(entityManager.find(Author.class, id));
    }

    public List<Author> findAll() {
        TypedQuery<Author> query = entityManager.createQuery("SELECT a FROM Author a", Author.class);
        return query.getResultList();
    }

    public void save(Author author) {
        if (author.getId() == null) {
            entityManager.persist(author);
        } else {
            entityManager.merge(author);
        }
    }

    public void remove(Author author) {
        entityManager.remove(author);
    }
}

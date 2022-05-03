package ru.otus.spring.dao.repository.impl;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;
import ru.otus.spring.dao.entity.Description;
import ru.otus.spring.dao.repository.CRUD;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class DescriptionRepoImpl implements CRUD<Description> {

    @PersistenceContext
    EntityManager entityManager;

    public Optional<Description> findById(long id) {
        return Optional.ofNullable(entityManager.find(Description.class, id));
    }

    public List<Description> findAll() {
        TypedQuery<Description> query = entityManager.createQuery("SELECT a FROM Description a", Description.class);
        return query.getResultList();
    }

    public void save(Description description) {
        if (description.getId() == null) {
            entityManager.persist(description);
        } else {
            entityManager.merge(description);
        }
    }

    public void remove(Description description) {
        entityManager.remove(description);
    }
}

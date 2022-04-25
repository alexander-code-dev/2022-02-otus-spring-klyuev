package ru.otus.spring.dao.repository.impl;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.spring.dao.entity.Genre;
import ru.otus.spring.dao.repository.GenreRepo;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class GenreRepoImpl implements GenreRepo {

    @PersistenceContext
    EntityManager entityManager;

    public Optional<Genre> findById(long id) {
        return Optional.ofNullable(entityManager.find(Genre.class, id));
    }

    public List<Genre> findAll() {
        TypedQuery<Genre> query = entityManager.createQuery("SELECT a FROM Genre a", Genre.class);
        return query.getResultList();
    }

    public void save(Genre genre) {
        if (genre.getId() == null) {
            entityManager.persist(genre);
        } else {
            entityManager.merge(genre);
        }
    }

    public void remove(Genre genre) {
        entityManager.remove(genre);
    }
}

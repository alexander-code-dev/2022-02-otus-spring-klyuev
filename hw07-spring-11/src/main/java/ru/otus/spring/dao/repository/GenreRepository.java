package ru.otus.spring.dao.repository;

import org.springframework.data.repository.CrudRepository;
import ru.otus.spring.dao.entity.Genre;

import java.util.List;

public interface GenreRepository extends CrudRepository<Genre, Long> {
    List<Genre> findAll();
}

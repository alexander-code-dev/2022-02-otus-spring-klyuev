package ru.otus.spring.dao.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.spring.dao.entity.Genre;

import java.util.List;

public interface GenreRepository extends MongoRepository<Genre, Long> {
    List<Genre> findAllByOrderByIdAsc();
}

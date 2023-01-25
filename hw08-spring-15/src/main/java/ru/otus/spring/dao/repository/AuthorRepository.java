package ru.otus.spring.dao.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.spring.dao.entity.Author;

import java.util.List;

public interface AuthorRepository extends MongoRepository<Author, Long> {
    List<Author> findAllByOrderByIdAsc();
}

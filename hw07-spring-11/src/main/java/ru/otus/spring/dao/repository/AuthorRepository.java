package ru.otus.spring.dao.repository;

import org.springframework.data.repository.CrudRepository;
import ru.otus.spring.dao.entity.Author;

import java.util.List;

public interface AuthorRepository extends CrudRepository<Author, Long> {
    List<Author> findAllByOrderByIdAsc();
}

package ru.otus.spring.dao.repository;

import org.springframework.data.repository.CrudRepository;
import ru.otus.spring.dao.entity.Book;

import java.util.List;

public interface BookRepository extends CrudRepository<Book, Long> {
    List<Book> findAllByOrderByIdAsc();
}

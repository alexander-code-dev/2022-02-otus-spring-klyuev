package ru.otus.spring.dao.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.spring.dao.entity.Book;

import java.util.List;

public interface BookRepository extends MongoRepository<Book, Long> {
    List<Book> findAllByOrderByIdAsc();
    List<Book> findAllByAuthorId(Long authorId);
    List<Book> findAllByGenreId(Long genreId);
}

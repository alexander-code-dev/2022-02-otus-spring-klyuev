package ru.otus.spring.dao.repository;

import java.util.List;
import java.util.Optional;

public interface CRUD<T> {
    Optional<T> findById(long id);
    List<T> findAll();
    void save(T entity);
    void remove(T entity);
}

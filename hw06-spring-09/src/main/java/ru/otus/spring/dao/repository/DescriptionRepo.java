package ru.otus.spring.dao.repository;

import ru.otus.spring.dao.entity.Description;

import java.util.List;
import java.util.Optional;

public interface DescriptionRepo {
    Optional<Description> findById(long id);
    List<Description> findAll();
    void save(Description description);
    void remove(Description description);
}

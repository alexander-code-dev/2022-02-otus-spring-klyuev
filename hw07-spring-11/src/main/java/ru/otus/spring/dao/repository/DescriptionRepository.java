package ru.otus.spring.dao.repository;

import org.springframework.data.repository.CrudRepository;
import ru.otus.spring.dao.entity.Description;

import java.util.List;

public interface DescriptionRepository extends CrudRepository<Description, Long> {
    List<Description> findAll();
}

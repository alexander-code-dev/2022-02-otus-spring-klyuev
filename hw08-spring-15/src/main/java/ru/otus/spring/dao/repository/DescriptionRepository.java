package ru.otus.spring.dao.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.spring.dao.entity.Description;

public interface DescriptionRepository extends MongoRepository<Description, Long> {

}

package ru.otus.spring.dao.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.spring.dao.entity.Comment;

public interface CommentRepository extends MongoRepository<Comment, Long> {

}

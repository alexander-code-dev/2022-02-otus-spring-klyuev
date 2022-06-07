package ru.otus.spring.dao.repository;

import org.springframework.data.repository.CrudRepository;
import ru.otus.spring.dao.entity.Comment;

import java.util.List;

public interface CommentRepository extends CrudRepository<Comment, Long> {

}

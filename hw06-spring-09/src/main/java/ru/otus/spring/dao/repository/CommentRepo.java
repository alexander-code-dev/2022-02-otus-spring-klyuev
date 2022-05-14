package ru.otus.spring.dao.repository;

import ru.otus.spring.dao.entity.Comment;

import java.util.List;

public interface CommentRepo extends CRUD<Comment> {
    List<Comment> findAllByBookId(long bookId);
}

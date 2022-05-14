package ru.otus.spring.sevice;

import ru.otus.spring.dto.CommentDto;

import java.util.List;

public interface CommentLibrary {
    List<CommentDto> getAllCommentByBookId(long bookId);
    Long insertComment(CommentDto commentDto);
    void deleteComment(Integer id);
}

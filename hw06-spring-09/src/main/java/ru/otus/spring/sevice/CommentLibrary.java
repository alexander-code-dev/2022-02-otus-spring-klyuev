package ru.otus.spring.sevice;

import ru.otus.spring.dto.BookDto;
import ru.otus.spring.dto.CommentDto;

public interface CommentLibrary {
    Long insertComment(CommentDto commentDto);
    void deleteComment(Integer id);
}

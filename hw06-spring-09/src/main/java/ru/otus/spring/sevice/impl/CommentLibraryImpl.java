package ru.otus.spring.sevice.impl;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.spring.dao.entity.Book;
import ru.otus.spring.dao.entity.Comment;
import ru.otus.spring.dao.repository.CRUD;
import ru.otus.spring.dto.BookDto;
import ru.otus.spring.dto.CommentDto;
import ru.otus.spring.sevice.CommentLibrary;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class CommentLibraryImpl implements CommentLibrary {

    CRUD<Comment> commentRepo;
    CRUD<Book> bookRepo;

    @Override
    @Transactional(readOnly = true)
    public List<CommentDto> getAllCommentsByBookId(long bookId) {
        Optional<Book> book = bookRepo.findById(bookId);
        return book.map(value -> value.getComments().stream()
                .map(m -> CommentDto.builder()
                        .id(m.getId())
                        .comment(m.getComment())
                        .bookDto(BookDto.builder().name(m.getBook().getName()).build())
                        .build())
                .collect(Collectors.toList())).orElseGet(ArrayList::new);
    }

    @Override
    @Transactional
    public void insertComment(CommentDto commentDto) {
        Long id = commentDto.getBookDto().getId();
        if (bookRepo.findById(id).isPresent()) {
            Book book = bookRepo.findById(id).get();
            Comment comment = new Comment();
            comment.setComment(commentDto.getComment());
            comment.setBook(book);
            book.getComments().add(comment);
        } else {
            log.warn("invalid book id: {}", id);
        }
    }

    @Override
    @Transactional
    public void deleteComment(Integer id) {
        long commentId = id.longValue();
        if (commentRepo.findById(commentId).isPresent()) {
            Comment comment = commentRepo.findById(commentId).get();
            commentRepo.remove(comment);
        }
    }
}

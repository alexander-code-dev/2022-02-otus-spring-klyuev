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
import ru.otus.spring.dao.repository.CommentRepo;
import ru.otus.spring.dto.BookDto;
import ru.otus.spring.dto.CommentDto;
import ru.otus.spring.sevice.CommentLibrary;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class CommentLibraryImpl implements CommentLibrary {

    CommentRepo commentRepo;
    CRUD<Book> bookRepo;

    @Override
    public List<CommentDto> getAllCommentByBookId(long bookId) {
        return commentRepo.findAllByBookId(bookId)
                .stream()
                .map(m -> CommentDto.builder()
                        .id(m.getId())
                        .comment(m.getComment())
                        .bookDto(BookDto.builder().name(m.getBook().getName()).build())
                        .build())
                .collect(Collectors.toList());
    }

    @Override
    public Long insertComment(CommentDto commentDto) {
        Comment comment = new Comment();
        Long id = commentDto.getBookDto().getId();
        if (bookRepo.findById(id).isPresent()) {
            Book book = bookRepo.findById(id).get();
            comment.setBook(book);
            comment.setComment(commentDto.getComment());
            commentRepo.save(comment);
            return comment.getId();
        } else {
            log.warn("invalid book id: {}", id);
            return null;
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

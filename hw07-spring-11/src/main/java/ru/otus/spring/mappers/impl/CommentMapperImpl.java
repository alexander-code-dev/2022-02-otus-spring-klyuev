package ru.otus.spring.mappers.impl;

import org.springframework.stereotype.Component;
import ru.otus.spring.dto.BookDto;
import ru.otus.spring.dto.CommentDto;
import ru.otus.spring.mappers.CommentMapper;

@Component
public class CommentMapperImpl implements CommentMapper {
    @Override
    public CommentDto convertToDto(Long id, String comment) {
        BookDto bookDto = BookDto.builder().id(id).build();
        return CommentDto.builder().comment(comment).bookDto(bookDto).build();
    }
}

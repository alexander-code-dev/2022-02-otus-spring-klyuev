package ru.otus.spring.mappers;

import ru.otus.spring.dto.CommentDto;

public interface CommentMapper {
    CommentDto convertToDto(Long id, String comment);
}

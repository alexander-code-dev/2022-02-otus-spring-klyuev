package ru.otus.spring.mappers.impl;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;
import ru.otus.spring.dao.entity.Author;
import ru.otus.spring.dao.entity.Book;
import ru.otus.spring.dto.AuthorDto;
import ru.otus.spring.dto.BookDto;
import ru.otus.spring.mappers.AuthorMapper;
import ru.otus.spring.mappers.BookMapper;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthorMapperImpl implements AuthorMapper {

    BookMapper bookMapper;

    public AuthorDto convertToDto(String authorName, String authorSurname) {
        return AuthorDto.builder()
                .name(authorName)
                .surname(authorSurname)
                .build();
    }
    public AuthorDto convertToDto(Author author, List<Book> books) {

        List<BookDto> bookDtoList = books.stream()
                .map(bookMapper::convertToDto)
                .collect(Collectors.toList());

        return AuthorDto.builder()
                .id(author.getId())
                .name(author.getName())
                .surname(author.getSurname())
                .bookDtoList(bookDtoList)
                .build();
    }
}

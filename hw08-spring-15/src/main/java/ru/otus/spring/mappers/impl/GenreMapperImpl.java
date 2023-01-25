package ru.otus.spring.mappers.impl;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;
import ru.otus.spring.dao.entity.Book;
import ru.otus.spring.dao.entity.Genre;
import ru.otus.spring.dto.BookDto;
import ru.otus.spring.dto.GenreDto;
import ru.otus.spring.mappers.BookMapper;
import ru.otus.spring.mappers.GenreMapper;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class GenreMapperImpl implements GenreMapper {

    BookMapper bookMapper;

    public GenreDto convertToDto(String genreName) {
        return GenreDto.builder().name(genreName).build();
    }

    public GenreDto convertToDto(Genre genre, List<Book> books) {

        List<BookDto> bookDtoList = books.stream()
                .map(bookMapper::convertToDto)
                .collect(Collectors.toList());

        return GenreDto.builder()
                .id(genre.getId())
                .name(genre.getName())
                .bookDtoList(bookDtoList)
                .build();
    }
}

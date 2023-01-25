package ru.otus.spring.mappers.impl;

import org.springframework.stereotype.Component;
import ru.otus.spring.dao.entity.Book;
import ru.otus.spring.dto.BookDto;
import ru.otus.spring.mappers.BookMapper;

@Component
public class BookMapperImpl implements BookMapper {
    public BookDto convertToDto(Long id, String bookName, Integer pageVolume, Integer bookReleaseYear) {
        return BookDto.builder()
                .id(id)
                .bookReleaseYear(bookReleaseYear)
                .name(bookName)
                .pageVolume(pageVolume)
                .build();
    }
    public BookDto convertToDto(String bookName, Integer pageVolume, Integer bookReleaseYear) {
        return BookDto.builder()
                .bookReleaseYear(bookReleaseYear)
                .name(bookName)
                .pageVolume(pageVolume)
                .build();
    }
    public BookDto convertToDto(Book book) {
        return BookDto.builder()
                .bookReleaseYear(book.getBookReleaseYear())
                .name(book.getName())
                .pageVolume(book.getPageVolume())
                .build();
    }
}

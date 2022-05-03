package ru.otus.spring.mappers.impl;

import org.springframework.stereotype.Component;
import ru.otus.spring.dto.BookDto;
import ru.otus.spring.mappers.MBook;

@Component
public class MBookImpl implements MBook {
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
}

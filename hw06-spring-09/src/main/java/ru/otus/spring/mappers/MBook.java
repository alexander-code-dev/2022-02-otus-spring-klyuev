package ru.otus.spring.mappers;

import ru.otus.spring.dto.BookDto;

public interface MBook {
    BookDto convertToDto(Long id, String bookName, Integer pageVolume, Integer bookReleaseYear);
    BookDto convertToDto(String bookName, Integer pageVolume, Integer bookReleaseYear);
}

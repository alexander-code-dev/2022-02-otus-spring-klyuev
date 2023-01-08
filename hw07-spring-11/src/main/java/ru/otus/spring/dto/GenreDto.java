package ru.otus.spring.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class GenreDto {
    Long id;
    String name;
    List<BookDto> bookDtoList;
}

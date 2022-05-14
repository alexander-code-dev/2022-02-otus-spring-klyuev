package ru.otus.spring.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class AuthorDto {
    Long id;
    String name;
    String surname;
    List<BookDto> bookDtoList;
}

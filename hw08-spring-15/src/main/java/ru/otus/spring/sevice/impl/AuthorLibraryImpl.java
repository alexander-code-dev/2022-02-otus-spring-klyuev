package ru.otus.spring.sevice.impl;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import ru.otus.spring.dao.entity.Author;
import ru.otus.spring.dao.entity.Book;
import ru.otus.spring.dao.repository.BookRepository;
import ru.otus.spring.dto.AuthorDto;
import ru.otus.spring.mappers.Mappers;
import ru.otus.spring.sevice.AuthorLibrary;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.groupingBy;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthorLibraryImpl implements AuthorLibrary {

    BookRepository bookRepository;
    Mappers mappers;

    @Override
    public List<AuthorDto> selectAuthor(String id) {
        List<AuthorDto> authors = new ArrayList<>();
        if (id.equals("all")) {
            Map<Author, List<Book>> authorMap = bookRepository.findAll().stream()
                    .collect(groupingBy(Book::getAuthor));
            authorMap.forEach((author, books) -> authors.add(mappers.getAuthorDto(author, books)));
        } else {
            long authorId = Long.parseLong(id);
            List<Book> books = bookRepository.findAllByAuthorId(authorId);
            Author author = books.stream()
                    .map(Book::getAuthor)
                    .findFirst()
                    .orElseThrow(() -> new IllegalArgumentException("id author is not correct"));
            AuthorDto authorDto = mappers.getAuthorDto(author, books);
            authors.add(authorDto);
        }
        return authors;
    }
}

package ru.otus.spring.sevice.impl;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import ru.otus.spring.dao.entity.Book;
import ru.otus.spring.dao.entity.Genre;
import ru.otus.spring.dao.repository.BookRepository;
import ru.otus.spring.dto.GenreDto;
import ru.otus.spring.mappers.Mappers;
import ru.otus.spring.sevice.GenreLibrary;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.groupingBy;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class GenreLibraryImpl implements GenreLibrary {

    BookRepository bookRepository;
    Mappers mappers;

    @Override
    public List<GenreDto> selectGenre(String id) {
        List<GenreDto> genres = new ArrayList<>();
        if (id.equals("all")) {
            Map<Genre, List<Book>> authorMap = bookRepository.findAll().stream()
                    .collect(groupingBy(Book::getGenre));
            authorMap.forEach((genre, books) -> genres.add(mappers.getGenreDto(genre, books)));
        } else {
            long genreId = Long.parseLong(id);
            List<Book> books = bookRepository.findAllByGenreId(genreId);
            System.out.println(books);
            Genre genre = books.stream()
                    .map(Book::getGenre)
                    .findFirst()
                    .orElseThrow(() -> new IllegalArgumentException("id genre is not correct"));
            GenreDto genreDto = mappers.getGenreDto(genre, books);
            genres.add(genreDto);
        }
        return genres;
    }
}

package ru.otus.spring.sevice.impl;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import ru.otus.spring.dao.entity.Author;
import ru.otus.spring.dao.entity.Book;
import ru.otus.spring.dao.entity.Description;
import ru.otus.spring.dao.entity.Genre;
import ru.otus.spring.dao.repository.AuthorRepository;
import ru.otus.spring.dao.repository.BookRepository;
import ru.otus.spring.dao.repository.DescriptionRepository;
import ru.otus.spring.dao.repository.GenreRepository;
import ru.otus.spring.dto.AuthorDto;
import ru.otus.spring.dto.BookDto;
import ru.otus.spring.dto.DescriptionDto;
import ru.otus.spring.dto.GenreDto;
import ru.otus.spring.sevice.BookLibrary;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class BookLibraryImpl implements BookLibrary {

    GenreRepository genreRepository;
    DescriptionRepository descriptionRepository;
    BookRepository bookRepository;
    AuthorRepository authorRepository;

    @Override
    public List<Book> selectBook(String id) {
        List<Book> books = new ArrayList<>();
        if (id.equals("all")) {
            books.addAll(bookRepository.findAllByOrderByIdAsc());
        } else {
            long bookId = Long.parseLong(id);
            if (bookRepository.findById(bookId).isPresent()) {
                books.add(bookRepository.findById(bookId).get());
            }
        }
        return books;
    }

    @Override
    public void updateBook(BookDto bookDto) {
        if (bookRepository.findById(bookDto.getId()).isPresent()) {
            Book book = bookRepository.findById(bookDto.getId()).get();
            if (!"unknownBookName".equals(bookDto.getName())) {
                book.setName(bookDto.getName());
            }
            if (0 != bookDto.getPageVolume()) {
                book.setPageVolume(bookDto.getPageVolume());
            }
            if (0 != bookDto.getBookReleaseYear()) {
                book.setBookReleaseYear(bookDto.getBookReleaseYear());
            }
            bookRepository.save(book);
        }
    }

    @Override
    public Long insertBook(AuthorDto authorDto, GenreDto genreDto, BookDto bookDto, DescriptionDto descriptionDto) {

        Author author = new Author();
        author.setName(authorDto.getName());
        author.setSurname(authorDto.getSurname());
        authorRepository.save(author);

        Genre genre = new Genre();
        genre.setName(genreDto.getName());
        genreRepository.save(genre);

        Description desc = new Description();
        desc.setDescription(descriptionDto.getDescription());
        descriptionRepository.save(desc);

        Book book = new Book();
        book.setAuthor(author);
        book.setGenre(genre);
        book.setDescription(desc);
        book.setName(bookDto.getName());
        book.setBookReleaseYear(bookDto.getBookReleaseYear());
        book.setPageVolume(bookDto.getPageVolume());
        bookRepository.save(book);

        return book.getId();
    }

    @Override
    public void deleteBook(Integer id) {
        long bookId = id.longValue();
        if (bookRepository.findById(bookId).isPresent()) {
            Book book = bookRepository.findById(bookId).get();
            bookRepository.delete(book);
        }
    }
}

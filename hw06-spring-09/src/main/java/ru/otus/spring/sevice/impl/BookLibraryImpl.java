package ru.otus.spring.sevice.impl;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.spring.dao.entity.Author;
import ru.otus.spring.dao.entity.Book;
import ru.otus.spring.dao.entity.Description;
import ru.otus.spring.dao.entity.Genre;
import ru.otus.spring.dao.repository.*;
import ru.otus.spring.dto.AuthorDto;
import ru.otus.spring.dto.BookDto;
import ru.otus.spring.dto.DescriptionDto;
import ru.otus.spring.dto.GenreDto;
import ru.otus.spring.sevice.BookLibrary;
import ru.otus.spring.sevice.OBook;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class BookLibraryImpl implements BookLibrary {

    CRUD<Genre> genreRepo;
    CRUD<Description> descriptionRepo;
    CRUD<Book> bookRepo;
    CRUD<Author> authorRepo;

    OBook oBook;

    @Override
    @Transactional(readOnly = true)
    public void selectBook(String id) {
        List<Book> books = new ArrayList<>();
        if (id.equals("all")) {
            books.addAll(bookRepo.findAll());
        } else {
            long bookId = Long.parseLong(id);
            if (bookRepo.findById(bookId).isPresent()) {
                books.add(bookRepo.findById(bookId).get());
            }
        }
        oBook.print(books);
    }

    @Override
    @Transactional
    public void updateBook(BookDto bookDto) {
        if (bookRepo.findById(bookDto.getId()).isPresent()) {
            Book book = bookRepo.findById(bookDto.getId()).get();
            book.setName(bookDto.getName());
            book.setPageVolume(bookDto.getPageVolume());
            book.setBookReleaseYear(bookDto.getBookReleaseYear());
            bookRepo.save(book);
            oBook.print("Done");
        } else {
            oBook.print("Incorrectly specified id book");
        }
    }

    @Override
    @Transactional
    public void insertBook(AuthorDto authorDto, GenreDto genreDto, BookDto bookDto, DescriptionDto descriptionDto) {

        Author author = new Author();
        author.setName(authorDto.getName());
        author.setSurname(authorDto.getSurname());
        authorRepo.save(author);

        Genre genre = new Genre();
        genre.setName(genreDto.getName());
        genreRepo.save(genre);

        Description desc = new Description();
        desc.setDescription(descriptionDto.getDescription());
        descriptionRepo.save(desc);

        Book book = new Book();
        book.setAuthor(author);
        book.setGenre(genre);
        book.setDescription(desc);
        book.setName(bookDto.getName());
        book.setBookReleaseYear(bookDto.getBookReleaseYear());
        book.setPageVolume(bookDto.getPageVolume());
        bookRepo.save(book);

        oBook.print("Add new book assigned id - "+book.getId()+System.lineSeparator()+"Done");
    }

    @Override
    @Transactional
    public void deleteBook(Integer id) {
        long bookId = id.longValue();
        if (bookRepo.findById(bookId).isPresent()) {
            Book book = bookRepo.findById(bookId).get();
            bookRepo.remove(book);
            oBook.print("Done");
        } else {
            oBook.print("Incorrectly specified id book");
        }
    }
}

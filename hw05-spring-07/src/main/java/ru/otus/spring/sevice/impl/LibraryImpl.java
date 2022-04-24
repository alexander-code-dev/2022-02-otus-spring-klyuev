package ru.otus.spring.sevice.impl;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import ru.otus.spring.dao.AuthorJdbc;
import ru.otus.spring.dao.BookJdbc;
import ru.otus.spring.dao.GenreJdbc;
import ru.otus.spring.domain.Author;
import ru.otus.spring.domain.Book;
import ru.otus.spring.domain.Genre;
import ru.otus.spring.sevice.Library;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class LibraryImpl implements Library {

    GenreJdbc genreJdbc;
    AuthorJdbc authorJdbc;
    BookJdbc bookJdbc;

    @Override
    public void selectGenre(String id) {
        List<Genre> genres = new ArrayList<>();
        if (id.equals("all")) {
            genres.addAll(genreJdbc.getAll());
        } else {
            genres.add(genreJdbc.getById(Long.parseLong(id)));
        }
        genres.forEach(genre-> System.out.println(System.lineSeparator()
                +"№ "+genre.getId()+System.lineSeparator()
                +" - name: "+ genre.getName()+System.lineSeparator()
                +" - amount of books: "+ genre.getBooks().size()+System.lineSeparator()
        ));
        System.out.println("Done");
    }

    @Override
    public void selectAuthor(String id) {
        List<Author> genres = new ArrayList<>();
        if (id.equals("all")) {
            genres.addAll(authorJdbc.getAll());
        } else {
            genres.add(authorJdbc.getById(Long.parseLong(id)));
        }
        genres.forEach(genre-> System.out.println(System.lineSeparator()
                +"№ "+genre.getId()+System.lineSeparator()
                +" - name: "+ genre.getName()+System.lineSeparator()
                +" - surname: "+ genre.getSurname()+System.lineSeparator()
                +" - amount of books: "+ genre.getBooks().size()+System.lineSeparator()
        ));
        System.out.println("Done");
    }

    @Override
    public void selectBook(String id) {
        List<Book> books = new ArrayList<>();
        if (id.equals("all")) {
            books.addAll(bookJdbc.getAll());
        } else {
            books.add(bookJdbc.getById(Long.parseLong(id)));
        }
        books.forEach(f-> System.out.println(System.lineSeparator() + "№ "+f.getId()+System.lineSeparator()
                +" - name: "+ f.getName()+System.lineSeparator()
                +" - description: "+f.getDescription()+System.lineSeparator()
                +" - release: "+ f.getBookReleaseYear()+System.lineSeparator()
                +" - volume: "+ f.getPageVolume()+System.lineSeparator()
                +" - genre: "+ f.getGenre().getName()+System.lineSeparator()
                +" - author: "+ f.getAuthor().getName() + " " +f.getAuthor().getSurname()+System.lineSeparator()

        ));
        System.out.println("Done");
    }

    @Override
    public void updateBook(Integer id, String bookName, String description,
                           Integer pageVolume, Integer bookReleaseYear) {
        Book book = bookJdbc.getById(id);
        book.setName(bookName);
        book.setDescription(description);
        book.setPageVolume(pageVolume);
        book.setBookReleaseYear(bookReleaseYear);
        bookJdbc.update(book);
        System.out.println("Done");
    }

    @Override
    public void insertBook(String authorName, String authorSurname, String genreName, String bookName,
                           String description, Integer pageVolume, Integer bookReleaseYear) {
        Author author = new Author(null, authorName, authorSurname, new ArrayList<>());
        Genre genre = new Genre(null, genreName, new ArrayList<>());
        Book book = new Book(null,
                bookName,
                description,
                pageVolume,
                bookReleaseYear,
                author,
                genre);
        long bookId = bookJdbc.insert(book);
        System.out.println("Add new book assigned id - "+bookId);
        System.out.println("Done");
    }

    @Override
    public void deleteBook(Integer id) {
        bookJdbc.deleteById(id);
        System.out.println("Done");
    }
}

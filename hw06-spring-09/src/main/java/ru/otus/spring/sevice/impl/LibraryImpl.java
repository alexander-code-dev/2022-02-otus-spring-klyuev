package ru.otus.spring.sevice.impl;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.spring.dao.entity.Author;
import ru.otus.spring.dao.entity.Book;
import ru.otus.spring.dao.entity.Description;
import ru.otus.spring.dao.entity.Genre;
import ru.otus.spring.dao.repository.AuthorRepo;
import ru.otus.spring.dao.repository.BookRepo;
import ru.otus.spring.dao.repository.DescriptionRepo;
import ru.otus.spring.dao.repository.GenreRepo;
import ru.otus.spring.sevice.Library;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class LibraryImpl implements Library {

    GenreRepo genreRepo;
    DescriptionRepo descriptionRepo;
    BookRepo bookRepo;
    AuthorRepo authorRepo;

    @Override
    @Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED)
    public void selectGenre(String id) {
        List<Genre> genres = new ArrayList<>();
        if (id.equals("all")) {
            genres.addAll(genreRepo.findAll());
        } else {
            long genreId = Long.parseLong(id);
            if (genreRepo.findById(genreId).isPresent()) {
                genres.add(genreRepo.findById(genreId).get());
            }
        }
        genres.forEach(genre-> System.out.println(System.lineSeparator()
                +"№ "+genre.getId()+System.lineSeparator()
                +" - name: "+ genre.getName()+System.lineSeparator()
                +" - amount of books: "+ genre.getBooks().size()+System.lineSeparator()
        ));
        System.out.println("Done");
    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED)
    public void selectAuthor(String id) {
        List<Author> authors = new ArrayList<>();
        if (id.equals("all")) {
            authors.addAll(authorRepo.findAll());
        } else {
            long authorId = Long.parseLong(id);
            if (authorRepo.findById(authorId).isPresent()) {
                authors.add(authorRepo.findById(authorId).get());
            }
        }
        authors.forEach(author-> System.out.println(System.lineSeparator()
                +"№ "+author.getId()+System.lineSeparator()
                +" - name: "+ author.getName()+System.lineSeparator()
                +" - surname: "+ author.getSurname()+System.lineSeparator()
                +" - amount of books: "+ author.getBooks().size()+System.lineSeparator()
        ));
        System.out.println("Done");
    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED)
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
        books.forEach(f-> System.out.println(System.lineSeparator() + "№ "+f.getId()+System.lineSeparator()
                +" - name: "+ f.getName()+System.lineSeparator()
                +" - description: "+f.getDescription().getDescription()+System.lineSeparator()
                +" - release: "+ f.getBookReleaseYear()+System.lineSeparator()
                +" - volume: "+ f.getPageVolume()+System.lineSeparator()
                +" - genre: "+ f.getGenre().getName()+System.lineSeparator()
                +" - author: "+ f.getAuthor().getName() + " " +f.getAuthor().getSurname()+System.lineSeparator()

        ));
        System.out.println("Done");
    }

    @Override
    @Transactional
    public void updateBook(Integer id, String bookName, Integer pageVolume, Integer bookReleaseYear) {
        long bookId = id.longValue();
        if (bookRepo.findById(bookId).isPresent()) {
            Book book = bookRepo.findById(bookId).get();
            book.setName(bookName);
            book.setPageVolume(pageVolume);
            book.setBookReleaseYear(bookReleaseYear);
            bookRepo.save(book);
            System.out.println("Done");
        } else {
            System.out.println("Incorrectly specified id book");
        }
    }

    @Override
    @Transactional
    public void insertBook(String authorName, String authorSurname, String genreName, String bookName,
                           String description, Integer pageVolume, Integer bookReleaseYear) {
        Author author = new Author();
        author.setName(authorName);
        author.setSurname(authorSurname);
        authorRepo.save(author);

        Genre genre = new Genre();
        genre.setName(genreName);
        genreRepo.save(genre);

        Description desc = new Description();
        desc.setDescription(description);
        descriptionRepo.save(desc);

        Book book = new Book();
        book.setAuthor(author);
        book.setGenre(genre);
        book.setDescription(desc);
        book.setName(bookName);
        book.setBookReleaseYear(bookReleaseYear);
        book.setPageVolume(pageVolume);
        bookRepo.save(book);

        System.out.println("Add new book assigned id - "+book.getId());
        System.out.println("Done");
    }

    @Override
    @Transactional
    public void deleteBook(Integer id) {
        long bookId = id.longValue();
        if (bookRepo.findById(bookId).isPresent()) {
            Book book = bookRepo.findById(bookId).get();
            bookRepo.remove(book);
            System.out.println("Done");
        } else {
            System.out.println("Incorrectly specified id book");
        }
    }
}

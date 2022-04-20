package ru.otus.spring.shell;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.otus.spring.dao.AuthorJdbc;
import ru.otus.spring.dao.BookJdbc;
import ru.otus.spring.dao.GenreJdbc;
import ru.otus.spring.domain.Author;
import ru.otus.spring.domain.Book;
import ru.otus.spring.domain.Genre;

import java.util.ArrayList;
import java.util.List;

@ShellComponent
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ApplicationEventsCommands {

    BookJdbc bookJdbc;
    GenreJdbc genreJdbc;
    AuthorJdbc authorJdbc;

    @ShellMethod(key = {"sg"}, value = "Get a list of genres or get one concrete genre by id")
    public void selectGenre(@ShellOption(value = "-g_id", defaultValue = "all") String id) {
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

    @ShellMethod(key = {"sa"}, value = "Get a list of authors or get one concrete author by id")
    public void selectAuthor(@ShellOption(value = "-a_id", defaultValue = "all") String id) {
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

    @ShellMethod(key = {"sb"}, value = "Get a list of books or get one concrete book by id")
    public void selectBook(@ShellOption(value = "-b_id", defaultValue = "all") String id
    ) {
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

    @ShellMethod(key = {"ub"}, value = "Update book")
    public void updateBook(@ShellOption(value = "-b_id") Integer id,
                           @ShellOption(value = "-b_name", defaultValue = "unknownBookName") String bookName,
                           @ShellOption(value = "-b_description", defaultValue = "unknownDescription") String description,
                           @ShellOption(value = "-b_volume", defaultValue = "0") Integer pageVolume,
                           @ShellOption(value = "-b_year", defaultValue = "0") Integer bookReleaseYear
    ) {
        Book book = bookJdbc.getById(id);
        book.setName(bookName);
        book.setDescription(description);
        book.setPageVolume(pageVolume);
        book.setBookReleaseYear(bookReleaseYear);
        bookJdbc.update(book);
        System.out.println("Done");
    }

    @ShellMethod(key = {"ib"}, value = "Insert book by id")
    public void insertBook(@ShellOption(value = "-a_name", defaultValue = "unknownAuthorName") String authorName,
                           @ShellOption(value = "-a_surname", defaultValue = "unknownAuthorSurname") String authorSurname,
                           @ShellOption(value = "-g_name", defaultValue = "unknownGenreName") String genreName,
                           @ShellOption(value = "-b_name", defaultValue = "unknownBookName") String bookName,
                           @ShellOption(value = "-b_description", defaultValue = "unknownDescription") String description,
                           @ShellOption(value = "-b_volume", defaultValue = "0") Integer pageVolume,
                           @ShellOption(value = "-b_year", defaultValue = "0") Integer bookReleaseYear
    ) {
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

    @ShellMethod(key = {"db"}, value = "Delete book by id")
    public void deleteBook(@ShellOption(value = "-b-id") Integer id) {
        bookJdbc.deleteById(id);
        System.out.println("Done");
    }
}

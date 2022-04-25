package ru.otus.spring.shell;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.otus.spring.sevice.Library;

@ShellComponent
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ApplicationEventsCommands {

    Library library;

    @ShellMethod(key = {"sg"}, value = "Get a list of genres or get one concrete genre by id")
    public void selectGenre(@ShellOption(value = "-g_id", defaultValue = "all") String id) {
        library.selectGenre(id);
    }

    @ShellMethod(key = {"sa"}, value = "Get a list of authors or get one concrete author by id")
    public void selectAuthor(@ShellOption(value = "-a_id", defaultValue = "all") String id) {
        library.selectAuthor(id);
    }

    @ShellMethod(key = {"sb"}, value = "Get a list of books or get one concrete book by id")
    public void selectBook(@ShellOption(value = "-b_id", defaultValue = "all") String id) {
        library.selectBook(id);
    }

    @ShellMethod(key = {"ub"}, value = "Update book")
    public void updateBook(@ShellOption(value = "-b_id") Integer id,
                           @ShellOption(value = "-b_name", defaultValue = "unknownBookName") String bookName,
                           @ShellOption(value = "-b_volume", defaultValue = "0") Integer pageVolume,
                           @ShellOption(value = "-b_year", defaultValue = "0") Integer bookReleaseYear) {
        library.updateBook(id, bookName, pageVolume, bookReleaseYear);
    }

    @ShellMethod(key = {"ib"}, value = "Insert book by id")
    public void insertBook(@ShellOption(value = "-a_name", defaultValue = "unknownAuthorName") String authorName,
                           @ShellOption(value = "-a_surname", defaultValue = "unknownAuthorSurname") String authorSurname,
                           @ShellOption(value = "-g_name", defaultValue = "unknownGenreName") String genreName,
                           @ShellOption(value = "-b_name", defaultValue = "unknownBookName") String bookName,
                           @ShellOption(value = "-b_description", defaultValue = "unknownDescription") String description,
                           @ShellOption(value = "-b_volume", defaultValue = "0") Integer pageVolume,
                           @ShellOption(value = "-b_year", defaultValue = "0") Integer bookReleaseYear) {
        library.insertBook(authorName, authorSurname, genreName, bookName, description, pageVolume, bookReleaseYear);
    }

    @ShellMethod(key = {"db"}, value = "Delete book by id")
    public void deleteBook(@ShellOption(value = "-b-id") Integer id) {
        library.deleteBook(id);
    }
}

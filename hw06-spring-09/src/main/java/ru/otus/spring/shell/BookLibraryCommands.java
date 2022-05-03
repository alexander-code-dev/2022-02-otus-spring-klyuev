package ru.otus.spring.shell;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.otus.spring.dto.AuthorDto;
import ru.otus.spring.dto.BookDto;
import ru.otus.spring.dto.DescriptionDto;
import ru.otus.spring.dto.GenreDto;
import ru.otus.spring.mappers.Mappers;
import ru.otus.spring.sevice.BookLibrary;

@ShellComponent
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class BookLibraryCommands {

    BookLibrary bookLibrary;
    Mappers mappers;

    @ShellMethod(key = {"sb"}, value = "Get a list of books or get one concrete book by id")
    public void selectBook(@ShellOption(value = "-b_id", defaultValue = "all") String id) {
        bookLibrary.selectBook(id);
    }

    @ShellMethod(key = {"ub"}, value = "Update book")
    public void updateBook(@ShellOption(value = "-b_id") Integer id,
                           @ShellOption(value = "-b_name", defaultValue = "unknownBookName") String bookName,
                           @ShellOption(value = "-b_volume", defaultValue = "0") Integer pageVolume,
                           @ShellOption(value = "-b_year", defaultValue = "0") Integer bookReleaseYear) {
        BookDto bookDto = mappers.getBookDto(Long.valueOf(id), bookName, pageVolume, bookReleaseYear);
        bookLibrary.updateBook(bookDto);
    }

    @ShellMethod(key = {"ib"}, value = "Insert book by id")
    public void insertBook(@ShellOption(value = "-a_name", defaultValue = "unknownAuthorName") String authorName,
                           @ShellOption(value = "-a_surname", defaultValue = "unknownAuthorSurname") String authorSurname,
                           @ShellOption(value = "-g_name", defaultValue = "unknownGenreName") String genreName,
                           @ShellOption(value = "-b_name", defaultValue = "unknownBookName") String bookName,
                           @ShellOption(value = "-b_description", defaultValue = "unknownDescription") String description,
                           @ShellOption(value = "-b_volume", defaultValue = "0") Integer pageVolume,
                           @ShellOption(value = "-b_year", defaultValue = "0") Integer bookReleaseYear) {

        BookDto bookDto = mappers.getBookDto(bookName, pageVolume, bookReleaseYear);
        AuthorDto authorDto = mappers.getAuthorDto(authorName, authorSurname);
        DescriptionDto descriptionDto = mappers.getDescriptionDto(description);
        GenreDto genreDto = mappers.getGenreDto(genreName);

        bookLibrary.insertBook(authorDto, genreDto, bookDto, descriptionDto);
    }

    @ShellMethod(key = {"db"}, value = "Delete book by id")
    public void deleteBook(@ShellOption(value = "-b-id") Integer id) {
        bookLibrary.deleteBook(id);
    }
}

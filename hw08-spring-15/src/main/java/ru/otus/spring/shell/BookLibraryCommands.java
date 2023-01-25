package ru.otus.spring.shell;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.spring.dao.entity.Book;
import ru.otus.spring.dao.entity.Comment;
import ru.otus.spring.dto.AuthorDto;
import ru.otus.spring.dto.BookDto;
import ru.otus.spring.dto.DescriptionDto;
import ru.otus.spring.dto.GenreDto;
import ru.otus.spring.mappers.Mappers;
import ru.otus.spring.sevice.BookLibrary;

import java.util.List;
import java.util.StringJoiner;

@ShellComponent
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class BookLibraryCommands {

    BookLibrary bookLibrary;
    Mappers mappers;

    @ShellMethod(key = {"sb"}, value = "Get a list of books or get one concrete book by id")
    @Transactional(readOnly = true)
    public void printBook(@ShellOption(value = "-b_id", defaultValue = "all") String id) {
        List<Book> books = bookLibrary.selectBook(id);
        if (books.isEmpty()) {
            System.out.println("No books found");
        }
        if (books.size() == 1) {
            String delimiter = System.lineSeparator()+"     ";
            StringJoiner joiner = new StringJoiner(delimiter);

            List<Comment> comments = books.get(0).getComments();
            if (comments != null) {
                for (Comment comment:(books.get(0).getComments())) {
                    joiner.add(comment.getId()+") "+comment.getComment());
                }
            } else {
                joiner.add(" (no comments)");
            }

            System.out.println(System.lineSeparator() + "№ "+books.get(0).getId()+System.lineSeparator()
                    +" - name: "+ books.get(0).getName()+System.lineSeparator()
                    +" - description: "+books.get(0).getDescription().getDescription()+System.lineSeparator()
                    +" - release: "+ books.get(0).getBookReleaseYear()+System.lineSeparator()
                    +" - volume: "+ books.get(0).getPageVolume()+System.lineSeparator()
                    +" - genre: "+ books.get(0).getGenre().getName()+System.lineSeparator()
                    +" - author: "+ books.get(0).getAuthor().getName() + " " +books.get(0).getAuthor().getSurname()+System.lineSeparator()
                    +" - comment: "+delimiter+joiner);
        } else {
            books.forEach(f-> System.out.println(System.lineSeparator() + "№ "+f.getId()+System.lineSeparator()
                    +" - name: "+ f.getName()+System.lineSeparator()
                    +" - release: "+ f.getBookReleaseYear()+System.lineSeparator()
                    +" - volume: "+ f.getPageVolume()+System.lineSeparator()
                    +" - genre: "+ f.getGenre().getName()+System.lineSeparator()
                    +" - author: "+ f.getAuthor().getName() + " " +f.getAuthor().getSurname()+System.lineSeparator()

            ));
        }
        System.out.println("Done");
    }

    @ShellMethod(key = {"ub"}, value = "Update book")
    public void updateBook(@ShellOption(value = "-b_id") Integer id,
                           @ShellOption(value = "-b_name", defaultValue = "unknownBookName") String bookName,
                           @ShellOption(value = "-b_volume", defaultValue = "0") Integer pageVolume,
                           @ShellOption(value = "-b_year", defaultValue = "0") Integer bookReleaseYear) {
        BookDto bookDto = mappers.getBookDto(Long.valueOf(id), bookName, pageVolume, bookReleaseYear);
        bookLibrary.updateBook(bookDto);
        System.out.println("Done");
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

        long id = bookLibrary.insertBook(authorDto, genreDto, bookDto, descriptionDto);
        System.out.println("Add new book assigned id - "+id+System.lineSeparator()+"Done");
    }

    @ShellMethod(key = {"db"}, value = "Delete book by id")
    public void deleteBook(@ShellOption(value = "-b_id") Integer id) {
        bookLibrary.deleteBook(id);
        System.out.println("Done");
    }
}

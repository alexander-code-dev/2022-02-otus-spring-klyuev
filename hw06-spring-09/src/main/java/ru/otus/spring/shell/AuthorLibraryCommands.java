package ru.otus.spring.shell;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.spring.dao.entity.Author;
import ru.otus.spring.sevice.AuthorLibrary;

import java.util.List;

@ShellComponent
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthorLibraryCommands {

    AuthorLibrary authorLibrary;

    @ShellMethod(key = {"sa"}, value = "Get a list of authors or get one concrete author by id")
    @Transactional(readOnly = true)
    public void printAuthor(@ShellOption(value = "-a_id", defaultValue = "all") String id) {
        List<Author> authors = authorLibrary.selectAuthor(id);
        authors.forEach(author-> System.out.println(System.lineSeparator()
                +"№ "+author.getId()+System.lineSeparator()
                +" - name: "+ author.getName()+System.lineSeparator()
                +" - surname: "+ author.getSurname()+System.lineSeparator()
                +" - amount of books: "+ author.getBooks().size()+System.lineSeparator()
        ));
        System.out.println("Done");
    }
}

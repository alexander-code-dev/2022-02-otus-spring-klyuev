package ru.otus.spring.shell;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.otus.spring.sevice.AuthorLibrary;

@ShellComponent
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthorLibraryCommands {

    AuthorLibrary authorLibrary;

    @ShellMethod(key = {"sa"}, value = "Get a list of authors or get one concrete author by id")
    public void selectAuthor(@ShellOption(value = "-a_id", defaultValue = "all") String id) {
        authorLibrary.selectAuthor(id);
    }
}

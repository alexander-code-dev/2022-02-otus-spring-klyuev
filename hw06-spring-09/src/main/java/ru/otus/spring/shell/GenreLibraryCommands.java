package ru.otus.spring.shell;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.otus.spring.sevice.GenreLibrary;

@ShellComponent
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class GenreLibraryCommands {

    GenreLibrary genreLibrary;

    @ShellMethod(key = {"sg"}, value = "Get a list of genres or get one concrete genre by id")
    public void selectGenre(@ShellOption(value = "-g_id", defaultValue = "all") String id) {
        genreLibrary.selectGenre(id);
    }
}

package ru.otus.spring.shell;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.spring.dto.GenreDto;
import ru.otus.spring.sevice.GenreLibrary;

import java.util.List;

@ShellComponent
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class GenreLibraryCommands {

    GenreLibrary genreLibrary;

    @ShellMethod(key = {"sg"}, value = "Get a list of genres or get one concrete genre by id")
    @Transactional(readOnly = true)
    public void printGenre(@ShellOption(value = "-g_id", defaultValue = "all") String id) {
        List<GenreDto> genres = genreLibrary.selectGenre(id);
        if (genres.isEmpty()) {
            System.out.println("No genres found");
        }
        genres.forEach(genre-> System.out.println(System.lineSeparator()
                +"№ "+genre.getId()+System.lineSeparator()
                +" - name: "+ genre.getName()+System.lineSeparator()
                +" - amount of books: "+ genre.getBookDtoList().size()+System.lineSeparator()
        ));
        System.out.println("Done");
    }
}

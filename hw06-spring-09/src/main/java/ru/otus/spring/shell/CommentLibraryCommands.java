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
import ru.otus.spring.dto.CommentDto;
import ru.otus.spring.mappers.Mappers;
import ru.otus.spring.sevice.CommentLibrary;

@ShellComponent
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CommentLibraryCommands {

    CommentLibrary commentLibrary;
    Mappers mappers;

    @ShellMethod(key = {"ic"}, value = "Insert book commentary")
    @Transactional
    public void addComment(@ShellOption(value = "-book_id") Long id,
                           @ShellOption(value = "-comment") String comment) {
        CommentDto commentDto = mappers.getCommentDto(id, comment);
        commentLibrary.insertComment(commentDto);
    }

    @ShellMethod(key = {"dc"}, value = "Delete comment by id")
    public void deleteComment(@ShellOption(value = "-comment_id") Integer id) {
        commentLibrary.deleteComment(id);
        System.out.println("Done");
    }
}

package ru.otus.spring.shell;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.spring.dto.CommentDto;
import ru.otus.spring.mappers.Mappers;
import ru.otus.spring.sevice.CommentLibrary;

import java.util.List;

@ShellComponent
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CommentLibraryCommands {

    CommentLibrary commentLibrary;
    Mappers mappers;

    @ShellMethod(key = {"sc"}, value = "select book commentary")
    public void printCommentsByIdBook(@ShellOption(value = "-book_id") Long id) {
        List<CommentDto> commentDtoList = commentLibrary.getAllCommentsByBookId(id);
        if (commentDtoList.size() != 0) {
            System.out.println("Book name: "+commentDtoList.get(0).getBookDto().getName());
            commentDtoList.forEach(f -> {
                System.out.println("id: "+f.getId()+" - comment: "+f.getComment());
            });
        } else {
            System.out.println("Book comment not found");
        }

        System.out.println("Done");
    }

    @ShellMethod(key = {"ic"}, value = "Insert book commentary")
    public void addComment(@ShellOption(value = "-book_id") Long id,
                           @ShellOption(value = "-comment") String comment) {
        CommentDto commentDto = mappers.getCommentDto(id, comment);
        commentLibrary.insertComment(commentDto);
        System.out.println("Done");
    }

    @ShellMethod(key = {"dc"}, value = "Delete comment by id")
    public void deleteComment(@ShellOption(value = "-comment_id") Integer id) {
        commentLibrary.deleteComment(id);
        System.out.println("Done");
    }
}

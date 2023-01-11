package ru.otus.spring.dao.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import ru.otus.spring.dao.anotation.CascadeSave;
import ru.otus.spring.dao.anotation.DbSeqName;

import java.util.List;
import java.util.Objects;

@Document(collection = "books")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Book extends AbstractDocument {

    @Transient
    @DbSeqName
    public static final String BOOK_SEQ = "book_seq";

    String name;

    @DBRef
    @CascadeSave
    Description description;

    Integer pageVolume;
    Integer bookReleaseYear;

    @DBRef
    @CascadeSave
    Author author;

    @DBRef
    @CascadeSave
    Genre genre;

    @DBRef
    @CascadeSave
    List<Comment> comments;

    @PersistenceConstructor
    public Book(String name, Integer pageVolume, Integer bookReleaseYear) {
        this.name = name;
        this.pageVolume = pageVolume;
        this.bookReleaseYear = bookReleaseYear;
    }

    public Book(String name, Description description, Integer pageVolume, Integer bookReleaseYear, Author author, Genre genre) {
        this.name = name;
        this.description = description;
        this.pageVolume = pageVolume;
        this.bookReleaseYear = bookReleaseYear;
        this.author = author;
        this.genre = genre;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return Objects.equals(id, book.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", pageVolume=" + pageVolume +
                ", bookReleaseYear=" + bookReleaseYear +
                '}';
    }
}

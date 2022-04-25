package ru.otus.spring.dao.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.util.Objects;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "BOOK")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "BOOK_SEQ")
    @SequenceGenerator(name = "BOOK_SEQ", sequenceName = "BOOK_ID_SEQ", allocationSize = 1)
    Long id;
    String name;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "description_id")
    Description description;

    @Column(name = "PAGE_VOLUME")
    int pageVolume;
    @Column(name = "BOOK_RELEASE_YEAR")
    int bookReleaseYear;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "AUTHOR_ID")
    Author author;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "GENRE_ID")
    Genre genre;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return pageVolume == book.pageVolume && bookReleaseYear == book.bookReleaseYear && Objects.equals(id, book.id) && Objects.equals(name, book.name) && Objects.equals(description, book.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, pageVolume, bookReleaseYear);
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

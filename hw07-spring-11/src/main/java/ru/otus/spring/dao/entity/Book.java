package ru.otus.spring.dao.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.util.List;
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

    @ManyToOne
    @JoinColumn(name = "AUTHOR_ID")
    Author author;

    @ManyToOne
    @JoinColumn(name = "GENRE_ID")
    Genre genre;

    @OneToMany(mappedBy = "book", orphanRemoval = true, cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REMOVE})
    List<Comment> comments;

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

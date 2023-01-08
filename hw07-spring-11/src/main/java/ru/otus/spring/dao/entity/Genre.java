package ru.otus.spring.dao.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.BatchSize;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "GENRE")
public class Genre {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "GENRE_SEQ")
    @SequenceGenerator(name = "GENRE_SEQ", sequenceName = "GENRE_ID_SEQ", allocationSize = 1)
    Long id;
    String name;

    @OneToMany(
            mappedBy = "genre"
            , cascade = {CascadeType.MERGE, CascadeType.PERSIST}
    )
    @BatchSize(size = 10)
    List<Book> books;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Genre genre = (Genre) o;
        return Objects.equals(id, genre.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Genre{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}

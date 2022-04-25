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
@Table(name = "AUTHOR")
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "AUTHOR_SEQ")
    @SequenceGenerator(name = "AUTHOR_SEQ", sequenceName = "AUTHOR_ID_SEQ", allocationSize = 1)
    Long id;
    String name;
    String surname;

    @OneToMany(
            mappedBy = "author"
            , cascade = {CascadeType.MERGE, CascadeType.PERSIST}
            , fetch = FetchType.LAZY
    )
    @BatchSize(size = 10)
    List<Book> books;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Author author = (Author) o;
        return Objects.equals(id, author.id) && Objects.equals(name, author.name) && Objects.equals(surname, author.surname);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, surname);
    }

    @Override
    public String toString() {
        return "Author{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                '}';
    }
}

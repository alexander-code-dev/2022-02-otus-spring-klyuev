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
@Table(name = "DESCRIPTION")
public class Description {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "DESCRIPTION_SEQ")
    @SequenceGenerator(name = "DESCRIPTION_SEQ", sequenceName = "DESCRIPTION_ID_SEQ", allocationSize = 1)
    Long id;
    String description;

    @OneToOne(
            mappedBy = "description"
            , cascade = {CascadeType.MERGE, CascadeType.PERSIST}
            , fetch = FetchType.LAZY
    )
    private Book book;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Description that = (Description) o;
        return Objects.equals(id, that.id) && Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, description);
    }

    @Override
    public String toString() {
        return "Description{" +
                "id=" + id +
                ", description='" + description + '\'' +
                '}';
    }
}

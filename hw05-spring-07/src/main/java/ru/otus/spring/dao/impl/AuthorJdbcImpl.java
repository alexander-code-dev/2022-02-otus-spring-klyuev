package ru.otus.spring.dao.impl;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import ru.otus.spring.dao.AuthorJdbc;
import ru.otus.spring.domain.Author;
import ru.otus.spring.domain.Book;
import ru.otus.spring.domain.Genre;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@Repository
public class AuthorJdbcImpl implements AuthorJdbc {

    NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public long insert(Author author) {
        String sql;
        Map<String, Object> params;
        MapSqlParameterSource mapParams = new MapSqlParameterSource();
        KeyHolder keyHolder = new GeneratedKeyHolder();
        if (author.getId() != null) {
            params = new HashMap<>(Map.of(
                    "id", author.getId(),
                    "name", author.getName(),
                    "surname", author.getSurname()));
            mapParams.addValues(params);
            sql = "insert into author (id, name, surname) values (:id, :name, :surname)";
        } else {
            params = new HashMap<>(Map.of(
                    "name", author.getName(),
                    "surname", author.getSurname()));
            mapParams.addValues(params);
            sql = "insert into author (name, surname) values (:name, :surname)";
        }
        namedParameterJdbcTemplate.update(sql, mapParams, keyHolder);
        return (long) Objects.requireNonNull(keyHolder.getKeys()).get("id");
    }

    public Author getById(long id) {
        Map<String, Object> params = Collections.singletonMap("id", id);
        List<Author> list = namedParameterJdbcTemplate
                .query("select a.id as author_id, a.name as author_name, a.surname, " +
                                "b.id as book_id, b.name as book_name, b.description, b.page_volume, b.book_release_year " +
                                //"g.id as genre_id, g.name as genre_name " +
                                "from author a " +
                                "left join book b on a.id = b.author_id " +
                                //"left join genre g on b.genre_id = g.id" +
                                "   where 1=1 " +
                                "       and a.id = :id " +
                                " ORDER BY a.id",
                        params, new AuthorLeftJoinBook());
        return list.size() != 0 ? list.get(0) : null;
    }

    @Override
    public List<Author> getAll() {
        return namedParameterJdbcTemplate
                .query("select a.id as author_id, a.name as author_name, a.surname, " +
                        "b.id as book_id, b.name as book_name, b.description, b.page_volume, b.book_release_year " +
                        //"g.id as genre_id, g.name as genre_name " +
                        "from author a " +
                        "left join book b on a.id = b.author_id " +
                        //"left join genre g on b.genre_id = g.id " +
                                "ORDER BY a.id",
                        new AuthorLeftJoinBook());
    }

    @Override
    public void update(Author author) {
        Map<String, Object> params = new HashMap<>(Map.of(
                "id", author.getId(),
                "name", author.getName(),
                "surname", author.getSurname()));
        namedParameterJdbcTemplate.update("update author set name = :name, surname = :surname where id = :id",
                params);
    }

    @Override
    public void deleteById(long id) {
        Map<String, Object> params = Collections.singletonMap("id", id);
        namedParameterJdbcTemplate.update("delete from author where id = :id", params);
    }

    private static class AuthorLeftJoinBook implements ResultSetExtractor<List<Author>> {
        @Override
        public List<Author> extractData(ResultSet rs) throws SQLException, DataAccessException {
            Map<Long, Author> authors = new HashMap<>();
            while (rs.next()) {

                // Author
                long authorId = rs.getInt("author_id");
                String authorName = rs.getString("author_name");
                String surname = rs.getString("surname");

                // Book
                long bookId = rs.getLong("book_id");
                String bookName = rs.getString("book_name");
                String description = rs.getString("description");
                int pageVolume = rs.getInt("page_volume");
                int bookReleaseYear = rs.getInt("book_release_year");

                // Genre
                //long genreId = rs.getLong("genre_id");
                //String genreName = rs.getString("genre_name");

                Book book = new Book(bookId, bookName, description, pageVolume, bookReleaseYear,
                        new Author(authorId, authorName, surname, new ArrayList<>()),
                        new Genre());

                // add element
                if (authors.keySet().stream().noneMatch(f -> f.equals(authorId))) {
                    authors.put(authorId, new Author(authorId, authorName, surname, List.of(book)));
                } else {
                    Author author = authors.get(authorId);
                    List<Book> bookList = new ArrayList<>(author.getBooks());
                    bookList.add(book);
                    author.setBooks(bookList);
                    authors.put(authorId, author);
                }
            }
            return new ArrayList<>(authors.values());
        }
    }
}

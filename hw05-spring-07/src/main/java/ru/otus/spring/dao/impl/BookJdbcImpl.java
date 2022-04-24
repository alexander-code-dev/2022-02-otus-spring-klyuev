package ru.otus.spring.dao.impl;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import ru.otus.spring.dao.AuthorJdbc;
import ru.otus.spring.dao.BookJdbc;
import ru.otus.spring.dao.GenreJdbc;
import ru.otus.spring.domain.Author;
import ru.otus.spring.domain.Book;
import ru.otus.spring.domain.Genre;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@Repository
public class BookJdbcImpl implements BookJdbc {
    NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    AuthorJdbc authorJdbc;
    GenreJdbc genreJdbc;

    @Override
    public long insert(Book book) {
        String sql;
        Map<String, Object> params;
        MapSqlParameterSource mapParams = new MapSqlParameterSource();
        KeyHolder keyHolder = new GeneratedKeyHolder();
        if (book.getId() != null) {
            params = new HashMap<>(Map.of(
                    "id", book.getId(),
                    "name", book.getName(),
                    "description", book.getDescription(),
                    "page_volume", book.getPageVolume(),
                    "book_release_year", book.getBookReleaseYear()));
            sql = "INSERT INTO book " +
                    "(id, name, description, page_volume, book_release_year, author_id, genre_id) " +
                    "values " +
                    "(:id, :name, :description, :page_volume, :book_release_year, :author_id, :genre_id)";
        } else {
            params = new HashMap<>(Map.of(
                    "name", book.getName(),
                    "description", book.getDescription(),
                    "page_volume", book.getPageVolume(),
                    "book_release_year", book.getBookReleaseYear()));
            sql = "INSERT INTO book " +
                    "(name, description, page_volume, book_release_year, author_id, genre_id) " +
                    "values " +
                    "(:name, :description, :page_volume, :book_release_year, :author_id, :genre_id)";
        }
        // Author
        long authorId = authorJdbc.insert(book.getAuthor());
        params.put("author_id", authorId);
        // Genre
        long genreId = genreJdbc.insert(book.getGenre());
        params.put("genre_id", genreId);
        mapParams.addValues(params);
        namedParameterJdbcTemplate.update(sql, mapParams, keyHolder);
        return (long) Objects.requireNonNull(keyHolder.getKeys()).get("id");
    }

    @Override
    public Book getById(long id) {
        Map<String, Object> params = Collections.singletonMap("id", id);
        return namedParameterJdbcTemplate
                .queryForObject("select b.id, b.name, b.description, b.page_volume, b.book_release_year, " +
                                "a.id as author_id, a.name as author_name, a.surname, " +
                                "g.id as genre_id, g.name as genre_name  " +
                                "from book b " +
                                "join author a on b.author_id = a.id " +
                                "join genre g on b.genre_id = g.id " +
                                "   where 1=1 " +
                                "       and b.id = :id " +
                                " ORDER BY b.id",
                params, new BookJdbcImpl.BookMapper());
    }

    @Override
    public List<Book> getAll() {
        return namedParameterJdbcTemplate
                .query("select b.id, b.name, b.description, b.page_volume, b.book_release_year, " +
                                "a.id as author_id, a.name as author_name, a.surname, " +
                                "g.id as genre_id, g.name as genre_name  " +
                                "from book b " +
                                "join author a on b.author_id = a.id " +
                                "join genre g on b.genre_id = g.id " +
                                "ORDER BY b.id ",
                new BookJdbcImpl.BookMapper());
    }

    @Override
    public void update(Book book) {
        Map<String, Object> params = new HashMap<>();
        params.put("id", book.getId());
        params.put("name", book.getName());
        params.put("description", book.getDescription());
        params.put("page_volume", book.getPageVolume());
        params.put("book_release_year", book.getBookReleaseYear());
        namedParameterJdbcTemplate
                .update("update book " +
                                "set " +
                                "   name = :name, " +
                                "   description = :description, " +
                                "   page_volume = :page_volume, " +
                                "   book_release_year = :book_release_year " +
                        "where id = :id",
                params);
    }

    @Override
    public void deleteById(long id) {
        Map<String, Object> params = Collections.singletonMap("id", id);
        namedParameterJdbcTemplate.update("delete from book where id = :id", params);
    }

    private static class BookMapper implements RowMapper<Book> {
        @Override
        public Book mapRow(ResultSet resultSet, int i) throws SQLException {
            // Book
            long id = resultSet.getLong("id");
            String name = resultSet.getString("name");
            String description = resultSet.getString("description");
            int page_volume = resultSet.getInt("page_volume");
            int book_release_year = resultSet.getInt("book_release_year");
            // Author
            long author_id = resultSet.getLong("author_id");
            String author_name = resultSet.getString("author_name");
            String surname = resultSet.getString("surname");
            Author author = new Author(author_id, author_name, surname, new ArrayList<>());
            // Genre
            long genre_id = resultSet.getLong("genre_id");
            String genre_name = resultSet.getString("genre_name");
            Genre genre = new Genre(genre_id, genre_name, new ArrayList<>());

            return new Book(id, name, description, page_volume, book_release_year, author, genre);
        }
    }
}

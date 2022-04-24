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
public class GenreJdbcImpl implements GenreJdbc {
    NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public long insert(Genre genre) {
        String sql;
        Map<String, Object> params;
        MapSqlParameterSource mapParams = new MapSqlParameterSource();
        KeyHolder keyHolder = new GeneratedKeyHolder();
        if (genre.getId() != null) {
            params = new HashMap<>(Map.of(
                    "id", genre.getId(),
                    "name", genre.getName()));
            mapParams.addValues(params);
            sql = "insert into genre (id, name) values (:id, :name)";
        } else {
            params = new HashMap<>(Map.of(
                    "name", genre.getName()));
            mapParams.addValues(params);
            sql = "insert into genre (name) values (:name)";
        }
        namedParameterJdbcTemplate.update(sql, mapParams, keyHolder);
        return (long) Objects.requireNonNull(keyHolder.getKeys()).get("id");
    }

    @Override
    public Genre getById(long id) {
        Map<String, Object> params = Collections.singletonMap("id", id);
        List<Genre> list = namedParameterJdbcTemplate
                .query("select g.id as genre_id, g.name as genre_name, " +
                        "b.id as book_id, b.name as book_name, b.description, b.page_volume, b.book_release_year " +
                        //"a.id as author_id, a.name as author_name, a.surname " +
                        "from genre g " +
                        "left join book b on g.id = b.genre_id " +
                        //"left join author a on b.author_id = a.id" +
                        "   where 1=1 " +
                        "       and g.id = :id " +
                                "ORDER BY g.id ",
                params, new GenreLeftJoinBook());
        return list.size() != 0 ? list.get(0) : null;
    }

    @Override
    public List<Genre> getAll() {
        return namedParameterJdbcTemplate.query("select g.id as genre_id, g.name as genre_name, " +
                        "b.id as book_id, b.name as book_name, b.description, b.page_volume, b.book_release_year " +
                        //"a.id as author_id, a.name as author_name, a.surname " +
                        "from genre g " +
                        "left join book b on g.id = b.genre_id " +
                        //"left join author a on b.author_id = a.id " +
                        "ORDER BY g.id "
                ,
                new GenreLeftJoinBook());
    }

    @Override
    public void update(Genre genre) {
        Map<String, Object> params = new HashMap<>(Map.of(
                "id", genre.getId(),
                "name", genre.getName()));
        namedParameterJdbcTemplate.update("update genre set name = :name where id = :id", params);
    }

    @Override
    public void deleteById(long id) {
        Map<String, Object> params = Collections.singletonMap("id", id);
        namedParameterJdbcTemplate.update("delete from genre where id = :id", params);
    }

    private static class GenreLeftJoinBook implements ResultSetExtractor<List<Genre>> {
        @Override
        public List<Genre> extractData(ResultSet rs) throws SQLException, DataAccessException {
            Map<Long, Genre> genres = new HashMap<>();
            while (rs.next()) {

                // Genre
                long genreId = rs.getLong("genre_id");
                String genreName = rs.getString("genre_name");

                // Book
                long bookId = rs.getLong("book_id");
                String bookName = rs.getString("book_name");
                String description = rs.getString("description");
                int pageVolume = rs.getInt("page_volume");
                int bookReleaseYear = rs.getInt("book_release_year");

                // Author
                //long authorId = rs.getInt("author_id");
                //String authorName = rs.getString("author_name");
                //String surname = rs.getString("surname");

                Book book = new Book(bookId, bookName, description, pageVolume, bookReleaseYear,
                        new Author(),
                        new Genre(genreId, genreName, new ArrayList<>()));

                // add element
                if (genres.keySet().stream().noneMatch(f -> f.equals(genreId))) {
                    genres.put(genreId, new Genre(genreId, genreName, List.of(book)));
                } else {
                    Genre genre = genres.get(genreId);
                    List<Book> bookList = new ArrayList<>(genre.getBooks());
                    bookList.add(book);
                    genre.setBooks(bookList);
                    genres.put(genreId, genre);
                }
            }
            return new ArrayList<>(genres.values());
        }
    }
}

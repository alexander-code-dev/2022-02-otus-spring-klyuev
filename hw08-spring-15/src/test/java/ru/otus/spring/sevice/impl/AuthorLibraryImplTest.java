package ru.otus.spring.sevice.impl;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.context.annotation.Import;
import org.springframework.data.mongodb.core.MongoOperations;
import ru.otus.spring.dao.entity.Author;
import ru.otus.spring.dao.entity.Book;
import ru.otus.spring.dao.sevice.CascadingMongoEventListener;
import ru.otus.spring.dao.sevice.SequenceGeneratorService;
import ru.otus.spring.dto.AuthorDto;
import ru.otus.spring.mappers.impl.*;
import ru.otus.spring.sevice.AuthorLibrary;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataMongoTest
@Import({AuthorLibraryImpl.class,
        SequenceGeneratorService.class,
        CascadingMongoEventListener.class,
        MappersImpl.class,
        GenreMapperImpl.class,
        DescriptionMapperImpl.class,
        BookMapperImpl.class,
        AuthorMapperImpl.class,
        CommentMapperImpl.class
})
@FieldDefaults(level = AccessLevel.PRIVATE)
class AuthorLibraryImplTest {

    @Autowired
    AuthorLibrary authorLibrary;

    @Autowired
    MongoOperations mongoOperations;

    @Test
    void selectAuthor() {
        Author stivenKing = new Author("Стивен","Кинг");
        mongoOperations.save(stivenKing);

        Book sebastjanFitcekBook = new Book("11/22/63", 928, 2021);
        Author sebastjanFitcek = new Author("Себастьян","Фитцек");

        sebastjanFitcekBook.setAuthor(sebastjanFitcek);
        sebastjanFitcekBook = mongoOperations.save(sebastjanFitcekBook);

        List<AuthorDto> authors = authorLibrary.selectAuthor("all");
        assertEquals(1, authors.size());
        assertNotNull(authors.get(0).getBookDtoList());

        List<Book> readBookFromDb = mongoOperations.findAll(Book.class);
        assertEquals(sebastjanFitcekBook, readBookFromDb.get(0));
    }
}
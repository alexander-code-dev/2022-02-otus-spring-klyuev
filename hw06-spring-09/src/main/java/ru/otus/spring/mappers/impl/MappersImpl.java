package ru.otus.spring.mappers.impl;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;
import ru.otus.spring.dto.AuthorDto;
import ru.otus.spring.dto.BookDto;
import ru.otus.spring.dto.DescriptionDto;
import ru.otus.spring.dto.GenreDto;
import ru.otus.spring.mappers.*;

@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class MappersImpl implements Mappers {

    MGenre mGenre;
    MDescription mDescription;
    MBook mBook;
    MAuthor mAuthor;

    public GenreDto getGenreDto(String genreName) {
        return mGenre.convertToDto(genreName);
    }
    public DescriptionDto getDescriptionDto(String description) {
        return mDescription.convertToDto(description);
    }
    public BookDto getBookDto(Long id, String bookName, Integer pageVolume, Integer bookReleaseYear) {
        return mBook.convertToDto(id, bookName, pageVolume, bookReleaseYear);
    }
    public BookDto getBookDto(String bookName, Integer pageVolume, Integer bookReleaseYear) {
        return mBook.convertToDto(bookName, pageVolume, bookReleaseYear);
    }
    public AuthorDto getAuthorDto(String authorName, String authorSurname) {
        return mAuthor.convertToDto(authorName, authorSurname);
    }
}

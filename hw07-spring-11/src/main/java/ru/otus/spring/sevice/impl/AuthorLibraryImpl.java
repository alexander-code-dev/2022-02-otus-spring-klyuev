package ru.otus.spring.sevice.impl;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import ru.otus.spring.dao.entity.Author;
import ru.otus.spring.dao.repository.AuthorRepository;
import ru.otus.spring.sevice.AuthorLibrary;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthorLibraryImpl implements AuthorLibrary {

    AuthorRepository authorRepository;

    @Override
    public List<Author> selectAuthor(String id) {
        List<Author> authors = new ArrayList<>();
        if (id.equals("all")) {
            authors.addAll(authorRepository.findAll());
        } else {
            long authorId = Long.parseLong(id);
            Optional<Author> author = authorRepository.findById(authorId);
            author.ifPresent(authors::add);
        }
        return authors;
    }
}

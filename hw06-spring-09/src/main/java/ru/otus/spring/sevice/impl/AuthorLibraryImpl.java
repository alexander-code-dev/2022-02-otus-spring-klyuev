package ru.otus.spring.sevice.impl;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import ru.otus.spring.dao.entity.Author;
import ru.otus.spring.dao.repository.CRUD;
import ru.otus.spring.sevice.AuthorLibrary;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthorLibraryImpl implements AuthorLibrary {

    CRUD<Author> repository;

    @Override
    public List<Author> selectAuthor(String id) {
        List<Author> authors = new ArrayList<>();
        if (id.equals("all")) {
            authors.addAll(repository.findAll());
        } else {
            long authorId = Long.parseLong(id);
            if (repository.findById(authorId).isPresent()) {
                authors.add(repository.findById(authorId).get());
            }
        }
        return authors;
    }
}

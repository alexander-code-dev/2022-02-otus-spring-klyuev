package ru.otus.spring.sevice.impl;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.spring.dao.entity.Genre;
import ru.otus.spring.dao.repository.CRUD;
import ru.otus.spring.sevice.GenreLibrary;
import ru.otus.spring.sevice.OGenre;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class GenreLibraryImpl implements GenreLibrary {

    CRUD<Genre> genreRepo;
    OGenre oGenre;

    @Override
    @Transactional(readOnly = true)
    public void selectGenre(String id) {
        List<Genre> genres = new ArrayList<>();
        if (id.equals("all")) {
            genres.addAll(genreRepo.findAll());
        } else {
            long genreId = Long.parseLong(id);
            if (genreRepo.findById(genreId).isPresent()) {
                genres.add(genreRepo.findById(genreId).get());
            }
        }
        oGenre.print(genres);
    }
}

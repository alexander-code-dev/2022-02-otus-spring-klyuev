package ru.otus.spring.sevice.impl;

import org.springframework.stereotype.Component;
import ru.otus.spring.dao.entity.Genre;
import ru.otus.spring.sevice.OGenre;

import java.util.List;

@Component
public class OGenreImpl implements OGenre {
    @Override
    public void print(List<Genre> genres) {
        genres.forEach(genre-> System.out.println(System.lineSeparator()
                +"№ "+genre.getId()+System.lineSeparator()
                +" - name: "+ genre.getName()+System.lineSeparator()
                +" - amount of books: "+ genre.getBooks().size()+System.lineSeparator()
        ));
        System.out.println("Done");
    }
}

package ru.otus.spring.sevice.impl;

import org.springframework.stereotype.Component;
import ru.otus.spring.dao.entity.Author;
import ru.otus.spring.sevice.OAuthor;

import java.util.List;

@Component
public class OAuthorImpl implements OAuthor {
    @Override
    public void print(List<Author> authors) {
        authors.forEach(author-> System.out.println(System.lineSeparator()
                +"№ "+author.getId()+System.lineSeparator()
                +" - name: "+ author.getName()+System.lineSeparator()
                +" - surname: "+ author.getSurname()+System.lineSeparator()
                +" - amount of books: "+ author.getBooks().size()+System.lineSeparator()
        ));
        System.out.println("Done");
    }
}

package ru.otus.spring.sevice.impl;

import org.springframework.stereotype.Component;
import ru.otus.spring.dao.entity.Book;
import ru.otus.spring.sevice.OBook;

import java.util.List;

@Component
public class OBookImpl implements OBook {
    @Override
    public void print(List<Book> books) {
        books.forEach(f-> System.out.println(System.lineSeparator() + "№ "+f.getId()+System.lineSeparator()
                +" - name: "+ f.getName()+System.lineSeparator()
                +" - description: "+f.getDescription().getDescription()+System.lineSeparator()
                +" - release: "+ f.getBookReleaseYear()+System.lineSeparator()
                +" - volume: "+ f.getPageVolume()+System.lineSeparator()
                +" - genre: "+ f.getGenre().getName()+System.lineSeparator()
                +" - author: "+ f.getAuthor().getName() + " " +f.getAuthor().getSurname()+System.lineSeparator()

        ));
        System.out.println("Done");
    }
    @Override
    public void print(String str) {
        System.out.println(str);
    }
}

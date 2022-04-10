package ru.otus.spring.service.Impl;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;
import ru.otus.spring.service.IOService;

import java.util.Scanner;

@Component
@FieldDefaults(level = AccessLevel.PRIVATE)
public class IOStringServiceImpl implements IOService<Scanner> {

    Scanner scanner;

    @Override
    public void setReader(Scanner reader) {
        this.scanner = reader;
    }
    @Override
    public void closeReader() {
        this.scanner.close();
    }
    @Override
    public String read() {
        return scanner.nextLine();
    }
    @Override
    public Integer readInt() {
        return scanner.nextInt();
    }
    @Override
    public void output(String message) {
        System.out.println(message);
    }
}

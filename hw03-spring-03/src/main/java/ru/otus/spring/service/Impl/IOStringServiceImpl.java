package ru.otus.spring.service.Impl;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import ru.otus.spring.service.IOService;

import java.util.Scanner;

@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class IOStringServiceImpl implements IOService {

    Scanner scanner;

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

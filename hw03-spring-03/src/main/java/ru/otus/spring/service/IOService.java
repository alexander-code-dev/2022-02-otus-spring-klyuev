package ru.otus.spring.service;

public interface IOService<T> {
    void setReader(T reader);
    void closeReader();
    String read();
    Integer readInt();
    void output(String message);
}

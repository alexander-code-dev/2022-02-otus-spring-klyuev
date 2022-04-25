package ru.otus.spring.sevice;

public interface Library {
    void selectGenre(String id);
    void selectAuthor(String id);
    void selectBook(String id);
    void updateBook(Integer id, String bookName, Integer pageVolume, Integer bookReleaseYear);
    void insertBook(String authorName, String authorSurname, String genreName, String bookName, String description,
                    Integer pageVolume, Integer bookReleaseYear);
    void deleteBook(Integer id);
}

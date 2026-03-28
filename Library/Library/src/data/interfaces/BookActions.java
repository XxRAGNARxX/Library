package data.interfaces;


import models.Book;

import java.util.List;

public interface BookActions {
    void addBook(Book book);
    void removeBook(String isbn);
    Book getBookByIsbn(String isbn);
    List<Book> getAllBooks();

    // For the 'books find' command
    List<Book> findBooks(String option, String searchString);

    // For the 'books sort' command
    void sortBooks(String option, boolean ascending);
}
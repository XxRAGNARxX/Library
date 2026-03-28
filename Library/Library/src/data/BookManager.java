package data;

import data.interfaces.BookActions;
import exceptions.BookException;
import models.Book;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class BookManager implements BookActions, Serializable {
    private List<Book> books;

    public BookManager() {
        this.books = new ArrayList<>();
    }

    @Override
    public void addBook(Book book) {
        for (Book b : books) {
            if (b.equals(book)) {
                throw new BookException("Book with this ISBN already exists!");
            }
        }
        books.add(book);
    }

    @Override
    public void removeBook(String isbn) {
        Book book = getBookByIsbn(isbn);
        books.remove(book);
    }

    @Override
    public Book getBookByIsbn(String isbn) {
        for (Book book : books) {
            if (book.getIsbn().equals(isbn)) {
                return book;
            }
        }
        throw new BookException("Book not found!");
    }

    @Override
    public List<Book> getAllBooks() {
        return books;
    }

    @Override
    public List<Book> findBooks(String option, String searchString) {
        List<Book> result = new ArrayList<>();
        String search = searchString.toLowerCase(); // Case-insensitive requirement

        for (Book book : books) {
            switch (option.toLowerCase()) {
                case "title":
                    if (book.getTitle().toLowerCase().contains(search)) result.add(book);
                    break;
                case "author":
                    if (book.getAuthor().toLowerCase().contains(search)) result.add(book);
                    break;
                case "tag":
                    for (String tag : book.getTags()) {
                        if (tag.toLowerCase().contains(search)) {
                            result.add(book);
                            break;
                        }
                    }
                    break;
                default:
                    throw new BookException("Invalid search option! Use: title, author, tag");
            }
        }
        return result;
    }

    @Override
    public void sortBooks(String option, boolean ascending) {
        mergeSort(this.books, option, ascending);
    }

    private void mergeSort(List<Book> list, String option, boolean asc) {
        if (list.size() <= 1) return;

        int mid = list.size() / 2;
        List<Book> left = new ArrayList<>(list.subList(0, mid));
        List<Book> right = new ArrayList<>(list.subList(mid, list.size()));

        mergeSort(left, option, asc);
        mergeSort(right, option, asc);

        merge(list, left, right, option, asc);
    }

    private void merge(List<Book> result, List<Book> left, List<Book> right, String option, boolean asc) {
        int i = 0, j = 0, k = 0;

        while (i < left.size() && j < right.size()) {
            if (compareBooks(left.get(i), right.get(j), option, asc)) {
                result.set(k++, left.get(i++));
            } else {
                result.set(k++, right.get(j++));
            }
        }

        while (i < left.size()) result.set(k++, left.get(i++));
        while (j < right.size()) result.set(k++, right.get(j++));
    }

    private boolean compareBooks(Book b1, Book b2, String option, boolean asc) {
        int cmp = 0;
        switch (option.toLowerCase()) {
            case "title":
                cmp = b1.getTitle().compareToIgnoreCase(b2.getTitle());
                break;
            case "author":
                cmp = b1.getAuthor().compareToIgnoreCase(b2.getAuthor());
                break;
            case "year":
                cmp = Integer.compare(b1.getYear(), b2.getYear());
                break;
            case "rating":
                cmp = Double.compare(b1.getRating(), b2.getRating());
                break;
            default:
                throw new BookException("Invalid sort option! Use: title, author, year, rating");
        }
        if (asc) {
            return cmp <= 0;
        } else {
            return cmp >= 0;
        }
    }
}
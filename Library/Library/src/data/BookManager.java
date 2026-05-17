package data;

import data.interfaces.BookActions;
import exceptions.BookException;
import models.Book;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Default in-memory implementation of {@link BookActions}.
 *
 * <p>Books are stored in an {@link ArrayList}. The list order reflects the order
 * in which books were added, unless a sort has been performed.
 *
 * <p>Sorting is implemented with a custom recursive merge sort rather than the
 * standard library sort, keeping the algorithm visible for academic review.
 *
 * <p>Instances are serialized as part of the library data file.
 */
public class BookManager implements BookActions, Serializable {
    private List<Book> books;
    /**
     * Constructs an empty book manager.
     */
    public BookManager() {
        this.books = new ArrayList<>();
    }

    /**
     * {@inheritDoc}
     *
     * @throws BookException if a book with the same ISBN is already in the catalogue
     */
    @Override
    public void addBook(Book book) {
        for (Book b : books) {
            if (b.equals(book)) {
                throw new BookException("Book with this ISBN already exists!");
            }
        }
        books.add(book);
    }

    /**
     * {@inheritDoc}
     *
     * @throws BookException if no book with {@code isbn} exists
     */
    @Override
    public void removeBook(String isbn) {
        Book book = getBookByIsbn(isbn);
        books.remove(book);
    }

    /**
     * {@inheritDoc}
     *
     * @throws BookException if no book with {@code isbn} exists
     */
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
    /**
     * {@inheritDoc}
     *
     * <p>Delegates to {@link #mergeSort} which sorts the list in-place.
     *
     * @throws BookException if {@code option} is not a supported sort key
     */
    @Override
    public void sortBooks(String option, boolean ascending) {
        mergeSort(this.books, option, ascending);
    }

    /**
     * Recursively splits and sorts {@code list} in-place using merge sort.
     *
     * @param list   the sublist to sort (modified in place)
     * @param option sort key
     * @param asc    {@code true} for ascending order
     */
    private void mergeSort(List<Book> list, String option, boolean asc) {
        if (list.size() <= 1) return;

        int mid = list.size() / 2;
        List<Book> left = new ArrayList<>(list.subList(0, mid));
        List<Book> right = new ArrayList<>(list.subList(mid, list.size()));

        mergeSort(left, option, asc);
        mergeSort(right, option, asc);

        merge(list, left, right, option, asc);
    }

    /**
     * Merges two sorted halves back into {@code result}.
     *
     * @param result the target list (same size as left + right)
     * @param left   sorted left half
     * @param right  sorted right half
     * @param option sort key
     * @param asc    {@code true} for ascending order
     */
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

    /**
     * Compares two books according to the given sort key and direction.
     *
     * @param b1     first book
     * @param b2     second book
     * @param option sort key ({@code "title"}, {@code "author"}, {@code "year"}, or {@code "rating"})
     * @param asc    {@code true} if ascending order is desired
     * @return {@code true} if {@code b1} should come before {@code b2}
     * @throws BookException if {@code option} is not a recognised sort key
     */

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
package data.interfaces;


import models.Book;

import java.util.List;

/**
 * Defines the book-management operations available in the library.
 *
 * <p>Implementations are responsible for storing, retrieving, searching,
 * and sorting the book catalogue.
 */
public interface BookActions {

    /**
     * Adds a book to the catalogue.
     *
     * @param book the book to add
     * @throws exceptions.BookException if a book with the same ISBN already exists
     */
    void addBook(Book book);

    /**
     * Removes the book identified by the given ISBN from the catalogue.
     *
     * @param isbn ISBN of the book to remove
     * @throws exceptions.BookException if no book with the given ISBN exists
     */
    void removeBook(String isbn);

    /**
     * Retrieves a book by its ISBN.
     *
     * @param isbn ISBN to look up
     * @return the matching {@link Book}
     * @throws exceptions.BookException if no book with the given ISBN exists
     */
    Book getBookByIsbn(String isbn);

    /**
     * Returns all books currently in the catalogue.
     *
     * @return mutable list of all books (may be empty)
     */
    List<Book> getAllBooks();
    /**
     * Searches the catalogue and returns books that match the query.
     *
     * <p>Supported options:
     * <ul>
     *   <li>{@code "title"}  – substring match on the book title (case-insensitive)</li>
     *   <li>{@code "author"} – substring match on the author name (case-insensitive)</li>
     *   <li>{@code "tag"}    – substring match against any of the book's tags</li>
     * </ul>
     *
     * @param option       one of {@code "title"}, {@code "author"}, or {@code "tag"}
     * @param searchString the substring to look for
     * @return list of matching books (may be empty)
     * @throws exceptions.BookException if {@code option} is not one of the supported values
     */
    // For the 'books find' command
    List<Book> findBooks(String option, String searchString);
    /**
     * Sorts the catalogue in-place using a stable merge sort.
     *
     * <p>Supported sort keys:
     * <ul>
     *   <li>{@code "title"}  – alphabetical by title (case-insensitive)</li>
     *   <li>{@code "author"} – alphabetical by author (case-insensitive)</li>
     *   <li>{@code "year"}   – chronological by publication year</li>
     *   <li>{@code "rating"} – by reader rating</li>
     * </ul>
     *
     * @param option    one of {@code "title"}, {@code "author"}, {@code "year"}, or {@code "rating"}
     * @param ascending {@code true} for ascending order, {@code false} for descending
     * @throws exceptions.BookException if {@code option} is not one of the supported values
     */
    // For the 'books sort' command
    void sortBooks(String option, boolean ascending);
}
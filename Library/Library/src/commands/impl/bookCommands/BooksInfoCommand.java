package commands.impl.bookCommands;

import commands.Command;
import data.interfaces.LibraryData;
import exceptions.BookException;
import models.Book;


/**
 * Implements the {@code books info} command.
 *
 * <p>Syntax: {@code books info <isbn>}
 *
 * <p>Prints the full details of the book identified by the given ISBN.
 * Requires the user to be logged in.
 */
public class BooksInfoCommand  implements Command {
    private final LibraryData libraryData;
    /**
     * Constructs the command with the shared library state.
     *
     * @param libraryData the in-memory library state
     */
    public BooksInfoCommand(LibraryData libraryData) {
        this.libraryData = libraryData;
    }

    /**
     * Executes the {@code books info} command.
     *
     * <p>Expects at least three tokens: {@code books}, {@code info}, and the ISBN.
     *
     * @param args token array from the user's input line
     * @return full book details string, or an error/usage message
     */
    @Override
    public String execute(String[] args) {
        if (libraryData.getLoggedInUser() == null) {
            return "Error: You must be logged in to view book info.";
        }
        if (args.length < 3) {
            return "Usage: books info <isbn>";
        }

        String isbn = args[2];
        try {
            Book book = libraryData.getBookActions().getBookByIsbn(isbn);
            return book.toString();
        } catch (BookException e) {
            return "Error: " + e.getMessage();
        }
    }

    @Override
    public String getHelpMessage() {
        return "books info <isbn> - outputs detailed information about a book.";
    }
}
package commands.impl.bookCommands;

import commands.Command;
import data.interfaces.LibraryData;
import models.Book;
import java.util.List;

/**
 * Implements the {@code books all} command.
 *
 * <p>Lists a one-line summary for every book currently in the catalogue.
 * Requires the user to be logged in.
 */
public class BooksAllCommand implements Command {
    private final LibraryData libraryData;

    /**
     * Constructs the command with the shared library state.
     *
     * @param libraryData the in-memory library state
     */
    public BooksAllCommand(LibraryData libraryData) {
        this.libraryData = libraryData;
    }

    /**
     * Executes the {@code books all} command.
     *
     * <p>Returns an error message if the user is not logged in. If the
     * catalogue is empty, a notice is returned instead of an empty string.
     *
     * @param args token array (not used beyond the command word)
     * @return short-info listing of all books, or an appropriate message
     */
    @Override
    public String execute(String[] args) {
        if (libraryData.getLoggedInUser() == null) {
            return "Error: You must be logged in to view books.";
        }

        List<Book> books = libraryData.getBookActions().getAllBooks();
        if (books.isEmpty()) {
            return "The library is currently empty.";
        }

        StringBuilder sb = new StringBuilder();
        for (Book book : books) {
            sb.append(book.shortInfo()).append("\n");
        }
        return sb.toString().trim();
    }

    @Override
    public String getHelpMessage() {
        return "books all - Displays a short info of all books in the library.";
    }
}
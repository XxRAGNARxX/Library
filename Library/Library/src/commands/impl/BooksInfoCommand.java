package commands.impl;

import commands.Command;
import data.interfaces.LibraryData;
import exceptions.BookException;
import models.Book;

public class BooksInfoCommand  implements Command {
    private final LibraryData libraryData;

    public BooksInfoCommand(LibraryData libraryData) {
        this.libraryData = libraryData;
    }

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
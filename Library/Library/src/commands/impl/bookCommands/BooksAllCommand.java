package commands.impl.bookCommands;

import commands.Command;
import data.interfaces.LibraryData;
import models.Book;
import java.util.List;

public class BooksAllCommand implements Command {
    private final LibraryData libraryData;

    public BooksAllCommand(LibraryData libraryData) {
        this.libraryData = libraryData;
    }

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
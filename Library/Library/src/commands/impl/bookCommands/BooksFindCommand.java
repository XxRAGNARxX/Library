package commands.impl.bookCommands;

import commands.Command;
import data.interfaces.LibraryData;
import exceptions.BookException;
import models.Book;

import java.util.Arrays;
import java.util.List;

/**
 * Implements the {@code books find} command.
 *
 * <p>Syntax: {@code books find <title|author|tag> <search string>}
 *
 * <p>Performs a case-insensitive substring search against the requested field.
 * The search string may contain spaces — all tokens from position 3 onward are
 * joined with a space character. Requires the user to be logged in.
 */
public class BooksFindCommand implements Command {
    private  final LibraryData libraryData;

    /**
     * Constructs the command with the shared library state.
     *
     * @param libraryData the in-memory library state
     */
    public BooksFindCommand(LibraryData libraryData) {
        this.libraryData = libraryData;
    }

    /**
     * Executes the {@code books find} command.
     *
     * <p>Expects at least four tokens: {@code books}, {@code find},
     * the search option, and at least one search-string token.
     *
     * @param args token array from the user's input line
     * @return formatted list of matching books, or an appropriate message
     */
    @Override
    public String execute(String[] args) {
        if(libraryData.getLoggedInUser()==null){
            return "You must be logged in to perform this action.";
        }
        if(args.length<4){
            return "Usage: books find <title|author|tag> <search_string>";
        }
        String option = args[2];
        String search=String.join(" ", Arrays.copyOfRange(args, 3, args.length));
        try{
            List<Book> foundBooks =libraryData.getBookActions().findBooks(option,search);
            if(foundBooks.isEmpty()){
                return "No books found.";
            }
            StringBuilder stringBuilder=new StringBuilder("Found Books: ");
            for(Book book:foundBooks){
                stringBuilder.append(book.shortInfo()).append("\n");
            }
            return stringBuilder.toString();

        }catch(BookException e){
            return "Error: " + e.getMessage();
        }

    }

    @Override
    public String getHelpMessage() {
        return "books find <option> <string> - finds a book with the given title, author, or tag.";
    }
}

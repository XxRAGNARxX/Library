package commands.impl.bookCommands;

import commands.Command;
import data.interfaces.LibraryData;
import exceptions.BookException;

/**
 * Implements the {@code books remove} command.
 *
 * <p>Syntax: {@code books remove <isbn>}
 *
 * <p>Removes the book with the given ISBN from the catalogue.
 * Requires the currently logged-in user to hold the {@code ADMIN} role.
 */
public class BooksRemoveCommand implements Command {
    private final LibraryData libraryData;
    /**
     * Constructs the command with the shared library state.
     *
     * @param libraryData the in-memory library state
     */
    public BooksRemoveCommand(LibraryData libraryData){
        this.libraryData = libraryData;
    }

    /**
     * Executes the {@code books remove} command.
     *
     * <p>Returns an error message if the user is not logged in or is not an admin.
     * Expects at least three tokens: {@code books}, {@code remove}, and the ISBN.
     *
     * @param args token array from the user's input line
     * @return success or error message
     */
    @Override
    public String execute(String[] args) {
       if (libraryData.getLoggedInUser()==null||!libraryData.getLoggedInUser().isAdmin()){
           return "Error: Only admins can perform this action";
       }
       if (args.length<3){
           return "Error: Please enter a valid book ID";
       }
       String isbn = args[2];
       try{
           libraryData.getBookActions().removeBook(isbn);
           return "Successfully removed "+isbn;
       }catch(BookException e){
           return "Error: "+e.getMessage();
       }
    }

    @Override
    public String getHelpMessage() {
        return "books remove <isbn> - removes a book from the library";
    }
}

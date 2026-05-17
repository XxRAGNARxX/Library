package commands.impl.bookCommands;

import commands.Command;
import data.interfaces.LibraryData;
import exceptions.BookException;

/**
 * Implements the {@code books sort} command.
 *
 * <p>Syntax: {@code books sort <title|author|year|rating> <asc|desc>}
 *
 * <p>Sorts the in-memory catalogue in-place. Changes are visible immediately
 * in subsequent {@code books all} output but are not automatically persisted;
 * the user must run {@code save} to write the new order to disk.
 *
 * <p>Requires the user to be logged in.
 */
public class BooksSortCommand implements Command {
    private final LibraryData libraryData;

    /**
     * Constructs the command with the shared library state.
     *
     * @param libraryData the in-memory library state
     */
    public BooksSortCommand(LibraryData libraryData) {
        this.libraryData = libraryData;
    }

    /**
     * Executes the {@code books sort} command.
     *
     * <p>Expects at least four tokens: {@code books}, {@code sort}, the sort key,
     * and the direction ({@code asc} or {@code desc}).
     *
     * @param args token array from the user's input line
     * @return success or error message
     */
    @Override
    public String execute(String[] args) {
       if(libraryData.getLoggedInUser()==null) {
            return "You are not logged in";
       }
       if(args.length<3) {
           return "Usage: books sort title|author|year|rating> [asc|desc]";
       }
       String option = args[2];
       boolean ascending=true;
        if (args[3].equalsIgnoreCase("desc")) {
            ascending=false;
        }else if (!args[3].equalsIgnoreCase("asc")) {
            return "Error: Direction must be 'asc' or 'desc'.";
        }

        try{
            libraryData.getBookActions().sortBooks(option,ascending);
            return "Books successfully sorted by " + option + (ascending ? " (ascending)." : " (descending).") +
                    "\nType 'books all' to view them.";
        }catch(BookException e){
            return "Error: " + e.getMessage();
        }
    }

    @Override
    public String getHelpMessage() {
        return "";
    }
}

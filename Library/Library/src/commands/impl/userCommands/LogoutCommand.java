package commands.impl.userCommands;

import commands.Command;
import data.interfaces.LibraryData;
import exceptions.AuthException;

/**
 * Implements the {@code logout} command.
 *
 * <p>Ends the current user's session by delegating to {@link LibraryData#logout()}.
 * Returns an error message if no user is currently logged in.
 */
public class LogoutCommand implements Command {
    private  final LibraryData libraryData;

    /**
     * Constructs the command with the shared library state.
     *
     * @param library the in-memory library state
     */
    public LogoutCommand(LibraryData library) {
        this.libraryData = library;
    }

    /**
     * Executes the {@code logout} command.
     *
     * @param args token array (not used beyond the command word)
     * @return success or error message
     */
    @Override
    public String execute(String[] args) {
       try {
           libraryData.logout();
           return "Logged out successfully";
       }catch (AuthException e){
           return e.getMessage();
       }
    }

    @Override
    public String getHelpMessage() {
        return "logout -Log out the user";
    }
}

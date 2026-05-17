package commands.impl.userCommands;

import commands.Command;
import data.interfaces.LibraryData;
import exceptions.UserException;
import models.Role;
import models.User;

/**
 * Implements the {@code users remove} command.
 *
 * <p>Syntax: {@code users remove <username>}
 *
 * <p>Removes the specified user from the system. The protected default
 * {@code admin} account cannot be deleted. Admin-only operation.
 */
public class UsersRemoveCommand implements Command {
    private final LibraryData libraryData;

    /**
     * Constructs the command with the shared library state.
     *
     * @param libraryData the in-memory library state
     */
    public UsersRemoveCommand(LibraryData libraryData) {
        this.libraryData = libraryData;
    }

    /**
     * Executes the {@code users remove} command.
     *
     * <p>Expects at least three tokens: {@code users}, {@code remove},
     * and the username to remove.
     *
     * @param args token array from the user's input line
     * @return success or error message
     */
    @Override
    public String execute(String[] args) {
        if(args.length < 3){
            return "usage: user remove <userName> ";
        }
        String username = args[2];
        try{
            libraryData.getUserActions().removeUser(username);

            return "User removed successfully";
        }catch(UserException e){
            return e.getMessage();
        }
    }
    @Override
    public String getHelpMessage() {
        return "user remove <userName> - removes a user from the library";
    }
}

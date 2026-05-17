package commands.impl.userCommands;

import commands.Command;
import data.LibrarySystem;
import data.interfaces.LibraryData;
import data.interfaces.UserActions;
import exceptions.UserException;
import models.Role;
import models.User;

/**
 * Implements the {@code users add} command.
 *
 * <p>Syntax: {@code users add <username> <password>}
 *
 * <p>Creates a new {@link User} with the {@link Role#CLIENT} role and registers
 * them in the user store. Admin-only operation.
 */
public class UsersAddCommand implements Command {
    private final LibraryData libraryData;

    /**
     * Constructs the command with the shared library state.
     *
     * @param libraryData the in-memory library state
     */
    public UsersAddCommand(LibraryData libraryData) {
        this.libraryData = libraryData;
    }

    /**
     * Executes the {@code users add} command.
     *
     * <p>Expects at least four tokens: {@code users}, {@code add},
     * the username, and the password. The new user is always created
     * with the {@link Role#CLIENT} role; promote to admin separately if needed.
     *
     * @param args token array from the user's input line
     * @return success or error message
     */
    @Override
    public String execute(String[] args) {
        if(args.length < 4){
            return "usage: user add <userName> <password> ";
        }

        String username = args[2];
        String password = args[3];
        try{
            User user = new User(username, password, Role.CLIENT);
            libraryData.getUserActions().addUser(user);
            return "User added successfully";
        }catch(UserException e){
            return e.getMessage();
        }
    }

    @Override
    public String getHelpMessage() {
        return "user add <userName> <password> - adds a user to the library";
    }
}

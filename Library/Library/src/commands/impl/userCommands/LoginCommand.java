package commands.impl.userCommands;

import commands.Command;
import core.PasswordReader;
import data.interfaces.LibraryData;
import exceptions.AuthException;
import java.util.Scanner;

/**
 * Implements the {@code login} command.
 *
 * <p>Interactively prompts for a username and password then delegates to
 * {@link LibraryData#login(String, String)}. The password is read via
 * {@link PasswordReader} which masks input when running in a real terminal.
 *
 * <p>Only one user can be logged in at a time. Attempting to log in while
 * already authenticated returns an error message.
 */
public class LoginCommand implements Command {
    private final LibraryData libraryData;

    /**
     * Constructs the command with the shared library state.
     *
     * @param libraryData the in-memory library state
     */
    public LoginCommand(LibraryData libraryData) {
        this.libraryData = libraryData;
    }

    /**
     * Executes the {@code login} command.
     *
     * <p>Returns immediately with a message if a user is already logged in.
     * Otherwise, prompts for credentials and attempts authentication.
     *
     * @param args token array (not used beyond the command word)
     * @return welcome message on success, or an error message on failure
     */
    @Override
    public String execute(String[] args) {
        if (libraryData.getLoggedInUser() != null) {
            return "You are already logged in";
        }
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter username: ");
        String username = scanner.nextLine();

        String password = PasswordReader.readPassword("Enter password: ");

        try {
            libraryData.login(username, password);
            return "Welcome, " + username + "!";
        } catch (AuthException e) {
            return "Login failed: " + e.getMessage();
        }
    }

    @Override
    public String getHelpMessage() {
        return "login - Logs a user into the system.";
    }
}
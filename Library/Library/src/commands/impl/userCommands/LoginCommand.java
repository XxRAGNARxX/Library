package commands.impl.userCommands;

import commands.Command;
import core.PasswordReader;
import data.interfaces.LibraryData;
import exceptions.AuthException;
import java.util.Scanner;

public class LoginCommand implements Command {
    private final LibraryData libraryData;

    public LoginCommand(LibraryData libraryData) {
        this.libraryData = libraryData;
    }

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
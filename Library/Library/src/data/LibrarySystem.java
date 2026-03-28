package data;

import data.interfaces.BookActions;
import data.interfaces.LibraryData;
import data.interfaces.UserActions;
import exceptions.AuthException;
import models.User;

import java.io.Serializable;

public class LibrarySystem implements LibraryData, Serializable {
    private BookActions bookManager;
    private UserActions userManager;

    private transient User loggedInUser;// not  be saved to the file when serial...

    public LibrarySystem() {
        this.bookManager = new BookManager();
        this.userManager = new UserManager();
        this.loggedInUser = null;
    }

    @Override
    public BookActions getBookActions() {
        return bookManager;
    }

    @Override
    public UserActions getUserActions() {
        return userManager;
    }

    @Override
    public User getLoggedInUser() {
        return loggedInUser;
    }

    @Override
    public void login(String username, String password) {
        if (loggedInUser != null) {
            if (loggedInUser.getUsername().equals(username)) {
                throw new AuthException("You are already logged in!");
            } else {
                throw new AuthException("Another user is currently logged in. Please logout first.");
            }
        }

        User user = userManager.getUserByUsername(username);
        if (user.getPassword().equals(password)) {
            this.loggedInUser = user;
        } else {
            throw new AuthException("Wrong credentials!");
        }
    }

    @Override
    public void logout() {
        if (loggedInUser == null) {
            throw new AuthException("You are not logged in!");
        }
        this.loggedInUser = null;
    }

    @Override
    public void setLibraryData(LibraryData libraryData) {
        this.bookManager = libraryData.getBookActions();
        this.userManager = libraryData.getUserActions();
    }
}
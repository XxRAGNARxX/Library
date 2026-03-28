package data.interfaces;

import models.User;

public interface LibraryData {
    BookActions getBookActions();
    UserActions getUserActions();

    User getLoggedInUser();
    void login(String username, String password);
    void logout();
    void setLibraryData(LibraryData libraryData);
}
package data.interfaces;

import models.User;

/**
 * Central facade for the library's in-memory state.
 *
 * <p>Aggregates access to the book catalogue ({@link BookActions}), user
 * registry ({@link UserActions}), and the current authentication session.
 * Commands interact exclusively through this interface, keeping them
 * decoupled from concrete implementations (DIP).
 */
public interface LibraryData {

    /**
     * Returns the book-management subsystem.
     *
     * @return the {@link BookActions} implementation
     */
    BookActions getBookActions();

    /**
     * Returns the user-management subsystem.
     *
     * @return the {@link UserActions} implementation
     */
    UserActions getUserActions();

    /**
     * Returns the currently logged-in user, or {@code null} if no user is authenticated.
     *
     * @return logged-in user, or {@code null}
     */

    User getLoggedInUser();

    /**
     * Authenticates a user by username and password.
     *
     * <p>On success the user is stored as the active session user. Only one
     * user can be logged in at a time.
     *
     * @param username the username to authenticate
     * @param password the plain-text password
     * @throws exceptions.AuthException if credentials are wrong or another user is already logged in
     */
    void login(String username, String password);

    /**
     * Ends the current user session.
     *
     * @throws exceptions.AuthException if no user is currently logged in
     */
    void logout();

    /**
     * Replaces the current book and user data with data from another {@link LibraryData} instance.
     *
     * <p>Used by {@link FileActions#open} to swap in deserialized state without
     * replacing the top-level object reference.
     *
     * @param libraryData the source of the new data
     */
    void setLibraryData(LibraryData libraryData);
}
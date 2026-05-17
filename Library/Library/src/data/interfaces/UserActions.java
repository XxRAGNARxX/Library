package data.interfaces;


import models.User;

/**
 * Defines the user-management operations available in the library system.
 *
 * <p>Implementations maintain the list of registered users and provide
 * lookup and existence-check utilities consumed by authentication and
 * administration commands.
 */
public interface UserActions {

    /**
     * Registers a new user in the system.
     *
     * @param user the user to add
     * @throws exceptions.UserException if a user with the same username already exists
     */
    void addUser(User user);

    /**
     * Removes the user with the given username from the system.
     *
     * @param username the username to remove
     * @throws exceptions.UserException if no such user exists or if the user is the
     *                                  protected default admin
     */
    void removeUser(String username);

    /**
     * Retrieves a user by their username (case-insensitive).
     *
     * @param username the username to look up
     * @return the matching {@link User}
     * @throws exceptions.UserException if no user with the given username exists
     */
    User getUserByUsername(String username);

    /**
     * Checks whether a user with the given username exists (case-insensitive).
     *
     * @param username the username to check
     * @return {@code true} if the user exists, {@code false} otherwise
     */
    boolean userExists(String username);
}
package data;

import data.interfaces.UserActions;
import exceptions.UserException;
import models.Role;
import models.User;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Default in-memory implementation of {@link UserActions}.
 *
 * <p>Users are stored in an {@link ArrayList}. Three default accounts are
 * seeded at construction time for development and testing convenience.
 * The {@code "admin"} account is protected and cannot be removed.
 *
 * <p>Username lookups are case-insensitive. Instances are serialized as part
 * of the library data file.
 */
public class UserManager implements UserActions, Serializable {
    private List<User> users;

    public UserManager() {
        this.users = new ArrayList<>();
        this.users.add(new User("admin", "i<3Java", Role.ADMIN));
        this.users.add(new User("Georgi", "1234", Role.ADMIN));
        this.users.add(new User("G", "1", Role.ADMIN));//for fast login

    }

    /**
     * {@inheritDoc}
     *
     * @throws UserException if a user with the same username already exists
     */
    @Override
    public void addUser(User user) {
        if (userExists(user.getUsername())) {
            throw new UserException("User already exists!");
        }
        users.add(user);
    }

    /**
     * {@inheritDoc}
     *
     * @throws UserException if the user does not exist or is the protected default admin
     */
    @Override
    public void removeUser(String username) {
        User user = getUserByUsername(username);
        if (user.isAdmin() && username.equals("admin")) {
            throw new UserException("Cannot remove the default admin!");
        }
        users.remove(user);
    }

    /**
     * {@inheritDoc}
     *
     * <p>Comparison is case-insensitive.
     *
     * @throws UserException if no user with the given username exists
     */
    @Override
    public User getUserByUsername(String username) {
        for (User user : users) {
            if (user.getUsername().equalsIgnoreCase(username)) {
                return user;
            }
        }
        throw new UserException("User not found!");
    }

    /**
     * {@inheritDoc}
     *
     * <p>Comparison is case-insensitive.
     */
    @Override
    public boolean userExists(String username) {
        for (User user : users) {
            if (user.getUsername().equalsIgnoreCase(username)) {
                return true;
            }
        }
        return false;
    }
}
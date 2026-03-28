package data;

import data.interfaces.UserActions;
import exceptions.UserException;
import models.Role;
import models.User;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class UserManager implements UserActions, Serializable {
    private List<User> users;

    public UserManager() {
        this.users = new ArrayList<>();
        this.users.add(new User("admin", "i<3Java", Role.ADMIN));
    }

    @Override
    public void addUser(User user) {
        if (userExists(user.getUsername())) {
            throw new UserException("User already exists!");
        }
        users.add(user);
    }

    @Override
    public void removeUser(String username) {
        User user = getUserByUsername(username);
        if (user.isAdmin() && username.equals("admin")) {
            throw new UserException("Cannot remove the default admin!");
        }
        users.remove(user);
    }

    @Override
    public User getUserByUsername(String username) {
        for (User user : users) {
            if (user.getUsername().equalsIgnoreCase(username)) {
                return user;
            }
        }
        throw new UserException("User not found!");
    }

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
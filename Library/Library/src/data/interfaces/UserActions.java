package data.interfaces;


import models.User;

public interface UserActions {
    void addUser(User user);
    void removeUser(String username);
    User getUserByUsername(String username);
    boolean userExists(String username);
}
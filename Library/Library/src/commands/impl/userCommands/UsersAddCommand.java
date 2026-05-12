package commands.impl.userCommands;

import commands.Command;
import data.LibrarySystem;
import data.interfaces.LibraryData;
import data.interfaces.UserActions;
import exceptions.UserException;
import models.Role;
import models.User;

public class UsersAddCommand implements Command {
    private final LibraryData libraryData;

    public UsersAddCommand(LibraryData libraryData) {
        this.libraryData = libraryData;
    }

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

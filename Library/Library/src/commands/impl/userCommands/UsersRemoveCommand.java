package commands.impl.userCommands;

import commands.Command;
import data.interfaces.LibraryData;
import exceptions.UserException;
import models.Role;
import models.User;

public class UsersRemoveCommand implements Command {
    private final LibraryData libraryData;
    public UsersRemoveCommand(LibraryData libraryData) {
        this.libraryData = libraryData;
    }

    @Override
    public String execute(String[] args) {
        if(args.length < 3){
            return "usage: user remove <userName> ";
        }
        String username = args[2];
        try{
            libraryData.getUserActions().removeUser(username);

            return "User removed successfully";
        }catch(UserException e){
            return e.getMessage();
        }
    }
    @Override
    public String getHelpMessage() {
        return "user remove <userName> - removes a user from the library";
    }
}

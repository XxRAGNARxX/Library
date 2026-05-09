package commands.impl;

import commands.Command;
import data.interfaces.LibraryData;
import exceptions.AuthException;

public class LogoutCommand implements Command {
    private  final LibraryData libraryData;

    public LogoutCommand(LibraryData library) {
        this.libraryData = library;
    }
    @Override
    public String execute(String[] args) {
       try {
           libraryData.logout();
           return "Logged out successfully";
       }catch (AuthException e){
           return e.getMessage();
       }
    }

    @Override
    public String getHelpMessage() {
        return "logout -Log out the user";
    }
}

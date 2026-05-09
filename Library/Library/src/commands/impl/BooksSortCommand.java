package commands.impl;

import commands.Command;
import data.interfaces.LibraryData;
import exceptions.BookException;

public class BooksSortCommand implements Command {
    private final LibraryData libraryData;
    public BooksSortCommand(LibraryData libraryData) {
        this.libraryData = libraryData;
    }

    @Override
    public String execute(String[] args) {
       if(libraryData.getLoggedInUser()==null) {
            return "You are not logged in";
       }
       if(args.length<3) {
           return "Usage: books sort title|author|year|rating> [asc|desc]";
       }
       String option = args[2];
       boolean ascending=true;
        if (args[3].equalsIgnoreCase("desc")) {
            ascending=false;
        }else if (!args[3].equalsIgnoreCase("asc")) {
            return "Error: Direction must be 'asc' or 'desc'.";
        }

        try{
            libraryData.getBookActions().sortBooks(option,ascending);
            return "Books successfully sorted by " + option + (ascending ? " (ascending)." : " (descending).") +
                    "\nType 'books all' to view them.";
        }catch(BookException e){
            return "Error: " + e.getMessage();
        }
    }

    @Override
    public String getHelpMessage() {
        return "";
    }
}

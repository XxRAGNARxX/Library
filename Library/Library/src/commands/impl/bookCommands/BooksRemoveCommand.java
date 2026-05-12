package commands.impl.bookCommands;

import commands.Command;
import data.interfaces.LibraryData;
import exceptions.BookException;

public class BooksRemoveCommand implements Command {
    private final LibraryData libraryData;
    public BooksRemoveCommand(LibraryData libraryData){
        this.libraryData = libraryData;
    }
    @Override
    public String execute(String[] args) {
       if (libraryData.getLoggedInUser()==null||!libraryData.getLoggedInUser().isAdmin()){
           return "Error: Only admins can perform this action";
       }
       if (args.length<3){
           return "Error: Please enter a valid book ID";
       }
       String isbn = args[2];
       try{
           libraryData.getBookActions().removeBook(isbn);
           return "Successfully removed "+isbn;
       }catch(BookException e){
           return "Error: "+e.getMessage();
       }
    }

    @Override
    public String getHelpMessage() {
        return "books remove <isbn> - removes a book from the library";
    }
}

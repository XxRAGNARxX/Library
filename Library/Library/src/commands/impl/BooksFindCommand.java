package commands.impl;

import commands.Command;
import data.interfaces.LibraryData;
import exceptions.BookException;
import models.Book;

import java.util.Arrays;
import java.util.List;

public class BooksFindCommand implements Command {
    private  final LibraryData libraryData;
    public BooksFindCommand(LibraryData libraryData) {
        this.libraryData = libraryData;
    }

    @Override
    public String execute(String[] args) {
        if(libraryData.getLoggedInUser()==null){
            return "You must be logged in to perform this action.";
        }
        if(args.length<4){
            return "Usage: books find <title|author|tag> <search_string>";
        }
        String option = args[2];
        String search=String.join(" ", Arrays.copyOfRange(args, 3, args.length));
        try{
            List<Book> foundBooks =libraryData.getBookActions().findBooks(option,search);
            if(foundBooks.isEmpty()){
                return "No books found.";
            }
            StringBuilder stringBuilder=new StringBuilder("Found Books: ");
            for(Book book:foundBooks){
                stringBuilder.append(book.shortInfo()).append("\n");
            }
            return stringBuilder.toString();

        }catch(BookException e){
            return "Error: " + e.getMessage();
        }

    }

    @Override
    public String getHelpMessage() {
        return "books find <option> <string> - finds a book with the given title, author, or tag.";
    }
}

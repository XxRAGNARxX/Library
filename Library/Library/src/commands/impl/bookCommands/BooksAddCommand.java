package commands.impl.bookCommands;

import commands.Command;
import data.interfaces.LibraryData;
import exceptions.BookException;
import models.Book;
import models.User;

import java.util.Scanner;

public class BooksAddCommand implements Command {
    final LibraryData libraryData;
    public BooksAddCommand(LibraryData library) {
        this.libraryData = library;
    }
    @Override
    public String execute(String[] args) {
        User currentUser = libraryData.getLoggedInUser();
        if (currentUser == null) {
            return "You are not logged in!";
        }
        if (!currentUser.isAdmin()) {
            return "You are not an admin!";
        }
        Scanner scanner = new Scanner(System.in);
        System.out.println("Add new book ");
        try {
            System.out.println("Enter ISBN: ");
            String isbn = scanner.nextLine();

            System.out.println("Enter Title: ");
            String title = scanner.nextLine();

            System.out.println("Enter Author: ");
            String author = scanner.nextLine();

            System.out.println("Enter Genre: ");
            String genre = scanner.nextLine();

            System.out.println("Enter Description: ");
            String description = scanner.nextLine();

            System.out.println("Enter Year Published: ");
            int year = Integer.parseInt(scanner.nextLine());

            System.out.println("Enter Rating ");
            double rating =Double.parseDouble(scanner.nextLine());
            Book newBook = new Book(isbn, title, author, genre, description, year, rating);

            System.out.print("Enter tags (comma separated, or press Enter to skip): ");
            String tagsInput = scanner.nextLine().trim();
            if (!tagsInput.isEmpty()) {
                String[] tags = tagsInput.split(",");
                for (String tag : tags) {
                    newBook.addTag(tag.trim());
                }
            }
            libraryData.getBookActions().addBook(newBook);
            return "Successfully added Book:"+title;
        }catch (BookException e){
            return "Error: "+e.getMessage();
        }
    }

    @Override
    public String getHelpMessage() {
        return "books add - adds a new book to library";
    }
}

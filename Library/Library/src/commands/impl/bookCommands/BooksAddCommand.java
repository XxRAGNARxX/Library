package commands.impl.bookCommands;

import commands.Command;
import data.interfaces.LibraryData;
import exceptions.BookException;
import models.Book;
import models.User;

import java.util.Scanner;

/**
 * Implements the {@code books add} command.
 *
 * <p>Interactively prompts the admin user for all required book fields
 * (ISBN, title, author, genre, description, year, rating, and optional tags)
 * then adds the resulting {@link Book} to the catalogue.
 *
 * <p>Requires the currently logged-in user to hold the {@code ADMIN} role.
 */
public class BooksAddCommand implements Command {
    final LibraryData libraryData;

    /**
     * Constructs the command with the shared library state.
     *
     * @param library the in-memory library state
     */
    public BooksAddCommand(LibraryData library) {
        this.libraryData = library;
    }

    /**
     * Executes the {@code books add} command.
     *
     * <p>Returns an error message if the user is not logged in or is not an admin.
     * On success, prompts line-by-line for each book field and delegates to
     * {@link data.interfaces.BookActions#addBook(Book)}.
     *
     * @param args token array (not used beyond the command word)
     * @return success or error message
     */
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

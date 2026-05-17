package commands.impl;

import commands.Command;

/**
 * Prints the full list of commands supported by the Library Management System.
 *
 * <p>This command requires no authentication and accepts no arguments.
 */
public class HelpCommand implements Command {


    @Override
    public String execute(String[] args) {
        StringBuilder helpText = new StringBuilder();

        helpText.append("The following commands are supported:\n");

        helpText.append("open <file>                 - opens <file>\n");
        helpText.append("close                       - closes currently opened file\n");
        helpText.append("save                        - saves the currently open file\n");
        helpText.append("save as <file>              - saves the currently open file in <file>\n");
        helpText.append("help                        - prints this information\n");
        helpText.append("exit                        - exits the program\n");
        helpText.append("login                       - prompts for username and password\n");
        helpText.append("logout                      - logs out the current user\n");
        helpText.append("books all                   - lists short info for all books\n");
        helpText.append("books info <isbn>           - lists detailed info for a specific book\n");
        helpText.append("books find <option> <str>   - finds books by title, author or tag\n");
        helpText.append("books sort <option> [asc]   - sorts books by title, author, year or rating\n");
        helpText.append("books add                   - adds a new book (Admin only)\n");
        helpText.append("books remove <isbn>         - removes a book by ISBN (Admin only)\n");
        helpText.append("users add <user> <pass>     - adds a new user (Admin only)\n");
        helpText.append("users remove <user>         - removes a user (Admin only)");

        return helpText.toString();
    }

    @Override
    public String getHelpMessage() {
        return "help - prints information about supported commands.";
    }
}
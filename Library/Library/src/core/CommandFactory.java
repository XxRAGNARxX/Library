package core;

import commands.Command;
import commands.CommandsIndex;
import commands.impl.*;
import data.interfaces.FileActions;
import data.interfaces.LibraryData;

import java.util.HashMap;
import java.util.Map;

public class CommandFactory {
    private final Map<CommandsIndex, Command> commands;

    public CommandFactory(LibraryData libraryData, FileActions fileActions) {
        this.commands = new HashMap<>();

        commands.put(CommandsIndex.LOGIN, new LoginCommand(libraryData));
        commands.put(CommandsIndex.BOOKS_ALL, new BooksAllCommand(libraryData));
        commands.put(CommandsIndex.HELP,new HelpCommand());
        commands.put(CommandsIndex.LOGOUT, new LogoutCommand(libraryData));
        commands.put(CommandsIndex.BOOKS_ADD, new BooksAddCommand(libraryData));
        commands.put(CommandsIndex.SAVE,new SaveCommand(libraryData, fileActions));
        commands.put(CommandsIndex.SAVE_AS,new SaveAsCommand(libraryData, fileActions));
        commands.put(CommandsIndex.OPEN,new OpenCommand(libraryData, fileActions));
        commands.put(CommandsIndex.BOOKS_FIND, new BooksFindCommand(libraryData));
        commands.put(CommandsIndex.BOOKS_INFO,new BooksInfoCommand(libraryData));
        commands.put(CommandsIndex.BOOKS_REMOVE,new BooksRemoveCommand(libraryData));
        commands.put(CommandsIndex.BOOKS_SORT,new BooksSortCommand(libraryData));
    }

    public Command getCommand(CommandsIndex index) {
        return commands.get(index);
    }
}
package commands.impl.fileCommands;

import commands.Command;
import data.interfaces.FileActions;
import data.interfaces.LibraryData;
import exceptions.FileException;

import java.io.File;

/**
 * Implements the {@code open} command.
 *
 * <p>Syntax: {@code open <file_path>}
 *
 * <p>Opens an existing library data file (deserializing its content into the
 * in-memory state) or creates a new empty file at the given path if it does
 * not yet exist. Only one file may be open at a time.
 */
public class OpenCommand implements Command {
    private final LibraryData libraryData;
    private  final FileActions fileActions;

    /**
     * Constructs the command with the shared library state and file service.
     *
     * @param libraryData the in-memory library state
     * @param fileActions the file persistence service
     */
    public OpenCommand(LibraryData libraryData, FileActions fileActions) {
        this.libraryData = libraryData;
        this.fileActions = fileActions;
    }

    /**
     * Executes the {@code open} command.
     *
     * <p>Expects at least two tokens: {@code open} and the file path.
     *
     * @param args token array from the user's input line
     * @return success or error message
     */

    @Override
    public String execute(String[] args) {
        if (args.length < 2) {
            return "Error: Please specify a file path. Usage: open <file_path>";
        }

        String filePath = args[1];
        try {
            fileActions.open(libraryData, new File(filePath));
            return "Successfully opened file: " + filePath;
        } catch (FileException e) {
            return "Error: " + e.getMessage();
        }
    }

    @Override
    public String getHelpMessage() {
        return "open <file> - opens the specified file or creates a new one.";
    }
}
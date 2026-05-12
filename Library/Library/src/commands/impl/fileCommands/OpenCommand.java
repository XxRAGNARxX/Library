package commands.impl.fileCommands;

import commands.Command;
import data.interfaces.FileActions;
import data.interfaces.LibraryData;
import exceptions.FileException;

import java.io.File;

public class OpenCommand implements Command {
    private final LibraryData libraryData;
    private  final FileActions fileActions;
    public OpenCommand(LibraryData libraryData, FileActions fileActions) {
        this.libraryData = libraryData;
        this.fileActions = fileActions;
    }


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
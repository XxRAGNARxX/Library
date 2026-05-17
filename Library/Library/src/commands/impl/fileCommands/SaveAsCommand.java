package commands.impl.fileCommands;

import commands.Command;
import data.interfaces.FileActions;
import data.interfaces.LibraryData;
import exceptions.FileException;

import java.io.File;

/**
 * Implements the {@code save as} command.
 *
 * <p>Syntax: {@code save as <file_path>}
 *
 * <p>Writes the current in-memory library state to a new file path.
 * After a successful save-as, the new path becomes the active open file,
 * so subsequent {@code save} commands will target it.
 */
public class SaveAsCommand implements Command {

    private  final LibraryData libraryData;
    private final FileActions fileActions;

    /**
     * Constructs the command with the shared library state and file service.
     *
     * @param libraryData the in-memory library state
     * @param fileActions the file persistence service
     */
    public SaveAsCommand(LibraryData libraryData, FileActions fileActions) {
        this.libraryData = libraryData;
        this.fileActions = fileActions;
    }

    /**
     * Executes the {@code save as} command.
     *
     * <p>Expects at least three tokens: {@code save}, {@code as}, and the file path.
     *
     * @param args token array from the user's input line
     * @return success or error message
     */
    @Override
    public String execute(String[] args) {
        if(args.length < 3){
            return "needs specific file path.Usage: save as <filePath>";
        }

        String filePath = args[2];
        try{
            fileActions.write(libraryData,new File(filePath));
            return "Successfully saved as "+filePath;
        }catch(FileException e){
            return "Error: "+e.getMessage();
        }
    }

    @Override
    public String getHelpMessage() {
        return "save as <file> - saves the info to a new file path";
    }
}

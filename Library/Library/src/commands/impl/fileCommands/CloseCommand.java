package commands.impl.fileCommands;

import commands.Command;
import data.interfaces.FileActions;
import data.interfaces.LibraryData;
import exceptions.FileException;

/**
 * Implements the {@code close} command.
 *
 * <p>Closes the currently open library data file and resets the in-memory
 * state to a blank default. Any unsaved changes are lost.
 */
public class CloseCommand implements Command {
    private  final LibraryData libraryData;
    private final FileActions fileActions;

    /**
     * Constructs the command with the shared library state and file service.
     *
     * @param libraryData the in-memory library state
     * @param fileActions the file persistence service
     */
    public CloseCommand(LibraryData libraryData, FileActions fileActions) {
        this.libraryData = libraryData;
        this.fileActions = fileActions;
    }

    /**
     * Executes the {@code close} command.
     *
     * <p>Returns an error message if no file is currently open.
     *
     * @param args token array (not used beyond the command word)
     * @return success or error message
     */
    @Override
    public String execute(String[] args) {
       if(!fileActions.isOpen()){
           return "Error: No file opened";
       }
       try{
           fileActions.close(libraryData);
           return "Successfully closed file";
       }catch(FileException e){
           return "Error: "+ e.getMessage();
       }
    }

    @Override
    public String getHelpMessage() {
        return "";
    }
}

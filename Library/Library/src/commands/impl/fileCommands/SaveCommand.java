package commands.impl.fileCommands;

import commands.Command;
import data.interfaces.FileActions;
import data.interfaces.LibraryData;
import exceptions.FileException;

/**
 * Implements the {@code save} command.
 *
 * <p>Persists the current in-memory library state to the currently open file.
 * A file must already be open (via {@code open}) before saving.
 */
public class SaveCommand implements Command {
private  final LibraryData libraryData;
private final FileActions fileActions;

    /**
     * Constructs the command with the shared library state and file service.
     *
     * @param libraryData the in-memory library state
     * @param fileActions the file persistence service
     */
public SaveCommand(LibraryData libraryData, FileActions fileActions) {
    this.libraryData = libraryData;
    this.fileActions = fileActions;
}

    /**
     * Executes the {@code save} command.
     *
     * <p>Returns an error message if no file is currently open.
     *
     * @param args token array (not used beyond the command word)
     * @return success or error message
     */
    @Override
    public String execute(String[] args) {

       if(!fileActions.isOpen()){
           return "Please open the file";
       }
       try{
       fileActions.write(libraryData,null);
       return "Successfully wrote to the file";
       }catch(FileException e){
           return "Error: "+e.getMessage();
       }
    }

    @Override
    public String getHelpMessage() {
        return "save - saves the info";
    }
}

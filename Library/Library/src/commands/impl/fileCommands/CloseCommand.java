package commands.impl.fileCommands;

import commands.Command;
import data.interfaces.FileActions;
import data.interfaces.LibraryData;
import exceptions.FileException;

public class CloseCommand implements Command {
    private  final LibraryData libraryData;
    private final FileActions fileActions;
    public CloseCommand(LibraryData libraryData, FileActions fileActions) {
        this.libraryData = libraryData;
        this.fileActions = fileActions;
    }

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

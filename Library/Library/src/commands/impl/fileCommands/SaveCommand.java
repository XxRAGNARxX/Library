package commands.impl.fileCommands;

import commands.Command;
import data.interfaces.FileActions;
import data.interfaces.LibraryData;
import exceptions.FileException;

public class SaveCommand implements Command {
private  final LibraryData libraryData;
private final FileActions fileActions;

public SaveCommand(LibraryData libraryData, FileActions fileActions) {
    this.libraryData = libraryData;
    this.fileActions = fileActions;
}

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

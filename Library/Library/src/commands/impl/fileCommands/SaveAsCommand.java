package commands.impl.fileCommands;

import commands.Command;
import data.interfaces.FileActions;
import data.interfaces.LibraryData;
import exceptions.FileException;

import java.io.File;

public class SaveAsCommand implements Command {

    private  final LibraryData libraryData;
    private final FileActions fileActions;

    public SaveAsCommand(LibraryData libraryData, FileActions fileActions) {
        this.libraryData = libraryData;
        this.fileActions = fileActions;
    }

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

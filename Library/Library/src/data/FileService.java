package data;

import data.interfaces.FileActions;
import data.interfaces.LibraryData;
import exceptions.FileException;

import java.io.*;

public class FileService implements FileActions {
    private File currentFile;

    public FileService() {}

    @Override
    public boolean open(LibraryData libraryData, File file) {
        if (isOpen()) {
            throw new FileException("A File is already open. Close it before opening a new one");
        }

        if (file.exists()) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
                libraryData.setLibraryData((LibraryData) ois.readObject());
                this.currentFile = file;
                return true;
            } catch (ClassNotFoundException | IOException e) {
                this.currentFile = null;
                throw new FileException("Error loading file" + e.getMessage());
            }
        } else {
            this.currentFile = file;
            return write(libraryData, file);
        }
    }

    @Override
    public void close(LibraryData libraryData) {
        if (!isOpen()) {
            throw new FileException("There is no open file to close.");
        }
        this.currentFile = null;
        libraryData.setLibraryData(new LibrarySystem());
    }

    @Override
    public boolean write(LibraryData libraryData, File file) {
        File fileToWrite = (file != null) ? file : this.currentFile;
        if (fileToWrite == null) {
            throw new FileException("There is no open file to save to.");
        }
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileToWrite))) {
            oos.writeObject(libraryData);
            this.currentFile = fileToWrite;
            return true;
        } catch (IOException e) {
            throw new FileException("Could not save file: " + e.getMessage());
        }
    }

    @Override
    public boolean isOpen() {
        return currentFile != null && currentFile.exists() && currentFile.isFile();
    }

    @Override
    public File getFile() {
        return currentFile;
    }
}
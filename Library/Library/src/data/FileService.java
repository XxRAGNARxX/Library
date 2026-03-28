package data;

import data.interfaces.FileActions;
import data.interfaces.LibraryData;
import exceptions.FileException;

import java.io.*;

public class FileService implements FileActions {
    private File file;

    public FileService() {}

    @Override
    public boolean open(LibraryData libraryData, File file) {
        if (isOpen()) {
            throw new FileException("A File is already open. Close it before opening a new one");
        }

        if (file.exists()) {
            this.file = file;

            try (FileInputStream fis = new FileInputStream(file);
                 ObjectInputStream ois = new ObjectInputStream(fis)) {

                libraryData.setLibraryData((LibraryData) ois.readObject());
                return true;

            } catch (ClassNotFoundException | IOException e) {
                this.file = null;
                throw new FileException("Error loading file " + e.getMessage());
            }
        } else {
            try {
                file.createNewFile();
                this.file = file;
                write(libraryData, file);
                return true;
            } catch (IOException e) {
                throw new FileException("Could not create new file " + e.getMessage());
            }
        }
    }

    @Override
    public void close(LibraryData libraryData) {
        if (!isOpen()) {
            throw new FileException("There is no open file to close");
        }
        this.file = null;
        libraryData.setLibraryData(new LibrarySystem());
    }

    @Override
    public boolean write(LibraryData libraryData, File file) {
        if (file == null) {
            if (this.file != null) {
                file = this.file;
            } else {
                throw new FileException("There is no open file to save to.");
            }
        }

        try {
            if (!file.exists()) {
                file.createNewFile();
            }
            this.file = file;

            try (FileOutputStream fos = new FileOutputStream(file);
                 ObjectOutputStream oos = new ObjectOutputStream(fos)) {

                oos.writeObject(libraryData);
                return true;
            }
        } catch (IOException e) {
            throw new FileException("Could not save file: " + e.getMessage());
        }
    }

    @Override
    public boolean isOpen() {
        return (file != null && file.exists() && file.isFile());
    }

    @Override
    public File getFile() {
        return file;
    }
}
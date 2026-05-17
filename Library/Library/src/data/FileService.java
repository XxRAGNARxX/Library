package data;

import data.interfaces.FileActions;
import data.interfaces.LibraryData;
import exceptions.FileException;

import java.io.*;

/**
 * Default implementation of {@link FileActions} using Java object serialization.
 *
 * <p>Maintains a reference to the currently open {@link File}. The system
 * enforces a single-file-at-a-time policy: calling {@link #open} while a file
 * is already open throws a {@link FileException}.
 *
 * <p>On {@link #close} the in-memory state is reset to a fresh {@link LibrarySystem}.
 * The logged-in user is marked {@code transient} in {@link LibrarySystem} so it is
 * never persisted to disk.
 */
public class FileService implements FileActions {
    private File currentFile;
    /**
     * Constructs a {@code FileService} with no file open.
     */


    public FileService() {

    }

    /**
     * {@inheritDoc}
     *
     * <p>If the file exists it is deserialized and its data replaces the current
     * in-memory state. If the file does not exist an empty library is written to
     * create it.
     *
     * @throws FileException if a file is already open or an I/O error occurs
     */
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
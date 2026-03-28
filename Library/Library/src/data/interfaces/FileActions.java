package data.interfaces;

import java.io.File;

public interface FileActions {
    boolean open(LibraryData libraryData, File file);
    void close(LibraryData libraryData);
    boolean write(LibraryData libraryData, File file);
    boolean isOpen();
    File getFile();
}
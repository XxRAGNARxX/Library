package data.interfaces;

import java.io.File;

/**
 * Defines the file I/O operations used to persist and restore library data.
 *
 * <p>The system works with a single open file at a time. Implementations
 * serialize and deserialize {@link LibraryData} using Java object serialization.
 */
public interface FileActions {

    /**
     * Opens (or creates) a library data file and loads its contents.
     *
     * <p>If the file already exists its content is deserialized and applied to
     * {@code libraryData} via {@link LibraryData#setLibraryData(LibraryData)}.
     * If the file does not exist, a new empty file is written.
     *
     * @param libraryData the current in-memory library state to populate
     * @param file        the file to open
     * @return {@code true} on success
     * @throws exceptions.FileException if a file is already open or an I/O error occurs
     */
    boolean open(LibraryData libraryData, File file);

    /**
     * Closes the currently open file and resets the in-memory library state.
     *
     * <p>After a close, the system is back to a blank default state and no
     * file is considered open until the next {@link #open} call.
     *
     * @param libraryData the current in-memory library state to reset
     * @throws exceptions.FileException if no file is currently open
     */
    void close(LibraryData libraryData);

    /**
     * Serializes the library state and writes it to a file.
     *
     * <p>If {@code file} is {@code null}, the write targets the currently open file.
     * Passing a non-null {@code file} also updates the current open-file reference
     * (used by {@code save as}).
     *
     * @param libraryData the in-memory library state to persist
     * @param file        destination file, or {@code null} to write to the currently open file
     * @return {@code true} on success
     * @throws exceptions.FileException if no file is open (when {@code file} is {@code null})
     *                                  or an I/O error occurs
     */
    boolean write(LibraryData libraryData, File file);

    /**
     * Returns {@code true} if a file is currently open and accessible on disk.
     *
     * @return {@code true} if a valid file is open
     */
    boolean isOpen();

    /**
     * Returns the currently open {@link File}, or {@code null} if no file is open.
     *
     * @return the open file reference
     */
    File getFile();
}
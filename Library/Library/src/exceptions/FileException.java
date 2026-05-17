package exceptions;

/**
 * Thrown when a file I/O operation fails in the Library Management System.
 *
 * <p>Typical scenarios: opening a file when one is already open, saving
 * without an open file, or encountering an {@link java.io.IOException}
 * during serialization.
 */
public class FileException extends RuntimeException {

    /**
     * Constructs a {@code FileException} with the supplied detail message.
     *
     * @param message human-readable description of the file operation failure
     */
    public FileException(String message) {
        super(message);
    }
}
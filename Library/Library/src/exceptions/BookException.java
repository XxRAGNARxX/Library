package exceptions;

/**
 * Thrown when a book-related operation fails.
 *
 * <p>Typical scenarios: adding a book with a duplicate ISBN, looking up
 * a book that does not exist, or supplying an invalid search/sort option.
 */
public class BookException extends RuntimeException {

    /**
     * Constructs a {@code BookException} with the supplied detail message.
     *
     * @param message human-readable description of the book operation failure
     */
    public BookException(String message) {
        super(message);
    }
}
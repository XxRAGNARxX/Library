package exceptions;

/**
 * Thrown when a CLI command encounters an unexpected error during execution.
 *
 * <p>Use this exception for command-level issues not covered by the more
 * specific domain exceptions ({@link BookException}, {@link UserException}, etc.).
 */
public class CommandException extends RuntimeException {

    /**
     * Constructs a {@code CommandException} with the supplied detail message.
     *
     * @param message human-readable description of the command failure
     */
    public CommandException(String message) {
        super(message);
    }
}
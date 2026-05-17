package exceptions;

/**
 * Thrown when a user-management operation fails.
 *
 * <p>Typical scenarios: adding a duplicate username, attempting to remove
 * a user that does not exist, or trying to delete the protected default admin.
 */
public class UserException extends RuntimeException {

    /**
     * Constructs a {@code UserException} with the supplied detail message.
     *
     * @param message human-readable description of the user operation failure
     */
    public UserException(String message) {
        super(message);
    }
}
package exceptions;

/**
 * Thrown when an authentication or authorisation operation fails.
 *
 * <p>Typical scenarios: wrong password, attempting to log in while already
 * logged in, or calling a protected operation while not logged in.
 */
public class AuthException extends RuntimeException {

    /**
     * Constructs an {@code AuthException} with the supplied detail message.
     *
     * @param message human-readable description of the authentication failure
     */
    public AuthException(String message) {
        super(message);
    }
}
package exception;

/**
 * A custom exception to notify user how to write correct syntax.
 */
public class BatmanException extends RuntimeException {
    public BatmanException(String message) {
        super(message);
    }
}

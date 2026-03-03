package duchess.exception;

/**
 * Custom exception class to indicate invalid input arguments to commands.
 */
public class InvalidArgumentException extends Exception {
    public InvalidArgumentException(String message) {
        super(message);
    }
}

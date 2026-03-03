package duchess.exception;

/**
 * Custom exception class to indicate missing input arguments to commands.
 */
public class MissingArgumentException extends Exception {
    public MissingArgumentException(String message) {
        super(message);
    }
}

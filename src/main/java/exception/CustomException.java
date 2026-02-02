package exception;
/**
 * Handles missing /st and /by errors
 */
public class CustomException extends RuntimeException {
    public CustomException(String message) {
        super(message);
    }
}
package exception;

/**
 * Handles missing /st and /by errors
 */
public class CustomException extends RuntimeException {
    /**
     * Custom Exception carries a customized message
     * @param message error message to warn the user
     */
    public CustomException(String message) {
        super(message);
    }
}
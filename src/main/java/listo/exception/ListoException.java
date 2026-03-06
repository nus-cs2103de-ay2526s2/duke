package listo.exception;

/**
 * Represents exceptions specific to the listo.Listo chatbot.
 * Used for handling user input errors and logical constraints.
 */
public class ListoException extends Exception {
    /**
     * Creates a new ListoException with a specific error message.
     *
     * @param message The error message to display to the user.
     */
    public ListoException(String message) {
        super(message);
    }
}
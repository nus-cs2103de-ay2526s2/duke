package dill.exception;

/**
 * Represents an error that occurs when the user input cannot be parsed due to syntax issues.
 */
public class InvalidCommandException extends DillException {

    /**
     * Creates an instance of InvalidCommandException with the specified message.
     *
     * @param message The detailed message specifying the cause of the invalid command.
     */
    public InvalidCommandException(String message) {
        super(message);
    }
}

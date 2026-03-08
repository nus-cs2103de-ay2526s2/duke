package dill.exception;

/**
 * Represents the base exception class for all errors specific to Dill.
 */
public class DillException extends Exception {

    /**
     * Creates an instance of DillException with the specified message.
     *
     * @param message The detailed message specifying the cause of the exception.
     */
    public DillException(String message) {
        super(message);
    }
}

package dill.exception;

/**
 * Represents an error that occurs during the execution of a command.
 */
public class ExecutionException extends DillException {

    /**
     * Creates an instance of ExecutionException with the specified message.
     *
     * @param message The detailed message specifying the cause of execution error.
     */
    public ExecutionException(String message) {
        super(message);
    }
}

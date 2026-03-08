package dill.exception;

/**
 * Represents an error that occurs when a file is detected to be corrupted during storage operations.
 */
public class CorruptedFileException extends StorageException {

    /**
     * Creates an instance of CorruptedFileException with the specified message.
     *
     * @param message The detailed message explaining the cause of corruption.
     */
    public CorruptedFileException(String message) {
        super(message);
    }
}

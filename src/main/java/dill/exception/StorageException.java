package dill.exception;

/**
 * Represents an error that occurs during data storage operations.
 * Typically due to an invalid file path or issues reading/writing from/to the data storage file.
 */
public class StorageException extends DillException {

    /**
     * Creates an instance of StorageException with the specified message.
     *
     * @param message The detailed message specifying the cause of the storage operation error.
     */
    public StorageException(String message) {
        super(message);
    }
}

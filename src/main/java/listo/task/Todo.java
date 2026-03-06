package listo.task;

/**
 * Represents a listo.task.Todo task.
 * A simple task with only a description.
 */
public class Todo extends Task {

    /**
     * Creates a new listo.task.Todo task.
     *
     * @param description The task description.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Returns the string representation of the listo.task.Todo task.
     * Prepends "[T]" to the standard task string.
     *
     * @return The formatted string.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Returns the string format of the listo.task.Todo task for saving to a file.
     * Appends the type identifier "T" to the standard task format.
     *
     * @return A string representation of the listo.task.Todo task for file storage (e.g., "T | 0 | read book").
     */
    @Override
    public String toFileFormat() {
        return "T | " + super.toFileFormat();
    }
}
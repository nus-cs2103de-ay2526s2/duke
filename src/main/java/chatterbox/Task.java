package chatterbox;

/**
 * Represents a task with a description and completion status.
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Creates a task with the given description.
     *
     * @param description Task description.
     */
    public Task(String description) {
        assert description != null : "Task description should not be null";
        assert !description.isBlank() : "Task description should not be blank";
        
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns the task description (without type/status decorations).
     *
     * @return Raw description string.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Marks the task as done.
     */
    public void markDone() {
        isDone = true;
    }

    /**
     * Marks the task as not done.
     */
    public void markNotDone() {
        isDone = false;
    }

    /**
     * Sets the done status of this task.
     *
     * @param done True if done, false otherwise.
     */
    public void setDone(boolean done) {
        isDone = done;
    }

    /**
     * Returns the status icon displayed in the UI.
     *
     * @return "X" if done, otherwise a blank space.
     */
    protected String statusIcon() {
        return isDone ? "X" : " ";
    }

    /**
     * Returns the task type icon used in string representations.
     *
     * @return Single-letter task type icon.
     */
    protected abstract String typeIcon();

    /**
     * Converts this task into a storage-friendly string format.
     *
     * @return Storage string.
     */
    public String toStorageString() {
        return typeIcon() + " | " + (isDone ? "1" : "0") + " | " + description;
    }

    @Override
    public String toString() {
        return "[" + typeIcon() + "][" + statusIcon() + "] " + description;
    }
}
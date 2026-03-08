package bob;

/**
 * Task class
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructor class
     * @param description the task description
     */
    public Task (String description) {
        this.description = description;
        this.isDone = false;
    }
    /**
     * Mark task as done
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Mark task as not done
     */
    public void markAsNotDone() {
        this.isDone = false;
    }

    /**
     * Return status icon for task
     * @return the status icon
     * [X] if done, [] if not done
     */
    public String getStatusIcon() {
        if (isDone) {
            return "[X]";
        }  else {
            return "[]";
        }
    }

    /**
     * Get task description from user
     * @return the task description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Convert status to string
     *  @return the icon plus task
     */
    @Override
    public String toString() {
        return getStatusIcon() + " " + description;
    }
}

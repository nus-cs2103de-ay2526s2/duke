package bob;

/**
 * Represents todo task in Bob
 */
public class ToDo extends Task {
    /**
     * Create todo task with description
     * @param description the description of the task
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * Return as string representation
     * Add "[T]" in front
     * @return the formatted todo task string
     */
    @Override
    public String toString() {
        return "[T]" + getStatusIcon() + " " + description;
    }
}

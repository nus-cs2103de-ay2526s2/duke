package mickey.task;

/**
 * Abstract class for tasks for todo, event and deadline tasks to extend from
 */
public abstract class Task {
    protected String description;
    protected boolean isComplete;
    protected TaskTypes type;

    /**
     * Constructor for task
     */
    public Task(String description) {
        this.description = description;
        this.isComplete = false;
    }

    public abstract String toString();

    /**
     * Marks task as complete
     */
    public void markDone() {
        this.isComplete = true;
    }

    /**
     * Get task type
     */
    public TaskTypes getTypeOfTask() {
        return type;
    }

    /**
     * Gets task type letter
     */
    public String getTypeLetter() {
        switch (type) {
        case TODO:
            return "[T]";
        case DEADLINE:
            return "[D]";
        case EVENT:
            return "[E]";
        default:
            return "?";
        }
    }

    /**
     * Marks task as incomplete
     */
    public void markUndone() {
        this.isComplete = false;
    }

    /**
     * Gets task status
     */
    public String getStatus() {
        String check;
        if (isComplete) {
            check = "[X]";
        } else {
            check = "[ ]";
        }
        return check;
    }

    /**
     * Gets task description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Checks if task is complete
     *
     * @return true if task is complete, false if not
     */
    public boolean isComplete() {
        return isComplete;
    }
}

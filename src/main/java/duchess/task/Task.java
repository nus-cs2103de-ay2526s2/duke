package duchess.task;

import java.time.LocalDate;

/**
 * Class representing a task.
 */
public abstract class Task {
    protected final String name;
    private boolean isComplete = false;

    /**
     * Constructor for Task class.
     *
     * @param name the name of the task
     */
    public Task(String name) {
        this.name = name;
    }

    /**
     * Returns the name of the task.
     *
     * @return the name of the task
     */
    public String getName() {
        return name;
    }

    /**
     * Marks the task as complete.
     */
    public void markAsComplete() {
        isComplete = true;
    }

    /**
     * Marks the task as incomplete.
     */
    public void markAsIncomplete() {
        isComplete = false;
    }

    public boolean isComplete() {
        return isComplete;
    }

    /**
     * Sets the completion status of the task.
     *
     * @param isComplete the completion status of the task
     */
    protected void setComplete(boolean isComplete) {
        this.isComplete = isComplete;
    }

    public boolean isOutstanding(LocalDate date) {
        return false;
    }

    /**
     * Returns a string representation of the task.
     *
     * @return a string representation of the task
     */
    @Override
    public String toString() {
        return String.format("[%s] %s", isComplete() ? "X" : " ", name);
    }

    public abstract String toSaveString();
}

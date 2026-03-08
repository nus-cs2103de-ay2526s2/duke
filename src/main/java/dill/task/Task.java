package dill.task;

import java.time.LocalDate;

/**
 * Represents a generic task that provides the foundation for specific task types.
 */
public abstract class Task {
    private String taskName;
    private boolean isDone;

    /**
     * Initializes a task with a name and sets its status as incomplete.
     *
     * @param taskName The description of the task.
     */
    public Task(String taskName) {
        this.taskName = taskName;
        this.isDone = false;
    }

    /**
     * Returns the task type identifier for this task.
     *
     * @return The task type as a string.
     */
    public abstract String getTaskType();

    /**
     * Updates this task using the provided update fields.
     *
     * @param updateFields The UpdateFields object containing new values for this task.
     */
    public abstract void updateTask(UpdateFields updateFields);

    /**
     * Creates and returns a copy of this task.
     *
     * @return A new Task object with the same properties as this task.
     */
    public abstract Task cloneTask();

    /**
     * Returns the relevant date(s) associated with this task.
     *
     * @return An empty string array since a generic task has no associated dates.
     */
    public String[] getDates() {
        return new String[0]; // Return empty array by default for a todo task
    }

    /**
     * Sets the completion status of this task.
     *
     * @param isDone True if the task is completed, false otherwise.
     */
    public void setIsDone(boolean isDone) {
        this.isDone = isDone;
    }

    /**
     * Returns the completion status of this task.
     *
     * @return True if the task is completed, false otherwise.
     */
    public boolean getIsDone() {
        return this.isDone;
    }

    /**
     * Returns the name (description) of this task.
     *
     * @return The task name.
     */
    public String getTaskName() {
        return this.taskName;
    }

    /**
     * Sets the name (description) of this task.
     *
     * @param taskName The new task name.
     */
    public void setTaskName(String taskName) {
        assert taskName != null : "task name should not be null";
        this.taskName = taskName;
    }

    /**
     * Checks whether this task occurs on the specified date.
     *
     * @param date The date to check against.
     * @return True if the task occurs on the date, false otherwise.
     */
    public boolean isOccurringOn(LocalDate date) {
        return false; // Returns false by default for a todo task
    }

    /**
     *  Returns a string representation of the task for displaying in the user interface.
     *
     * @return A formatted string representation of the task.
     */
    public String toString() {
        if (isDone) {
            return "[X] " + taskName;
        }
        return "[ ] " + taskName;
    }
}
